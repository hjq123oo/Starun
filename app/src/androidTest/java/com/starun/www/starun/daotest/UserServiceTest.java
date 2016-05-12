package com.starun.www.starun.daotest;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.service.UserService;

import java.util.Date;
import java.util.Map;

/**
 * Created by yearsj on 2016/4/28.
 */
public class UserServiceTest extends AndroidTestCase {

    public void testLogin(){
        UserService userService = new UserService();
        User user = new User();
        //user.setBirthday(new Date());
        //user.setHeadImgPath(null);
        user.setNickname("隔壁小小黑");
        user.setPassword("111");
        //user.setSex(1);
        //user.setStature(120);
        //user.setUser_id(1);
        user.setUsername("111");
        String response = userService.login(user);
        Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
        });
    }

//    public void testRegister(){
//        UserService userService = new UserService();
//        User user = new User();
//        //user.setBirthday(new Date());
//        //user.setHeadImgPath(null);
//        user.setNickname("隔壁小小黑");
//        user.setPassword("111");
//        //user.setSex(1);
//        //user.setStature(120);
//        //user.setUser_id(1);
//        user.setUsername("yearsj");
//        userService.register(user);
//    }
}
