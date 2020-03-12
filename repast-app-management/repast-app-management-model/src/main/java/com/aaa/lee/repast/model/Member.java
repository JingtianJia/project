package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 使用jpa模式做的映射
 *      @Table:映射的就是数据库中的表
 *      @Id:所标识的是表中的主键
 *      @GeneratedValue(strategy = GenerationType.IDENTITY):是否自增主键
 *      @Column:标识的是数据库中的列名:
 *          如果数据库中所涉及的字段遵循java的命名规则，则不需要
 *          如果没有遵循java命名规则，遵循的是数据库命名规则(使用_连接的时候)
 *          这个时候必须用该注解，让属性和数据库中的列名进行映射
 *      @Size:定义数据库中字段的最大长度
 *          先在java中进行验证--->如果在项目中验证后没有问题，才会去连接数据库
 *      @NotNull:是一个非空字段
 *          也是在java中先验证
 *
 *    在设计数据库表的时候:
 *      1.创建时间
 *      2.更新时间
 *      3.冗余字段(为了当数据库中字段不足的时候，冗余字段就可以充当该字段使用)
 *
 */
@Table(name = "ums_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    @NotNull
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "member_level_id")
    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Integer status;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 所做城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    @Column(name = "personalized_signature")
    private String personalizedSignature;

    /**
     * 用户来源
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 成长值
     */
    private Integer growth;

    /**
     * 剩余抽奖次数
     */
    @Column(name = "luckey_count")
    private Integer luckeyCount;

    /**
     * 历史积分数量
     */
    @Column(name = "history_integration")
    private Integer historyIntegration;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 微信返回的open_id
     */
    @Column(name = "open_id")
    @Size(max = 255)
    @NotNull
    private String openId;

    /**
     * 微信返回的session_key
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 登录验证token
     */
    private String token;
}