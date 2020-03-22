package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sms_coupon_product_category_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class CouponProductCategoryRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "product_category_id")
    private Long productCategoryId;

    /**
     * 产品分类名称
     */
    @Column(name = "product_category_name")
    private String productCategoryName;

    /**
     * 父分类名称
     */
    @Column(name = "parent_category_name")
    private String parentCategoryName;
}