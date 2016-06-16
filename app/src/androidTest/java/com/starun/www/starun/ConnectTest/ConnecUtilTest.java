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
        Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>(){});
        String result = map.get("result");
        Log.d("response",response==null?"":response);
    }


    public void testAddFriend(){
        String message = "user_id1="+15+"&user_id2="+13;
        String response =  ConnectUtil.getResponse("addFriendInfo", message);


        Log.d("response",response==null?"":response);
    }


    public void testDisagreeFriend(){
        String message = "user_id1="+13+"&user_id2="+15;
        String response =  ConnectUtil.getResponse("disagreeFriend", message);

        Log.d("response",response==null?"":response);
    }

}
