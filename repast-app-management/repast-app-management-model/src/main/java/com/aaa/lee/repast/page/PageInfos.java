package com.aaa.lee.repast.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:49
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageInfos<T> implements Serializable {

    /**
     * 当前页码数
     */
    private Integer pageNum;

    /**
     * 每一页显示的条数
     */
    private Integer pageSize;

    /**
     * 需要条件查询的分页数据
     */
    private T t;

}
