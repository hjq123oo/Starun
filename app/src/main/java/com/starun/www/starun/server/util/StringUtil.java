package com.starun.www.starun.server.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yearsj on 2016/4/28.
 */
public class StringUtil {

//    public static String readString(InputStream inputStream){
//        int count = 0;
//        byte[] bTemp = null;
//        if(null!=inputStream){
//            try {
//                int pcount = 0;
//                do {
//                    pcount = inputStream.available();
//                    count+=pcount;
//                }while(0 != pcount);
//                bTemp = new byte[count];
//                int readCount = 0; // 已经成功读取的字节的个数
//                while (readCount < count) {
//                    readCount += inputStream.read(bTemp, readCount, count - readCount);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(null!=bTemp){
//            try {
//                return URLEncoder.encode(new String(bTemp),"UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static byte[] readBytes(InputStream inStream){
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        byte[] data = null;
        try {
            while( (len = inStream.read(buffer)) !=-1 ){
                outStream.write(buffer, 0, len);
            }
            data = outStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != outStream){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static String readString(InputStream inputSream){
        byte[] temp = readBytes(inputSream);
        String result = null;
        try {
            result = new String(temp,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
