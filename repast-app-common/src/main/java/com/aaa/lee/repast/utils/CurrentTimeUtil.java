package com.aaa.lee.repast.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aaa.lee.repast.staticstatus.StaticCode.FORMAT_DATE;

/**
 * @Author 丁平达
 * @Date 2020/3/18 18:47
 */
public class CurrentTimeUtil {
    public  Date currentTime(){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat(FORMAT_DATE);
        String format = dateFormat.format(date);
        Date parse = null;
        try {
            parse = dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
