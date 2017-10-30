package com.yang.background.common.utils;

public enum MessageType {
	FILA(10001,"系统繁忙"),

	//10*** 基本功能
	M10000(10000,"操作成功"),
	M10001(10002,"操作失败"),

	//1001* 登录
	M10011(10011,"该用户不存在"),
	M10012(10012,"用户名和密码不匹配"),



	M99999(99999,"我就占个位置");

	MessageType(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

}
