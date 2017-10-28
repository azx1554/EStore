package com.yang.backgroud.sys.entity;

import com.yang.backgroud.common.persistence.BaseDataEntity;

/**
 * Created by Yang on 2017/10/28 0028.
 */
public class SysUser extends BaseDataEntity<SysUser> {
    private String nick;
    private String password;
    private String username;
    private String mobile;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ",nick:" + nick + ",password:" + password + ",username:" + username + ",mobile:" + mobile + "}";
    }
}