package com.aaa.lee.repast.service;

import com.aaa.lee.repast.VO.FileNameSite;
import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.*;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.properties.FtpProperties;
import com.aaa.lee.repast.upload.UploadService;
import com.aaa.lee.repast.utils.CurrentTimeUtil;
import com.aaa.lee.repast.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.aaa.lee.repast.staticstatus.StaticCode.*;

/**
 * @Author 丁平达
 * @Date 2020/3/18 13:29
 * 订单退货申请
 */
@Service
public class OrderReturnApplyService extends BaseService<OrderReturnApply> {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private FtpProperties ftpProperties;
    /**
     *  查询所有的订单SN(后台管理员用的)
     * @return
     */
    public List<OrderItem> selectOrderSn(){
        List<OrderItem> orderItemList = orderItemMapper.selectOrderItemSn();

        if (null!=orderItemList&&orderItemList.size()>ZERO){
            return orderItemList;
        }
     return null;
    }
    /**
     *      查询单个要退款的订单（前台）
     * @param order
     * @param token
     * @return
     */
    public OrderReturnApply selectOrderReturnApply(Order order, String token){
        //当token不为空 并且订单编号不为空
        if (null!= order &&  null!=order.getOrderSn() && StringUtil.isNotEmpty(order.getOrderSn())
                && StringUtil.isNotEmpty(token)&& null!=token){
            //创建了一个订单和订单详情对象

            //查询订单详情信息
            List<OrderItem> orderItems = orderItemMapper.selectOrder(order.getOrderSn());
            //判断当详情信息存在
            if (null!=orderItems &&orderItems.size()>ZERO){
                OrderReturnApply orderReturn = new OrderReturnApply();
                orderReturn
                        //店铺id
                .setShopId(order.getShopId())
                        //订单id
                .setOrderId(order.getId())
                        //订单编号
                .setOrderSn(order.getOrderSn())
                        //店铺位置id
                .setCompanyAddressId(order.getShopId())
                .setCreateTime(new CurrentTimeUtil().currentTime())
                .setMemberUsername(memberMapper.selectMemberByToken(token).getUsername());
                return orderReturn;
            }else {
                //如果不存在则返回null
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *  退货申请：未发货退款和已发货退款，以及已收货退货
     * @param orderReturnApply
     * @param token
     * @return
     */
    public Boolean InsertOrderReturnApplyOne(OrderReturnApply orderReturnApply, String token){
        //判断用户在登陆状态并且订单id不为空
        if (null != token && StringUtil.isNotEmpty(token)&&null != orderReturnApply.getOrderSn()){
        //查询本次退款的订单
            Order order = orderMapper.selectOrder(orderReturnApply.getOrderSn());
            //查询订单详情信息
            List<OrderItem> orderItems = orderItemMapper.selectOrder(order.getOrderSn());
            //判断订单不会空并且发货单状态不为空并且为未发货的时候
            //为1的时候就是代发货状态
            CurrentTimeUtil currentTimeUtil = new CurrentTimeUtil();
            if (null!=order.getOrderSn() && null!= order.getStatus()
                &&order.getStatus()==ONE){
                for (OrderItem orderItem:orderItems){
                    //订单中的每一个商品的id
                    orderReturnApply.setProductId(orderItem.getProductId());
                    BigDecimal bigDecimal = new BigDecimal(orderItem.getProductQuantity());
                    //计算单个商品的实际支付金额
                    BigDecimal divide = orderItem.getRealAmount().
                            divide(bigDecimal,TWO, BigDecimal.ROUND_HALF_UP);
                    orderReturnApply
                    .setId(null)
                            //申请时间
                    .setCreateTime(currentTimeUtil.currentTime())
                            //退款金额
                    .setReturnAmount(orderItem.getRealAmount())
                            //申请退款状态
                    .setStatus(ZERO)
                            //商品图片
                    .setProductPic(orderItem.getProductPic())
                            //商品名字
                    .setProductName(orderItem.getProductName())
                            //商品品牌
                    .setProductBrand(orderItem.getProductBrand())
                            //退货的数量
                    .setProductCount(orderItem.getProductQuantity())
                            //商品单价
                    .setProductPrice(orderItem.getProductPrice())
                            //商品实际支付的单价
                    .setProductRealPrice(divide)
                            //收货人
                    .setReceiveMan(order.getReceiverName());
                    //网数据库里面加数据
                    Integer add = super.add(orderReturnApply);
                }
            return true;
            }else if (null!=order.getOrderSn() && null!= order.getStatus()
                    &&order.getStatus()==TWO){
                for (OrderItem orderItem:orderItems){
                    //订单中的每一个商品的id
                    orderReturnApply.setProductId(orderItem.getProductId());
                    //计算退款金额
                    BigDecimal bigDecimal = new BigDecimal(orderItem.getProductQuantity());
                    BigDecimal SevenPercent = new BigDecimal(ZERO_DOT_SEVEN);
                    BigDecimal divide = orderItem.getRealAmount().
                            divide(bigDecimal,TWO, BigDecimal.ROUND_HALF_UP)
                            .divide(SevenPercent,TWO, BigDecimal.ROUND_HALF_UP);
                    orderReturnApply
                            .setId(null)
                            //申请时间
                            .setCreateTime(currentTimeUtil.currentTime())
                            //退款金额
                            .setReturnAmount(orderItem.getRealAmount()
                                    .divide(SevenPercent,TWO, BigDecimal.ROUND_HALF_UP))
                            //申请退款状态
                            .setStatus(ZERO)
                            //商品图片
                            .setProductPic(orderItem.getProductPic())
                            //商品名字
                            .setProductName(orderItem.getProductName())
                            //商品品牌
                            .setProductBrand(orderItem.getProductBrand())
                            //退货的数量
                            .setProductCount(orderItem.getProductQuantity())
                            //商品单价
                            .setProductPrice(orderItem.getProductPrice())
                            //商品实际支付的单价
                            .setProductRealPrice(divide);
                    //网数据里面加数据
                     super.add(orderReturnApply);
                }
                return true;
            }
                }
        return false;
    }

    /**
     *  图片上传退款
     * @param token
     * @param file
     * @param uploadService
     * @param shopId
     * @param orderId
     * @param companyAddressId
     * @param orderSn
     * @param memberUsername
     * @param returnName
     * @param returnPhone
     * @param reason
     * @param description
     * @return
     */
    public Boolean insetOrderReturnApplyPic(String token, MultipartFile file,
                                            UploadService uploadService, Long shopId,
                                            Long orderId, Long companyAddressId,
                                            String  orderSn, String memberUsername,
                                            String returnName, String returnPhone,
                                            String reason, String description){
        OrderReturnApply orderReturnApply = new OrderReturnApply();
        orderReturnApply.setShopId(shopId).setOrderId(orderId).setCompanyAddressId(companyAddressId)
                .setOrderSn(orderSn).setMemberUsername(memberUsername).setReturnName(returnName)
                .setReturnPhone(returnPhone).setReason(reason).setDescription(description);


        if (null != token && StringUtil.isNotEmpty(token)&&null != orderReturnApply.getOrderSn()) {
            //查询本次退款的订单
            Order order = orderMapper.selectOrder(orderReturnApply.getOrderSn());
            //查询订单详情信息
            List<OrderItem> orderItems = orderItemMapper.selectOrder(order.getOrderSn());
            //判断订单不会空并且发货单状态不为空并且为未发货的时候
            //为1的时候就是代发货状态
            CurrentTimeUtil currentTimeUtil = new CurrentTimeUtil();
            if (null!=order.getOrderSn() && null!= order.getStatus()
                    &&order.getStatus()==THREE){
                //照片地址和名字
                String PicSite=null;
                if (file!=null){
                    FileNameSite upload = uploadService.upload(file, token);
                    PicSite = upload.getFilenameSite();
                }
                for (OrderItem orderItem:orderItems){
                    //订单中的每一个商品的id
                    orderReturnApply.setProductId(orderItem.getProductId());
                    //计算退款金额
                    //数量
                    BigDecimal bigDecimal = new BigDecimal(orderItem.getProductQuantity());
                    //实际的退款金额除于数量等于单价
                    BigDecimal divide = orderItem.getRealAmount().
                            divide(bigDecimal,TWO, BigDecimal.ROUND_HALF_UP);
                    orderReturnApply
                            .setId(null)
                            //申请时间
                            .setCreateTime(currentTimeUtil.currentTime())
                            //退款金额
                            .setReturnAmount(orderItem.getRealAmount())
                            //申请退款状态
                            .setStatus(ZERO)
                            //商品图片
                            .setProductPic(orderItem.getProductPic())
                            //商品名字
                            .setProductName(orderItem.getProductName())
                            //商品品牌
                            .setProductBrand(orderItem.getProductBrand())
                            //退货的数量
                            .setProductCount(orderItem.getProductQuantity())
                            //商品单价
                            .setProductPrice(orderItem.getProductPrice())
                            //商品实际支付的单价
                            .setProductRealPrice(divide);
                        if(null!=PicSite&&!NULL_STRING.equals(PicSite)){
                            //凭证图片
                            orderReturnApply.setProofPics(PicSite);
                        }
                    //网数据里面加数据
                    super.add(orderReturnApply);
                }
                return true;
        }

    }
        return false;
}

    /**
     *  当前用户下的所有申请退单的SN
     * @param pageInfos
     * @param token
     * @return
     */
    public  List<OrderReturnApply>  selectShopOrderReturnApply(PageInfos pageInfos, String token){
        if (null!=token&& StringUtil.isNotEmpty(token)){
            //查询用户信息
            Member member = memberMapper.selectMemberByToken(token);
            //首次查询当前也为空则让他从第0条开始查询
            if (null==pageInfos.getPageNum()){
                pageInfos.setPageNum(ZERO);
            }
            //每页7条
            pageInfos.setPageSize(SEVEN);
            //使用分页插件
            PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
            //查询要分页的数据 当前用户店铺下的所有Sn
            List<OrderReturnApply> orderReturnApplies = orderReturnApplyMapper.
                    selectOrderReturnApplyShopId(member.getShopId());
            List<OrderReturnApply> listOne=new ArrayList<>();
            for (OrderReturnApply orderReturnAppliesIp:orderReturnApplies){
                String httpPath = ftpProperties.getHttpPath();
                if (null!=orderReturnAppliesIp.getProofPics()
                        &&!NULL_STRING.equals(orderReturnAppliesIp.getProofPics())){
                orderReturnAppliesIp.setProofPics
                        (httpPath+SLASH+orderReturnAppliesIp.getProofPics());
                }
                listOne.add(orderReturnAppliesIp);
            }
            //让数据进行分页
            PageInfo<OrderReturnApply> orderReturnApplyPageInfo = new PageInfo<>(listOne);
            List<OrderReturnApply> list = orderReturnApplyPageInfo.getList();
            return list;
        }
        return null;
    }

    /**
     *  查询当前订单下的所有商品
     * @param orderReturnApply
     * @param List<OrderItem>
     * @return
     */
    public List<OrderItem> selectOrderReturnApplyByOrderSn
            (OrderReturnApply orderReturnApply, String token){
        if (null!=token&& StringUtil.isNotEmpty(token)){
            //根据sn查询订单下的商品
            List<OrderItem> orderItemList = orderItemMapper.selectOrder(orderReturnApply.getOrderSn());
            if (null!=orderItemList &&orderItemList.size()>0){
                return orderItemList;
            }
            return null;
        }
        return null;
    }

    /**
     *  审批退单申请
     * @param orderReturnApply
     * @param token
     * @return
     */
    public Boolean updateOrderReturnApplyByOrderSn(OrderReturnApply orderReturnApply,
                                                   String token, String name,
                                                   OrderReturnReasonService orderReturnReasonService){
        CurrentTimeUtil currentTimeUtil=new CurrentTimeUtil();
        if (null!=token&& StringUtil.isNotEmpty(token)){
            orderReturnApply.setId(null);
            Member member = memberMapper.selectMemberByToken(token);

            //判断当前的状态等于1 则同时退订单
            if (orderReturnApply.getStatus()==ONE){
                orderReturnApply.setHandleTime(currentTimeUtil.currentTime());
                //修改订单的具体字段
                Integer integer =
                        orderReturnApplyMapper.updateOrderReturnApplyByOrderSn(orderReturnApply);
                if (integer>ZERO){
                    //增加原因表数据 为启用状态
                    OrderReturnReason orderReturnReason = new OrderReturnReason();
                    orderReturnReason.setCreateTime(new CurrentTimeUtil().currentTime())
                    .setShopId(orderReturnApply.getShopId())
                    .setName(name).setStatus(ONE);
                    Boolean aBoolean = orderReturnReasonService.insertOrderReturnReason(orderReturnReason);
                    return aBoolean;
                }else {
                    return false;
                }
            }else if (orderReturnApply.getStatus()==TWO){
                //当状态为2的时候则标识退款订单货物已完成
                //修改订单申请的状态
                orderReturnApply.setReceiveTime(currentTimeUtil.currentTime());
                Integer integer = orderReturnApplyMapper.OrderReturnApplyByOrderSnByStatus(orderReturnApply);
                if (integer>ZERO){
                    //获取所有商品
                    List<OrderItem> orderItemList = orderItemMapper.selectOrder(orderReturnApply.getOrderSn());
                    //循环遍历每一个商品
                    for (OrderItem orderItem:orderItemList){
                        //通过商品类别id获取产品属性
                        ProductCategory productCategory = productCategoryMapper.
                                selectProductCategoryById(orderItem.getProductCategoryId());
                        //当这个商品的属性等级大于5的时候则为可以加库存商品
                    if (productCategory.getLevel()>FIVE){
                        //根据循环出来的当前商品id获取商品对象
                        Product product = productMapper.
                                selectProductGiftPoint(orderItem.getProductId().intValue());
                        //退货的数量
                        //+库存
                        Integer i = product.getStock() + orderItem.getProductQuantity();
                        Integer a = product.getLowStock() + orderItem.getProductQuantity();
                        product.setStock(i) ;
                        //+预警值库存
                        product.setLowStock(a) ;
                       productMapper.updateProductStock(product);
                       return true;
                    }
                    return true;
                    }
                }
                return false;
            }else if (orderReturnApply.getStatus()==THREE){
                //状态为3则表示拒绝退单
                //修改订单的具体字段
                Integer integer =
                        orderReturnApplyMapper.updateOrderReturnApplyByOrderSn(orderReturnApply);
                if (integer>ZERO){
                    //增加原因表数据 为启用状态
                    OrderReturnReason orderReturnReason = new OrderReturnReason();
                    //添加数据为不启用
                    orderReturnReason.setCreateTime(new CurrentTimeUtil().currentTime())
                            .setShopId(orderReturnApply.getShopId())
                            .setName(name).setStatus(ZERO);
                    orderReturnApply.setHandleTime(currentTimeUtil.currentTime());
                    Boolean aBoolean = orderReturnReasonService.insertOrderReturnReason(orderReturnReason);
                    return aBoolean;
                }else {
                    return false;
                }
            }

        }
        return false;
    }
}
