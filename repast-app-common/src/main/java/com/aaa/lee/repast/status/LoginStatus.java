package com.aaa.lee.repast.status;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:36
 * @Description
 **/
public enum  LoginStatus {

    LOGIN_SUCCESS("200", "登录成功"),
    LOGIN_FAILED("400", "登录失败"),
    USER_EXIST("201", "用户已经存在"),
    USER_NOT_EXIST("401", "用户不存在"),
    PASSWORD_WRONG("402", "密码错误"),
    LOGOUT_WRONG("405", "用户退出异常");

    LoginStatus(String code, String msg) {
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
