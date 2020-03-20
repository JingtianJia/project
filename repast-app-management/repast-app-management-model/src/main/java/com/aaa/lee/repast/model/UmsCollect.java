package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ums_collect")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class UmsCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 0已收藏可用商品，1已收藏可用店铺，2不可用
     */
    private Integer status;

    /**
     * 储存时间
     */
    @Column(name = "collect_start_time")
    private Date collectStartTime;

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
     * @return member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return product_id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取0已收藏可用商品，1已收藏可用店铺，2不可用
     *
     * @return status - 0已收藏可用商品，1已收藏可用店铺，2不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0已收藏可用商品，1已收藏可用店铺，2不可用
     *
     * @param status 0已收藏可用商品，1已收藏可用店铺，2不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取储存时间
     *
     * @return collect_start_time - 储存时间
     */
    public Date getCollectStartTime() {
        return collectStartTime;
    }

    /**
     * 设置储存时间
     *
     * @param collectStartTime 储存时间
     */
    public void setCollectStartTime(Date collectStartTime) {
        this.collectStartTime = collectStartTime;
    }
}