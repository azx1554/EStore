package com.yang.background.sys.controller;

import com.yang.background.common.controller.BaseController;
import com.yang.background.common.utils.Message;
import com.yang.background.sys.entity.SysUser;
import com.yang.background.sys.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Yang on 2017/10/28 0028.
 */
@Controller
@RequestMapping("sys")
public class SysController extends BaseController {
    @Autowired
    private ISysService sysService;

    private final String PAGE_MAP = "pageMap";
    private final String MAIN_PAGE = "main";

    @RequestMapping("main.do")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return MAIN_PAGE;
    }

    @ResponseBody
    @RequestMapping("login.do")
    public Message login(HttpServletRequest request, HttpServletResponse response, SysUser user) {
        return sysService.login(user.getUsername(), user.getPassword());
    }

    @RequestMapping("toPage.do")
    public String toPage(HttpServletRequest request, HttpServletResponse response) {
        String mid = request.getParameter("mid");
        String page = ((Map<String, String>) (request.getSession().getAttribute(PAGE_MAP))).get(mid);
        if (page == null)
            page = "index";
        return page;
    }

}
