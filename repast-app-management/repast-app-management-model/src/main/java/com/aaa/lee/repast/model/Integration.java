package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ums_integration_change_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class Integration {
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
     * 积分来源：0->购物奖励；1->管理员修改；2->购物消费
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取会员ID
     *
     * @return member_id - 会员ID
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 设置会员ID
     *
     * @param memberId 会员ID
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取店铺ID
     *
     * @return shop_id - 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店铺ID
     *
     * @param shopId 店铺ID
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取创建记录时间
     *
     * @return create_time - 创建记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建记录时间
     *
     * @param createTime 创建记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取积分变化类型 1. 外卖下单获取积分，2.食堂下单获取积分，3. 管理员修改 ，4. 积分兑换消耗积分
     *
     * @return change_type - 积分变化类型 1. 外卖下单获取积分，2.食堂下单获取积分，3. 管理员修改 ，4. 积分兑换消耗积分
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     * 设置积分变化类型 1. 外卖下单获取积分，2.食堂下单获取积分，3. 管理员修改 ，4. 积分兑换消耗积分
     *
     * @param changeType 积分变化类型 1. 外卖下单获取积分，2.食堂下单获取积分，3. 管理员修改 ，4. 积分兑换消耗积分
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    /**
     * 获取积分改变数量
     *
     * @return change_count - 积分改变数量
     */
    public Integer getChangeCount() {
        return changeCount;
    }

    /**
     * 设置积分改变数量
     *
     * @param changeCount 积分改变数量
     */
    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
    }

    /**
     * 获取操作人员
     *
     * @return operate_man - 操作人员
     */
    public String getOperateMan() {
        return operateMan;
    }

    /**
     * 设置操作人员
     *
     * @param operateMan 操作人员
     */
    public void setOperateMan(String operateMan) {
        this.operateMan = operateMan == null ? null : operateMan.trim();
    }

    /**
     * 获取操作备注
     *
     * @return operate_note - 操作备注
     */
    public String getOperateNote() {
        return operateNote;
    }

    /**
     * 设置操作备注
     *
     * @param operateNote 操作备注
     */
    public void setOperateNote(String operateNote) {
        this.operateNote = operateNote == null ? null : operateNote.trim();
    }

    /**
     * 获取积分来源：0->购物奖励；1->管理员修改；2->购物消费
     *
     * @return source_type - 积分来源：0->购物奖励；1->管理员修改；2->购物消费
     */
    public Integer getSourceType() {
        return sourceType;
    }

    /**
     * 设置积分来源：0->购物奖励；1->管理员修改；2->购物消费
     *
     * @param sourceType 积分来源：0->购物奖励；1->管理员修改；2->购物消费
     */
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
}