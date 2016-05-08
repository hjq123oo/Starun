package com.starun.www.starun.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yearsj on 2016/4/28.
 */
public class ConnectUtil {

    /**服务器的地址**/
    private static final String IPADDRESS = "http://192.168.191.1//";
    /**响应成功代码**/
    private static final int SUCCESS = 200;

    /**
     * 连接服务器并发送请求
     * @param urlOperation,message
     * @return 返回的请求数据
     */
    public static String getResponse(String urlOperation, String message){
        String urlString = IPADDRESS+urlOperation;
        URL url = null;
        HttpURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String result = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3 * 1000);
            outputStream = conn.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
            if(SUCCESS == conn.getResponseCode()){
                inputStream = conn.getInputStream();
                result = StringUtil.readString(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
