package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.mapper.OrderItemMapper;
import com.aaa.lee.repast.mapper.OrderMapper;
import com.aaa.lee.repast.mapper.ProductMapper;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.lee.repast.staticstatus.StaticCode.FORMAT_DATE;
import static com.aaa.lee.repast.status.StatusEnums.FAILED;

@Service
public class OrderService extends BaseService<Order> {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ResultData insertOrder(@RequestParam("token") String token, @RequestBody List<CartItem> cartItems) {
        ResultData resultData = new ResultData();
        Member member = memberMapper.selectMemberByToken(token);
        Order order = new Order();
        /*
        前端传过来一个购物车商品的list List<CartItem>

​	根据商品ID比对product表的库存，商品价格，商品上下架状态，
加事务，加行级锁，查询商品表，更新商品库存，
新增订单表初始状态为待付款订单，新增订单清单表
        * */
        if (null != member) {
            //用户id
            Long memberId = member.getMemberLevelId();
            order.setMemberId(memberId);
            //店铺id（必传）
            Long zero = Long.valueOf(0);

            //拼团和促销待定


            //订单编号
            String OrderSn = IDUtil.getUUID() + memberId;
            order.setOrderSn(OrderSn);
            //时间
            Date date = new Date();//获得系统时间.
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
            String nowTime = sdf.format(date);
            Date time = null;
            try {
                time = sdf.parse(nowTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //提交时间
            order.setCreateTime(time);
            //用户名
            String memeberUserName = member.getUsername();
            order.setMemberUsername(memeberUserName);

            //新建订单，然后以订单id为索引在下边引用
            int x = orderMapper.insert(order);
            if (1 != x) {
                resultData.setCode(FAILED.getCode());
                resultData.setMsg(FAILED.getMsg());
                resultData.setDetail("新建订单失败");
                return resultData;
            }

            Order order1 = orderMapper.selectOne(order);
            Long orderId = order1.getId();
            //价格计算 :总金额
            BigDecimal zongJia = null;
            for (CartItem cartItem : cartItems) {
                //数量
                int quantity = cartItem.getQuantity();
                BigDecimal q = BigDecimal.valueOf(quantity);
                Long prodoctId = cartItem.getProductId();
                Long shopId = cartItem.getShopId();

                Product product1 = new Product();
                product1.setShopId(shopId);
                product1.setId(prodoctId);

                Product product = productMapper.selectOne(product1);
                if (0 >= product.getStock()) {
                    resultData.setCode(FAILED.getCode());
                    resultData.setMsg(FAILED.getMsg());
                    resultData.setDetail(prodoctId + "库存不足");
                    return resultData;
                }
                //单个价格
                //促销没想明白，待定

                BigDecimal danJia = product.getPrice();


                //优惠券此处待定
                //所有这个商品的价格
                BigDecimal price = danJia.multiply(q);


                //订单详情表
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setOrderSn(OrderSn);
                orderItem.setProductId(prodoctId);
                orderItem.setProductPic(product.getPic());
                orderItem.setProductName(product.getName());
                orderItem.setProductBrand(String.valueOf(product.getBrandId()));
                orderItem.setProductSn(product.getProductSn());
                orderItem.setProductPrice(price);
                orderItem.setProductQuantity(quantity);
                //skuid不知为何物？
                //sku条码？？？
                orderItem.setProductCategoryId(product.getProductCategoryId());
                //商品销售属性
                //被优惠金额待定
                // TODO: 2020/3/22  优惠券

                int xiangQing = orderItemMapper.insert(orderItem);
                if (1 != xiangQing) {
                    resultData.setCode(FAILED.getCode());
                    resultData.setMsg(FAILED.getMsg());
                    resultData.setDetail("详情表新增失败");
                    return resultData;
                }
                zongJia = zongJia.add(price);

            }
            order.setPayType(0);
            order.setSourceType(1);
            order.setStatus(0);
            order.setOrderType(0);

            int z = orderMapper.updateByPrimaryKeySelective(order);
            if (1 != z) {
                resultData.setCode(FAILED.getCode());
                resultData.setMsg(FAILED.getMsg());
                resultData.setDetail("订单新增失败");
                return resultData;
            }

        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;
    }

    /**
    * @Author Gotta
    * @Description 根据订单查询订单商品清单
    * @Date 12:26 2020/3/21
    * @Param order
    * @return java.util.List<com.aaa.lee.repast.model.OrderItem>
    **/
    public List<OrderItem> selectOrderItemByOrder(Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        return orderItemMapper.select(orderItem);
    }

    /**
    * @Author Gotta
    * @Description 根据订单属性更新订单状态
    * @Date 10:23 2020/3/21
    * @Param order
    * @return java.lang.Boolean
    **/
    public Boolean cancelOrder(Order order) {
        if (orderMapper.updateByPrimaryKey(order) > 0) {
            return true;
        }
        return false;
    }

    /**
    * @Author Gotta
    * @Description 根据订单回滚库存
    * @Date 16:26 2020/3/21
    * @Param order
    * @return java.lang.Boolean
    **/
    public Boolean rollbackStockByOrder(Order order) {
        List<OrderItem> orderItems = selectOrderItemByOrder(order);
        for (OrderItem orderItem : orderItems) {
            if(productMapper.updateProductStock(new Product().setId(orderItem.getProductId()).setStock(orderItem.getProductQuantity()))>0){

                return true;
            }
        }
        return false;
    }

    /**
    * @Author Gotta
    * @Description 根据订单取消订单
    * @Date 16:37 2020/3/21
    * @Param order
    *   订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    * @return java.lang.Boolean
    **/
    public Boolean cancel(Order order) {
        if (null == order.getId() || order.getId().equals("")) {
            return false;
        }
        Integer status = order.getStatus();
        if (status == 0) {
            if (cancelOrder(order) && rollbackStockByOrder(order)) {
                return true;
            }
        } else if (status == 1) {
            // TODO 退款方法
            if (cancelOrder(order) && rollbackStockByOrder(order)) {
                return true;
            }
        } else if (status == 2) {
            if (cancelOrder(order)) {
                return true;
            }
        }
        return false;
    }
}
