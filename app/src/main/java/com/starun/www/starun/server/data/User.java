package com.starun.www.starun.server.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yearsj on 2016/4/28.
 */
public class User implements Serializable{
    private int user_id;      //用户id
    private String username;  //用户名
    private String password;  //用户密码
    private String nickname;  //用户昵称
    private double stature;  //用户身高
    private double weight;   //用户体重
    private Date birthday;    //用户生日
    private int sex;          //用户性别
    private String headImgPath;  //用户头像

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getStature() {
        return stature;
    }

    public void setStature(double stature) {
        this.stature = stature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }
}
