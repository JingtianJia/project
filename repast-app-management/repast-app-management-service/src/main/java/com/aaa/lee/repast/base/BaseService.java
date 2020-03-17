package com.aaa.lee.repast.base;

import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.utils.Map2BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/9 20:50
 * @Description
 *      一个service解决了单表业务逻辑(不再需要通过一个实体类对应一个service-->所有实体类对应一个service)
 **/
public abstract class BaseService<T> {

    private Class<T> cache = null; // 定义一个缓存--->来存储泛型

    @Autowired
    private Mapper<T> mapper;

    protected Mapper getMapper() {
        return mapper;
    }

    /**
     * @author Seven Lee
     * @description
     *      新增操作
     * @param [t]
     * @date 2020/3/12
     * @return java.lang.Integer
     * @throws
     **/
    public Integer add(T t) {
        return getMapper().insertSelective(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      更新操作
     * @param [t]
     * @date 2020/3/12
     * @return java.lang.Integer
     * @throws
     **/
    public Integer update(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      批量更新
     *      // TODO 后期维护
     * @param [t, ids, properties]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer updateBatch(T t, List<Object> ids, Object properties) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", ids)).build();
        return getMapper().updateByExample(t, example);
    }


    /**
     * @author Seven Lee
     * @description
     *      通过主键进行删除
     * @param [key]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer delete(T t) {
        return getMapper().deleteByPrimaryKey(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      通过主键集合进行批量删除
     *          数据库的主键名必须叫id
     * @param [ids]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deleteBatch(Integer[] ids) {
        /**
         * List:是需要传入的参数
         *      <delete id="" paratemeterType=list
         *      where 主键 in (1,2,3,4,5,6,7,8...)
         */
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return getMapper().deleteByExample(example);
    }

    /**
     * @author Seven Lee
     * @description
     *      查询一条数据
     * @param [t]
     * @date 2020/3/13
     * @return T
     * @throws
     **/
    public T queryOne(T t) {
        return mapper.selectOne(t);
    }

    /**
     * 需求是查询所有的权限名称
     * admin
     * role
     * permission
     * select p.permission_name, p.permission_chinese_name from admin a
     *  inner join admin_role ar on ar.admin_id = a.id
     *  ...
     *  inner join permission p on ...
     */
    /**
     * @author Seven Lee
     * @description
     *      查询一条数据(获取指定的字段)
     * @param [where, orderByField, fields]
     * @date 2020/3/13
     * @return T
     * @throws
     **/
    public T queryOneByField(Sqls where, String orderByField, String... fields) {
        return queryByFieldBase(null, where, orderByField, fields).get(0);
    }

    /**
     * @author Seven Lee
     * @description
     *      通过查询字段获取集合信息
     * @param [where, orderByField, fields]
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    public List<T> queryListByFields(Sqls where, String orderByField, String... fields) {
        return queryByFieldBase(null, where, orderByField, fields);
    }

    /**
     * @author Seven Lee
     * @description
     *      分页的封装
     * @param [pageInfos, where, orderByField, fields]
     * @date 2020/3/13
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     **/
    public PageInfo<T> queryListByPageAndField(PageInfos pageInfos, Sqls where, String orderByField,
                                               String... fields) {
        if(null == pageInfos.getPageNum()) {
            // 说明就是第一次从菜单点击，并不是直接开始了分页
            pageInfos.setPageNum(0);
        }
        return new PageInfo<T>(queryByFieldBase(pageInfos, where, orderByField, fields));
    }

    /**
     * @author Seven Lee
     * @description
     *      查询列表信息(带条件查询)
     *      t缺省:说明t-->null
     *      那么就查询所有数据
     * @param [t]
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    public List<T> queryList(T t) {
        return mapper.select(t);
    }

    /**
     * @author Seven Lee
     * @description
     *      带条件的分页查询
     * @param [t, pageInfos]
     * @date 2020/3/13
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     **/
    public PageInfo<T> queryListByPage(T t, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<T> list = mapper.select(t);
        pageInfo = new PageInfo<T>(list);
        return pageInfo;
    }

    /**
     * @author Seven Lee
     * @description
     *      查询的基础方法(通用)
     *      selct one
     *      select all
     *      select xx,xxx,xx
     *      select limit
     *      select order by
     *      pageInfos:
     *          pageNum:页码数
     *          pageSize:每一页显示的条数
     *          where:条件
     *          orderByField:排序字段
     *          filed:所需要查询的字段
     *
     *      参考Mybatis源码
     *
     *      <update id  parameterType>
     *          update set where 1 = 1
     *          <if test="username != ''">
     *              username = #{username},
     *          </if>
     *          <if test="password != ''">
     *              password = #{password}
     *          </if>
     *      </update>
     *
     * @param []
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    private List<T> queryByFieldBase(PageInfos pageInfos, Sqls where, String orderByField, String... fields) {
        Example.Builder builder = null;
        // 在判断形参可变长度数组的时候千万千万不要只判断是否为null--->有可能fields已经初始化过了，但是里面没有数据
        if(null == fields || fields.length == 0) {
            // 说明并没有传递字段，默认查询所有数据
            builder = Example.builder(getTypeArgument());
        } else {
            // 说明传递的有所需要查询的字段
            builder = Example.builder(getTypeArgument()).select(fields);
        }
        if(null != where) {
            builder = builder.where(where);
        }
        if(null != orderByField) {// 默认使用倒序
            builder = builder.orderByDesc(orderByField);
        }
        Example example = builder.build();
        // 实现通用分页
        /**
         * pageNum第一次进入的时候有可能为null
         *  为了实现通用，就不管是否是第一次点击进来
         */
        if(null != pageInfos.getPageNum() && null != pageInfos.getPageSize()) {
            PageHelper.startPage(pageInfos.getPageNum(), pageInfos.getPageSize()); // PageHelper的分页插件
        }
        return getMapper().selectByExample(example);
    }

    /**
     * @author Seven Lee
     * @description
     *      通过Map类型转换为Java实体对象
     * @param [map]
     * @date 2020/3/12
     * @return T
     * @throws
     **/
    public T newInstance(Map map) {
        // 自定义高性能反射工具类(有些是有泛型的(List,Map,Set,BaseService<T>....))
        return (T) Map2BeanUtil.map2Bean(map, getTypeArgument());
    }


    /**
     * @author Seven Lee
     * @description
     *      获取泛型
     * @param []
     * @date 2020/3/12
     * @return java.lang.Class<T>
     * @throws
     **/
    public Class<T> getTypeArgument() {
        if(null == cache) {
            cache = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);
        }
        return cache;
    }
}
