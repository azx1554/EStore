package com.yang.background.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:字符串等操作类
 * @author: lizhu
 */
public class StringUtil {
	
	
	/**
	 * 判断字符串是否是null并且空串
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj){
		return null != obj && !obj.toString().equals("");
	}
	
	/**
	 * 判断字符串是否是null并且空串
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj){
		return null == obj || obj.toString().equals("");
	}
	
	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		email = email.trim();
		if (email.indexOf(' ') != -1)
			return false;
		int idx = email.indexOf('@');
		if (idx == -1 || idx == 0 || (idx + 1) == email.length())
			return false;
		if (email.indexOf('@', idx + 1) != -1)
			return false;
		if (email.indexOf('.') == -1)
			return false;
		return true;
	}

	/**
	 * 判断某个值是否为空值
	 */
	public static boolean isEmpty(String Value) {
		return (Value == null || Value.trim().equals(""));
	}
	/**
	 * 获得用户登录sessionId
	 * @return string
	 */
	public static String getUserSessionId(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session!=null){
			return session.getId();
		}else{
			return "";
		} 
	}
		
}
