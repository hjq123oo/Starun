package com.starun.www.starun.ConnectTest;

import android.test.AndroidTestCase;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;

import java.util.Map;

/**
 * Created by yearsj on 2016/5/12.
 */
public class ConnecUtilTest extends AndroidTestCase {

    public void testResponse() throws Exception {
        User user = new User();
        user.setPassword("111");
        user.setUsername("111");

        String urlOperation = new String("login");

        String userMessage = "name="+user.getUsername()+"&pw="+user.getPassword();
        String response = ConnectUtil.getResponse(urlOperation, userMessage);


    }


}
