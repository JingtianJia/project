package com.aaa.lee.repast.base;

import static com.aaa.lee.repast.status.LoginStatus.*;
import static com.aaa.lee.repast.status.StatusEnums.*;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:42
 * @Description
 **/
public class BaseController {

    /**
     * @author Seven Lee
     * @description
     *      登录成功，使用系统消息
     * @param []
     * @date 2019/11/20
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    protected ResultData loginSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      登录成功，自定义返回消息
     * @param [msg]
     * @date 2019/11/20
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    protected ResultData loginSuccess(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      登录成功，使用系统消息，自定义返回值
     * @param [data]
     * @date 2019/11/20
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    protected ResultData loginSuccess(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      登录成功，自定义消息，自定义返回值
     * @param [msg, data]
     * @date 2019/11/20
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    protected ResultData loginSuccess(String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      登录失败，返回系统消息
     * @param []
     * @date 2019/11/20
     * @return com.aaa.lee.app.base.ResultData
     * @throws
     **/
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      登录失败，使用自定义消息
     * @param [msg]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    protected ResultData loginFailed(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作成功，返回系统消息
     * @param []
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws 
    **/
    protected ResultData operationSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作成功，返回自定义消息
     * @param [msg]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    protected ResultData operationSuccess(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作成功，使用系统消息，自定义返回值
     * @param [data]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    protected ResultData operationSuccess(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作成功，自定义消息，自定义返回值
     * @param [msg, data]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    protected ResultData operationSuccess(String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作失败，使用系统消息
     * @param []
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    protected ResultData operationFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }

    /**
     * @author Seven Lee
     * @description
     *      操作失败，使用自定义消息
     * @param [msg]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
     **/
    protected ResultData operationFailed(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    // TODO 该类未完成，自行完成剩余的方法

}
