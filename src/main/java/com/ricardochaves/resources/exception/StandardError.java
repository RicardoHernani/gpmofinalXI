package com.ricardochaves.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long timeStamp;
	private Integer status;
	private String msg;
	private String errorMessage;
	private String path;
	
	
	public StandardError() {
	}

	public StandardError(Integer status, String msg, Long timeStamp, String errorMessage, String path) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
		this.errorMessage = errorMessage;
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}