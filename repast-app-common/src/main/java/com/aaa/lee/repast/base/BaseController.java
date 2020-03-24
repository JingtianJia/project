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
     * 登陆成功，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData loginSuccess(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
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
     * 登录成功，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData loginSuccess(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
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
    /* ====================添加操作==================*/

    /**
     * 添加成功，使用系统消息
     * @return
     */
    protected ResultData addSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 添加成功，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData addSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 添加成功，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData addSuccess(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 添加成功，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData addSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 添加成功，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData addSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 添加成功，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData addSuccess(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 添加失败，返回系统消息
     * @return
     */
    protected ResultData addFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }
    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 添加失败，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData addFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 添加失败，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData addFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 添加失败，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData addFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 添加失败，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData addFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 添加失败，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData addFailed(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

    /*================删除操作============*/

    /**
     * 删除成功，使用系统消息
     * @return
     */
    protected ResultData delSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 删除成功，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData delSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 删除成功，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData delSuccess(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 删除成功，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData delSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 删除成功，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData delSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 删除成功，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData delSuccess(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 删除失败，返回系统消息
     * @return
     */
    protected ResultData delFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }
    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 删除失败，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData delFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 删除失败，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData delFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 删除失败，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData delFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 删除失败，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData delFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 删除失败，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData delFailed(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

    /*================修改操作============*/

    /**
     * 修改成功，使用系统消息
     * @return
     */
    protected ResultData uptSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 修改成功，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData uptSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 修改成功，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData uptSuccess(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 修改成功，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData uptSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 修改成功，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData uptSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 修改成功，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData uptSuccess(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 修改失败，返回系统消息
     * @return
     */
    protected ResultData uptFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }
    /**
     * 有些情况使用系统的消息太过简单，所以需要自定义(架构是程序员使用越简单，结构就越复杂)
     * 修改失败，自定义返回消息
     * @param msg
     * @return
     */
    protected ResultData uptFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 修改失败，自定义解释说明
     * @param msg
     * @param detail
     * @return
     */
    protected ResultData uptFailed(String msg,String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 修改失败，返回系统消息，自定义返回值
     * @param data
     * @return
     */
    protected ResultData uptFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 修改失败，自定义消息和返回值
     * @param msg
     * @param data
     * @return
     */
    protected ResultData uptFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 修改失败，返回自定义消息，解释说明和自定义数据
     * @param msg
     * @param detail
     * @param data
     * @return
     */
    protected ResultData uptFailed(String msg,String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

}
