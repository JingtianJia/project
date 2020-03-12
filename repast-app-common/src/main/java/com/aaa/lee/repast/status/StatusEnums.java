package com.aaa.lee.repast.status;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:37
 * @Description
 **/
public enum StatusEnums {

    SUCCESS("1", "操作成功"),
    FAILED("2", "操作失败"),
    ENOUGH("3", "库存充足"),
    NOT_ENOUGH("4", "库存不足"),
    CLEAR_CART_SUCCESS("5", "清空购物车成功"),
    DELETE_OPERATION("6", "删除操作"),
    UPDATE_OPERATION("7", "修改操作"),
    INSERT_OPERATION("8", "添加操作");

    StatusEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
