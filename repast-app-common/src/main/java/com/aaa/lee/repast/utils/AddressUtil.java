package com.aaa.lee.repast.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/12 10:19
 * @Description
 *      通过ip地址获取用户的详细地理位置
 *      百度的API:
 *          http://api.map.baidu.com/location/ip?ak=您的AK&ip=您的IP&coor=bd09ll
 **/
public class AddressUtil {

    /**
     * @author Seven Lee
     * @description
     *      对外部提供的方法(通过这个方法可以获取地理位置信息)
     * @param [content, encodingString]
     * @date 2020/3/12
     * @return java.lang.String
     * @throws
    **/
    public static Map<String, Object> getAddresses(String content, String encodingString) {
        // 这里调用百度API
        String urlStr = "http://api.map.baidu.com/location/ip?ak=6P28Z5GDb4sUhPMgRx7bX8pyG2Vj6iXv&ip="+content+"&coor=bd09ll";
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
            System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return null;//无效IP，局域网测试
            }
            System.out.println(returnStr);
            // 所获取到的数据是一个json类型字符串--->因为Map和Json的格式是一样的
            Map addressMap = JSONUtil.toObject(returnStr, Map.class);
            System.out.println(addressMap.get("address"));
            String addressStr = (String)addressMap.get("address");
            // 在java中|是关键字，不能直接使用，需要进行转译
            String[] addressArray = addressStr.split("\\|");
            // 0:CN 1:河南 2:郑州...
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("country", addressArray[0]);
            resultMap.put("province", addressArray[1]);
            resultMap.put("city", addressArray[2]);
            return resultMap;
        }
        return null;
    }

    /**
     * @author Seven Lee
     * @description
     *      通过获取到用户的ip--->把请求发送给百度Api服务器端--->从服务器端接收响应的数据
     * @param [urlStr, content, encoding]
     * @date 2020/3/12
     * @return java.lang.String
     * @throws
    **/
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(8000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(8000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * @author Seven Lee
     * @description
     *      把字节转换为中文的工具方法
     * @param [theString]
     * @date 2020/3/12
     * @return java.lang.String
     * @throws
    **/
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                }
                else {
                    if (aChar == 't') {
                        aChar = '\t';
                    }
                    else if (aChar == 'r') {
                        aChar = '\r';
                    }
                    else if (aChar == 'n') {
                        aChar = '\n';
                    }
                    else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            }
            else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> addresses = AddressUtil.getAddresses("222.137.210.39", "UTF-8");
        System.out.println(addresses);
    }

}
