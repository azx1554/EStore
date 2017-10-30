package com.yang.background.sys.service.impl;

import com.yang.background.common.impl.BaseService;
import com.yang.background.common.utils.MD5Util;
import com.yang.background.common.utils.Message;
import com.yang.background.common.utils.MessageType;
import com.yang.background.sys.dao.ISysUserDAO;
import com.yang.background.sys.entity.SysUser;
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

    @Override
    public Message login(String username, String password) {
        //String str = MD5Util.MD5(passWord);
        SysUser sysUser = new SysUser();
        logger.info("用户名："+username+" 密码："+password);
        sysUser.setUsername(username);
        try {
            sysUser = sysUserDAO.findSelective(sysUser);
            if (sysUser==null){
                return new Message(MessageType.M10011);
            }
            if (!sysUser.getPassword().equals(password)){
                return new Message(MessageType.M10012);
            }
            return new Message();
        }catch (Exception e){
            logger.info("SysServiceImpl login method error:", e);
            return new Message(MessageType.FILA);
        }
    }
}
