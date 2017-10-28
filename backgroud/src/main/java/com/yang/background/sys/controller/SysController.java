package com.yang.background.sys.controller;

import com.yang.background.sys.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yang on 2017/10/28 0028.
 */
@Controller
@RequestMapping("sys")
public class SysController {


   @RequestMapping("login.do")
   public String login(HttpServletRequest request, HttpServletResponse response, SysUser user) {
        /*SysUser sysUser = sysService.login(user.getLoginName(), user.getPassWord());
        if (sysUser != null) {
            if (sysUser.getLoginFlag() == 0) {
                SecurityUtils.getSubject().login(new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassWord()));
                List<SysMenu> menus = UserUtils.getMenuList();
                List<SysRole> roles = UserUtils.getRoleList();
                List<SysMenu> models = SysUtils.convertModel(menus);//可以显示的模块
                Map<String, String> pageMap = SysUtils.convertPageMap(menus);//可以显示的菜单
                Map<Long, SysMenu> modelMap = SysUtils.convertModelMap(menus);
                Map<Long, List<SysMenu>> menusMap = SysUtils.convertModelMenusMap(menus);
                if ((models != null) && (models.size() > 0)) {
                    request.getSession().setAttribute(MODEL, models.get(0));
                    request.getSession().setAttribute(MENUS, menusMap.get(models.get(0).getId()));
                }
                request.getSession().setAttribute(MODELS, models);
                request.getSession().setAttribute(USER_INFO, sysUser);
                request.getSession().setAttribute(ROLES, roles);
                request.getSession().setAttribute(MODEL_MAP, modelMap);
                request.getSession().setAttribute(MENUS_MAP, menusMap);
                request.getSession().setAttribute(PAGE_MAP, pageMap);
                // 更新用户登录信息
                SysUser loginUser = new SysUser();
                loginUser.setId(sysUser.getId());
                loginUser.setLoginIp(getRemoteAddr(request));
                loginUser.setLoginTime(new Date());
                sysService.updateUserLoginInfo(loginUser);
                return new MessagePage();
            } else {
                return new MessagePage(StatusType.FAIL, BAN_INFO);
            }
        } else {
            return new MessagePage(StatusType.FAIL, ERROR_INFO);
        }*/
        return "../index";
   }
}
