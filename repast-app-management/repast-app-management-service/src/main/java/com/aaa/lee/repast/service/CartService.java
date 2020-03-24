package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonCart;
import com.aaa.lee.repast.mapper.*;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@Service
public class CartService extends BaseService<CartItem> {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;
    @Autowired
    private ProductAttributeMapper productAttributeMapper;
    @Autowired
    private SkuMapper skuMapper;
    //产生异常，一但return就就会跳出for循环
    private Exception exception = new Exception("操作失败");

    /**
     * 设置延时处理
     */
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);
    /**
     * 从购物车中删除该商品
     */
    public Boolean deleteProductFromCart(Long productId,String token){
        Member member = memberMapper.selectMemberByToken(token);
        if (null != member){
            Long memberId = member.getId();
            Integer integer = cartItemMapper.deleteByProductIdAndMemberId(productId, memberId);
            if (integer > 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 通过token查询该用户的购物车
     */
    public List<CartItem> selectCartByToken(String token){
        Member member = memberMapper.selectMemberByToken(token);
        List<CartItem> cartItems = null;
        if (null != member){
            Long memberId = member.getId();
            cartItems = cartItemMapper.selectCartByMemberId(memberId);
        }
        if (cartItems.size() > 0){
            return cartItems;
        }else {
            return null;
        }
    }

    /**
     * 添加食堂商品到购物车
     */
    public Boolean canteenAddProductToCart(String token,Long productId){
        //定义一个Boolean值flag方法需要返回的值
        Boolean flag = false;
        //定义一个Boolean值repeat
        Boolean repeat = false;
        Member member = memberMapper.selectMemberByToken(token);
        List<CartItem> cartItems = cartItemMapper.selectCartByMemberId(member.getId());
        Product product = productMapper.selectProductById(productId);
        CartItem cartItem = new CartItem();
        CartItem cartItem1 = cartItemMapper.selectCartByMemberIdAndProductId(member.getId(), productId);
        //遍历查询出来的当前用户的购物车商品
        for (CartItem item : cartItems) {
            //如果购物车由该商品repeat为true
            if (item.getProductId().equals(productId)){
                repeat = true;
                break;
            }else {
                repeat = false;
            }
        }
        if (repeat){
            //如果repeat为true则购物车该商品数量+1
            cartItem1.setQuantity(cartItem1.getQuantity() + 1);
            int i = cartItemMapper.updateByPrimaryKey(cartItem1);
            if (i > 0){
                flag = true;
            }else {
                flag = false;
            }
        }
        if (null != member && null != product && repeat == false){
            cartItem.setMemberId(member.getId());
            cartItem.setCreateDate(new Date());
            cartItem.setMemberNickname(member.getNickname());
            cartItem.setModifyDate(new Date());
            cartItem.setPrice(product.getPrice());
            ProductAttribute productAttribute = productAttributeMapper.selectByPrimaryKey(product.getProductAttributeCategoryId());
            if (null != productAttribute){
                cartItem.setProductAttr(productAttribute.getName());
            }
            cartItem.setProductBrand(product.getBrandName());
            cartItem.setProductCategoryId(product.getProductCategoryId());
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setProductPic(product.getPic());
            SkuStock skuStock = skuStockMapper.selectByProductId(productId);
            if (null != skuStock){
                cartItem.setProductSkuCode(skuStock.getSkuCode());
                cartItem.setProductSkuId(skuStock.getId());
            }
            cartItem.setProductSn(product.getProductSn());
            cartItem.setProductSubTitle(product.getSubTitle());
            cartItem.setQuantity(1);
            cartItem.setShopId(product.getShopId());
            int insert = cartItemMapper.insert(cartItem);
            if (insert > 0){
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * //添加购物车
     **/
    public Boolean addCart(Map<String,Object> data, String token, CommonCart commonCart){
        Member member =memberMapper.selectMemberByToken(token);
        if (null!=member){
            Long memberId=member.getId();
            System.out.println(data);
            System.out.println(data.get("cart"));
            //1.首先获取前台传过来的商品信息
            List<Map> list = JSONUtil.toList(JSONUtil.toJsonString(data.get("cart")),Map.class);
            System.out.println(list);
            if (list.size()>0) {
                try {
                    int productServiceStatus = Integer.parseInt(JSONUtil.toJsonString(data.get("productServiceStatus")));
                    //2.将获取到的数据遍历出来
                    for (int i = 0; i < list.size(); i++) {
                        Map map = list.get(i);
                        Integer quantity = Integer.parseInt(JSONUtil.toJsonString(map.get("quantity")));
                        Long productId = Long.parseLong(JSONUtil.toJsonString(map.get("productId")));
                        Long shopId = Long.parseLong(JSONUtil.toJsonString(map.get("shopId")));
                        CartItem cartItem = new CartItem();
                        cartItem.setProductId(productId);
                        cartItem.setMemberId(memberId);
                        CartItem cartItem1 = cartItemMapper.selectMemberCart(cartItem);
                        //3.通过productId查询商品的库存数量
                        Sku sku = skuMapper.selectSkuById(productId);
                        System.out.println(sku);
                        Integer stock = sku.getStock();
                        //4.通过productId查询商品表 从而获取publishStatus
                        Product product = productMapper.selectProductById(productId);
                        Integer publishStatus = product.getPublishStatus();
                        if (0 != quantity) {
                            //如果购买数量不为0 证明顾客有意购买商品
                            if (null != cartItem1) {
                                //如果购物车不为空 证明购物车里有此商品
                                if (1 == publishStatus) {
                                    //如果publishStatus为1，证明此商品是上架商品
                                    if (2 == productServiceStatus) {
                                        //商品类型为2  购买的商品是有数量之分的 是需要考虑库存的问题
                                        if (stock >= quantity) {
                                            //如果购买商品数量quantity小于等于该商品库存 证明可以购买此商品
                                            CartItem cartItem2 = new CartItem();
                                            cartItem2.setProductId(productId);
                                            cartItem2.setMemberId(memberId);
                                            cartItem2.setQuantity(quantity);
                                            cartItem2.setModifyDate(commonCart.date());//修改的时间
                                            Integer update = cartItemMapper.updateCart(cartItem2);
                                            //通过封装的deleteTimeUpdate方法  实现对购物车的修改
                                            commonCart.deleteTimeUpdate(stock, quantity, productId, token);
                                        } else {
                                            //stock库存小于quantity购买数量 不可以购买
                                            System.out.println("商品的库存不足！");
                                            throw exception;
                                        }
                                    } else {
                                        //商品服务类型为0,1 是外卖或者预约 不需要考虑库存
                                        CartItem cartItem3 = new CartItem();
                                        cartItem3.setProductId(productId);
                                        cartItem3.setMemberId(memberId);
                                        cartItem3.setQuantity(quantity);
                                        cartItem3.setModifyDate(commonCart.date());//修改的时间
                                        Integer update = cartItemMapper.updateCart(cartItem3);
                                        if (update == 0) {
                                            System.out.println("修改购买数量失败");
                                            throw exception;
                                        }
                                    }
                                } else {
                                    //如果publishStatus为0 证明此商品为下架商品 删除购物车信息
                                    Integer delete = cartItemMapper.deleteCart(cartItem);
                                    if (delete == 0) {
                                        System.out.println("商品为下架，删除购物车信息成功");
                                        throw exception;
                                    }
                                }
                            } else {
                                //查询出此商品在自己的购物车里没有  但是用户想购买
                                if (1 == product.getPublishStatus()) {
                                    //当商品为上架的商品
                                    if (2 == productServiceStatus) {
                                        //需要考虑库存
                                        System.out.println("定时任务已开启");
                                        System.out.println(token+"---"+productId+"---"+shopId+"---"+quantity+"---"+productServiceStatus);
                                        commonCart.addCartCom(token, productId, shopId, quantity, productServiceStatus);
                                        commonCart.deleteTimeUpdate(stock, quantity, productId, token);
                                    } else {
                                        //服务类型为0,1 不需要考虑库存
                                        System.out.println("定时任务已开启");
                                        commonCart.addCartCom(token, productId, shopId, quantity, productServiceStatus);
                                    }
                                } else {
                                    //想购买的商品为已下架的商品
                                    System.out.println("商品已下架");
                                    throw exception;
                                }
                            }
                        }else{
                            //如果页面发送的数据为0，说明用户对于该商品不需要
                            if (null != cartItem1){
                                //查询数据库，数据库有信息，则删除购物车信息
                                Integer delete = cartItemMapper.deleteCart(cartItem);
                                if (delete == 0){
                                    System.out.println("删除购物车成功");
                                    throw exception;
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }
            else{
                System.out.println("前台发送数据为空");
                return false;
            }
        }
        else {
            System.out.println("您还没登录，请请先登录");
            return false;
        }
        return true;
    }

}
