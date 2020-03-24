package com.aaa.lee.repast.base;

import com.aaa.lee.repast.mapper.*;
import com.aaa.lee.repast.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@Service
public class CommonCart {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 延时8分钟 加入购物车 修改库存 八分钟之内没有下单 就恢复库存
     */
    public Boolean deleteTimeUpdate(Integer stock,Integer quantity,Long productId,String token){
        ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);
        Member member = memberMapper.selectMemberByToken(token);
        Long memberId = member.getId();
        //修改商品表中的库存
        Integer surplusstock = stock - quantity;
        System.out.println(surplusstock);
        Product product = new Product();
        product.setId(productId)
                .setStock(surplusstock);
        Integer countProduct = productMapper.updateProductStock(product);
        if (countProduct >0){
            System.out.println("修改商品和库存表的库存成功");
            //设置定时8分钟后库存返回修改之前的数据
            mScheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    //通过查询购物车 看该商品是否生成订单
                    System.out.println("开始定时");
                    CartItem cartItem = new CartItem();
                    cartItem.setProductId(productId);
                    cartItem.setMemberId(memberId);
                    CartItem cart = cartItemMapper.selectMemberCart(cartItem);
                    if (1 != cart.getDeleteStatus()) {
                        //如果商品是否删除状态不为1 为0（未删除） 未删除就代表没有生成订单
                        //生成订单的话  状态为改为1 删除状态 从新将库存恢复之前的数量
                        product.setId(productId)
                                .setStock(stock);
                        Integer ifBackSku = productMapper.updateProductStock(product);
                        if (ifBackSku >0){
                            System.out.println("8分钟内订单没有生成，恢复库存");
                        }else {
                            System.out.println("库存恢复失败");
                        }
                    }
                }
            },1, TimeUnit.MINUTES);
            return true;
        }else {
            System.out.println("修改商品和库存表的库存失败");
            return false;
        }
    }
    /**
     * 添加商品到购物车
     */
    public Boolean addCartCom(String token,Long productId,Long shopId,Integer quantity,Integer productServiceStatus){
        Member member = memberMapper.selectMemberByToken(token);
        Long memberId = member.getId();
        String nickName = member.getNickname();
        CartItem cartItem= new CartItem();
        Comment comment= commentMapper.selectCommentById(productId);
        //通过商品id查询该商品的上下架,以及添加时数据的获取都需要用到该方法
        Product product = productMapper.selectProductById(productId);
        //通过店铺id和商品id查询库存,获取新增时的字段，以及库存数量
        Sku sku = skuMapper.selectSkuById(productId);
        Integer stock = sku.getStock();
        //把获取到的属性放进购物车实体类中，把这些属性添加到购物车表中
        cartItem.setProductId(productId);
        cartItem.setProductSkuId(sku.getId());
        cartItem.setMemberId(memberId);
        cartItem.setShopId(shopId);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(product.getPrice());
        cartItem.setSp1(sku.getSp1());
        cartItem.setSp2(sku.getSp2());
        cartItem.setSp3(sku.getSp3());
        cartItem.setProductPic(product.getPic());;
        cartItem.setProductName(product.getName());
        cartItem.setProductSubTitle(product.getSubTitle());
        cartItem.setProductSkuCode(sku.getSkuCode());
        cartItem.setMemberNickname(nickName);
        cartItem.setCreateDate(date());
        cartItem.setDeleteStatus(0);
        cartItem.setProductCategoryId(product.getProductCategoryId());
        cartItem.setProductBrand(product.getBrandName());
        cartItem.setProductSn(product.getProductSn());
        cartItem.setProductAttr(comment.getProductAttribute());
        cartItem.setProductServiceStatus(productServiceStatus);
        System.out.println("CommonCart"+cartItem);
        Integer insertCart = cartItemMapper.addCartPro(cartItem);
        if (insertCart>0){
            System.out.println("新增成功");
            return true;
        }else {
            System.out.println("新增异常");
            return false;
        }
    }
    /**
     * 时间日期的封装
     * @return
     */
    public Date date(){
        Date date1 = new Date();
        String formatDate = null;
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
        formatDate = dFormat.format(date1);
        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date strD = null ;
        try {
            strD = lsdStrFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strD;
    }
}
