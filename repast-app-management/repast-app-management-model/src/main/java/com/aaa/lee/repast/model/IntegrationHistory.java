package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 *  历史操作记录类
 */
@Table(name = "ums_integration_change_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class IntegrationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员ID
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 店铺ID
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 创建记录时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 积分变化类型 1. 外卖下单获取积分，2.食堂下单获取积分，3. 管理员修改 ，4. 积分兑换消耗积分
     */
    @Column(name = "change_type")
    private Integer changeType;

    /**
     * 积分改变数量
     */
    @Column(name = "change_count")
    private Integer changeCount;

    /**
     * 操作人员
     */
    @Column(name = "operate_man")
    private String operateMan;

    /**
     * 操作备注
     */
    @Column(name = "operate_note")
    private String operateNote;

    /**
     * 积分来源：0->购物奖励；1->管理员修改；2->购物消费 3--》积分销号
     */
    @Column(name = "source_type")
    private Integer sourceType;
}