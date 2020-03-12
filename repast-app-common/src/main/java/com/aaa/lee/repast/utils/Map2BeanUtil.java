package com.aaa.lee.repast.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/12 11:15
 * @Description
 **/
public class Map2BeanUtil {

    private final static Objenesis OBJENESIS = new ObjenesisStd(true);

    private final static StringBuilder STRING_BUILDER = new StringBuilder();

    // 因为Map转换为java Bean--->需要把Map的数据转换为字节--->再把这些字节重组成一个java Bean
    // 这个转换的过程就必须要求性能(需要上缓存)
    private final static ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP =
            new ConcurrentHashMap<Class, MethodAccess>(16);

    /**
     * @author Seven Lee
     * @description
     *      Map转换Java Bean
     * @param [map, clazz]
     * @date 2020/3/12
     * @return T
     * @throws
    **/
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        // 每一个实体对象都会有一个Class对象
        // T instance = clazz.newInstance();// 通过java自带的--->有可能是一个null对象(空指针异常)
        // 为了提高性能，就不再使用java自带的反射了，使用jar包形式性能会更高，而且防止空指针
        T instance = OBJENESIS.newInstance(clazz);
        // 2.从缓存池中取方法，Map.put("name", "zhangsan")--->每一次put name的之后，先要判断是否存在
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        if(null == methodAccess) {
            methodAccess = MethodAccess.get(clazz);
            CONCURRENT_HASH_MAP.putIfAbsent(clazz, methodAccess);
        }

        // 3.遍历转换
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            /**
             * java Bean(User.java):
             *      private String name;
             *      private String password;
             *      private String age;
             *
             *      public String getName() {
             *
             *      }
             *
             *      public void setName(String name) {
             *
             *      }
             */
            String setMethodName = getSetMethodName(entry.getKey());
            int index = methodAccess.getIndex(setMethodName, entry.getValue().getClass());
            // 通过反射来获取对象(属性，set方法了，set方法的内容也有了)
            methodAccess.invoke(instance, index, entry.getValue());
        }
        return instance;
    }


    /**
     * @author Seven Lee
     * @description
     *      通过字段获取set方法
     * @param [filedName]
     * @date 2020/3/12
     * @return java.lang.String
     * @throws
    **/
    private static String getSetMethodName(String filedName) {
        STRING_BUILDER.setLength(0);
        // name -->> setName
        return STRING_BUILDER.append("set").append(firstToUpperCase(filedName)).toString();
    }

    /**
     * @author Seven Lee
     * @description
     *      定义首字母大写的转换方法
     * @param [str]
     * @date 2020/3/12
     * @return java.lang.String
     * @throws
    **/
    private static String firstToUpperCase(String str) {
        // setName();-->set N要大写(并不确定在Map中N是否大写--->所以统一转换)
        return str.substring(0,1).toUpperCase() + str.substring(1, str.length());
    }

}
