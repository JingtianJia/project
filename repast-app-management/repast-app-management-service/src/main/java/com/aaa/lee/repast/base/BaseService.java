package com.aaa.lee.repast.base;

import com.aaa.lee.repast.utils.Map2BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
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
