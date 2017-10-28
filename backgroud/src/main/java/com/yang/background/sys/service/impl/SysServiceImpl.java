package com.yang.background.sys.service.impl;

import com.yang.background.common.impl.BaseService;
import com.yang.background.sys.dao.ISysUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yang.background.sys.service.ISysService;

/**
 * Created by Yang on 2017/10/29.
 */
@Service("sysService")
public class SysServiceImpl extends BaseService implements ISysService {

    @Autowired
    private ISysUserDAO sysUserDAO;

    public void login(){
        //sysUserDAO.getCount();
    }
}
