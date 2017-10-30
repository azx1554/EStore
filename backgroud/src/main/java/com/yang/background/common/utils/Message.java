package com.yang.background.common.utils;

import java.util.Map;

public class Message {
	private int code;      //返回码
	private String error;  //返回信息
	private String info;   //附加信息
	private Map<String, Object> map; //返回体

	public Message() {
		this.code = MessageType.M10000.getCode();
		this.error = MessageType.M10000.getMsg();
	}

	public Message(MessageType type) {
		this.code = type.getCode();
		this.error = type.getMsg();
	}

	public Message(int code, String error) {
		this.code = code;
		this.error = error;
	}

	public Message(int code, String error, Map<String, Object> map) {
		this(code, error);
		this.map = map;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
