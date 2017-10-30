package com.yang.background.sys.service;

import com.yang.background.common.utils.Message;

/**
 * Created by Yang on 2017/10/29.
 */
public interface ISysService{
    /**
     * 系统用户登录
     * @param userName
     * @param passWord
     * @return SysUser
     */
    public Message login(String userName, String passWord);
}
