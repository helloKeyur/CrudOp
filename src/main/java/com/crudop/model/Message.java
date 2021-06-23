package com.crudop.model;

public class Message {
	
	private String msg;
	private String status;
	private String cssClass;
	
	public Message(String msg, String status, String cssClass) {
		super();
		this.msg = msg;
		this.status = status;
		this.cssClass = cssClass;
	}

	public Message() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String toString() {
		return "Message [getMsg()=" + getMsg() + ", getStatus()=" + getStatus() + ", getCssClass()=" + getCssClass()
				+ "]";
	}
	
	
	
	
}
