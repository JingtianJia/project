package com.aaa.lee.repast.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:43
 * @Description
 **/
@Data
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}
