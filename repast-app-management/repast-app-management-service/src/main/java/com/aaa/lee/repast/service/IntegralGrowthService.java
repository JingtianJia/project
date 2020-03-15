package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.IntegrationHistoryMapper;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.mapper.ProductMapper;
import com.aaa.lee.repast.model.IntegrationHistory;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.Order;
import com.aaa.lee.repast.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aaa.lee.repast.staticstatus.StaticCode.*;

/**
 * @Author 丁平达
 * @Date 2020/3/13 14:02
 *
 *  积分成长规则
 */
@Service
public class IntegralGrowthService extends BaseService<IntegrationHistory> {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private IntegrationHistoryMapper integrationHistoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Mapper<IntegrationHistory> getMapper() {
        return integrationHistoryMapper;
    }

    /**
     *       Dpd
     *      根据订单实际支付金额进行增加积分
     *      或者根据积分上传的消费的实际积分来减少积分
     *      并且增加历史总积分
     *      进行添加积分操作记录
     * @param order
     * @return
     */
    public Boolean IntegralGrowthAdd(Order order){
        if (null != order.getMemberId()){
            Integer payAmount = order.getPayAmount().intValue();
            //获得订单的店铺id
            int shopId = order.getShopId().intValue();
            //获取店铺对象
            Product product = productMapper.selectProductGiftPoint(shopId);
            //获取店铺赠送的积分
            Integer giftPoint = product.getGiftPoint();
            //获取订单人id
            Long memberIdLong = order.getMemberId();
            int id = memberIdLong.intValue();
            if (null!=giftPoint){
                //根据订单人的id查询会员信息
                Member member = memberMapper.selectMemberIdByIntegration(id);
                //
                IntegrationHistory integrationHistory =
                        new IntegrationHistory();
                //获取id加入积分操作记录对象
                integrationHistory.setMemberId(member.getId());
                //获取店铺id
                integrationHistory.setShopId(order.getShopId());
                //获取当前时间
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat(FORMAT_DATE);
                String format = dateFormat.format(date);
                Date parse = null;
                try {
                    parse = dateFormat.parse(format);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //把当前时间加入
                integrationHistory.setCreateTime(parse);
                //积分改变数量
                integrationHistory.setChangeCount(giftPoint);
                //操作人员
                integrationHistory.setOperateMan(SELF);

                //获取会员的积分
                Integer integration = member.getIntegration();
                //获取会员历史总积分
                Integer historyIntegration = member.getHistoryIntegration();
                    if (null != member){
                        //如果是微信支付或者支付宝支付则增加积分
                        if (order.getPayType()==ONE||order.getPayType()==TWO){
                        //积分增加
                        Integer IncreaseIntegral =integration+giftPoint;
                        //将原本的积分加上新增的积分附加给对象
                        member.setIntegration(IncreaseIntegral);
                        //总积分加上下单积分
                        Integer historyIncreaseIntegral=   historyIntegration+giftPoint;
                        //加入对象
                        member.setHistoryIntegration(historyIncreaseIntegral);
                        //进行修改对象的积分和历史总积分
                            Integer integer = memberMapper.updateMemberIntegration(member);
                            //判断是否修改成功
                            if (integer>ZERO){
                                //消费类型
                                integrationHistory.setChangeType(order.getPayType());
                                //增加积分
                                integrationHistory.setOperateNote(INTEGRAL_INCREASE);
                                //积分来源奖励
                                integrationHistory.setSourceType(TWO);
                                try {
                                    super.add(integrationHistory);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }else {
                                return false;
                            }
                            //当支付状态为3的时候则是积分支付
                        }else if (order.getPayType()==THREE){
                            //判断如果积分大于商品的积分才可以下单
                            if (integration>giftPoint){
                                //积分减去商品价格
                                Integer ReduceIntegral= integration-payAmount;
                                member.setIntegration(ReduceIntegral);
                                Integer integer = memberMapper.
                                        updateMemberReduceIntegration(member);
                                //判断是否修改成功
                                if (integer>ZERO){
                                    //消费类型
                                    integrationHistory.setChangeType(order.getPayType());
                                    //增加积分
                                    integrationHistory.setOperateNote(REDUCE_INCREASE);
                                    //积分来源奖励
                                    integrationHistory.setSourceType(THREE);
                                    try {
                                        super.add(integrationHistory);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }else {
                                    return false;
                                }
                            }else {
                                return false;
                            }
                        }else {
                            return false;
                        }
                    }else {
                        return false;
                    }
            }
            return true;
        }
        return false;
    }

}
