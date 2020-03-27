package com.aaa.lee.repast.base;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/12 11:04
 * @Description
 **/
public abstract class CommonController<T> extends BaseController {

    /**
     * @author Seven Lee
     * @description
     *      定义钩子函数:
     *          在新增操作之前先执行的内容
     * @param [map]
     * @date 2020/3/12
     * @return void
     * @throws
    **/
    protected void beforeAdd(Map map) {
        // TODO AddMethod Before to do
    }

    /**
     * @author Seven Lee
     * @description
     *      定义钩子函数:
     *          在新增操作之后执行的内容
     * @param [map]
     * @date 2020/3/12
     * @return void
     * @throws 
    **/
    protected void afterAdd(Map map) {
        // TODO AddMethod After to do
    }

    public abstract BaseService<T> getBaseService();

    /**
     * @author Seven Lee
     * @description
     *      新增操作
     * @param [map]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    public ResultData add(@RequestBody Map map) {
        beforeAdd(map);
        // insert into user(id, username...) values(?,?,?....)
        // 如果正好从前端传递过来的json格式--->Map的key与你数据库中的表字段一模一样
        // 那么你可以直接使用Map进行存储(<select id parameterType="map" resultMap="xxx">)
        // 如果对应不上，需要转换为实体类
        // 1.通过Map转换实体类型
        T instance = getBaseService().newInstance(map);
        Integer insertResult = getBaseService().add(instance);
        if(insertResult > 0) {
            afterAdd(map);
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    /**
     * @author Seven Lee
     * @description
     *      删除操作
     * @param [map]
     * @date 2020/3/13
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
     **/
    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer deleteResult = getBaseService().delete(instance);
        if(deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * 更新方法
     * @param map
     * @return
     */
    public ResultData update(@RequestBody Map map){
        T updates = getBaseService().newInstance(map);
        Integer update= getBaseService().update(updates);
        if(update > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * 查询
     * @param map
     * @return
     */
    public ResultData selcet(@RequestBody Map map){
        T select = getBaseService().newInstance(map);
        List<T> ts = getBaseService().queryList(select);
        if(ts.size()>0){
            return super.operationSuccess(ts);
        }
        return super.operationSuccess();
    }

    /**
     * 查询单个
     * @param map
     * @return
     */
    public ResultData selcetOne(@RequestBody Map map){
        T select = getBaseService().newInstance(map);
        T ts = getBaseService().queryOne(select);
        if(null!=ts){
            return super.operationSuccess(ts);
        }
        return super.operationSuccess();
    }

    /**
     * @author Seven Lee
     * @description
     *      批量删除
     * @param [ids]
     * @date 2020/3/13
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
     **/
    public ResultData batchDelete(@RequestParam("ids") Integer[] ids) {
        Integer deleteResult = getBaseService().deleteBatch(ids);
        if(deleteResult > 0) {
            return operationSuccess();
        }
        return operationFailed();
    }
}
