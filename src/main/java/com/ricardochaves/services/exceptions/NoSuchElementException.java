package com.ricardochaves.services.exceptions;

public class NoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchElementException(String msg) {
		super(msg);
	}
	
	public NoSuchElementException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
