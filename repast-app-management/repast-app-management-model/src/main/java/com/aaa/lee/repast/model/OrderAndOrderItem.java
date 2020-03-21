package com.aaa.lee.repast.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 丁平达
 * @Date 2020/3/18 17:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class OrderAndOrderItem implements Serializable {
    private Order order;
    private List<OrderItem> orderItem;
}
