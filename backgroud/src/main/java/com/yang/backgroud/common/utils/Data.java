package com.yang.backgroud.common.utils;

public class Data { 
	private String data;
	private String userId;
	private String resultCode;
	private String errorDesc;

	public Data(){
		
	}
	public Data(String data,String userId,String resultCode,String errorDesc){
		this.data = data;
		this.userId = userId;
		this.resultCode = resultCode;
		this.errorDesc = errorDesc;
	}
	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Data(String data) {
		this.data = data;
	}
}
