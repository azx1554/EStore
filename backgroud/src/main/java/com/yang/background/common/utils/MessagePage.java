package com.yang.background.common.utils;

import java.util.List;

public class MessagePage {
	private int errorCode;
	private StatusType status = StatusType.SUCCESS;
	private String content = "操作成功!";
	private String detailMsg;
	private List<Msg> messages;
	public static final String RESULT_SUCCESS = "操作成功!";
	public static final String RESULT_FAIL = "操作失败!";
	public static final String ERROR_MSG = "系统异常，请联系管理员";

	public MessagePage() {
	}

	public MessagePage(StatusType status) {
		this.status = status;
		switch (status) {
		case SUCCESS:
			this.content = "操作成功!";
			this.errorCode = 10000;
			break;
		case FAIL:
		case ERROR:
			this.content = "操作失败!";
			this.errorCode = 10001;
		}
	}

	public MessagePage(StatusType status, String detailMsg) {
		this.status = status;
		this.detailMsg = detailMsg;
		switch (status) {
			case SUCCESS:
				this.content = "操作成功!";
				this.errorCode = 10000;
				break;
			case FAIL:
			case ERROR:
				this.content = "操作失败!";
				this.errorCode = 10001;
				break;
		}
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public StatusType getStatus() {
		return this.status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
		switch (status) {
		case SUCCESS:
			this.content = "操作成功!";
			this.errorCode = 10000;
			break;
		case FAIL:
		case ERROR:
			this.content = "操作失败!";
			this.errorCode = 10001;
		}
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetailMsg() {
		return this.detailMsg;
	}

	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}

	public List<Msg> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Msg> messages) {
		this.messages = messages;
	}

	public class Msg {
		private String content;
		private String status;

		public Msg() {
		}

		public String getStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getContent() {
			return this.content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public static enum StatusType {
		SUCCESS, FAIL, ERROR;
	}
}