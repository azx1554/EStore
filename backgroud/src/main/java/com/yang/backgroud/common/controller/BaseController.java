package com.yang.backgroud.common.controller;

import com.mg.platform.gridfs.api.ImageService;
import com.mg.platform.message.service.IMessageService;
import com.mg.platform.message.service.ISmsMessageService;
import com.mg.platform.utils.DateUtils;
import com.mg.platform.weixin.service.IWeiXinProxyService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.util.Date;

@Component
public abstract class BaseController {
	protected static final Logger logger = Logger.getLogger(BaseController.class);

	@Resource
	@Qualifier("messageService")
	protected IMessageService messageService;

	@Resource
	@Qualifier("smsMessageService")
	protected ISmsMessageService smsMessageService;

	@Resource
	@Qualifier("weiXinProxyService")
	protected IWeiXinProxyService weiXinProxyService;
	
	@Resource
	@Qualifier("imageService")
	protected ImageService imageService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	@ExceptionHandler({ UnauthorizedException.class, UnauthenticatedException.class })
	public String authenticationException() {
		return "noperms";
	}
}