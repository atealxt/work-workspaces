package com.m2.exception;

public class M2Exception extends Exception {


	public M2Exception() {
		super();
	}

	public M2Exception(String message) {
		super(message);
	}

	public M2Exception(String message, Throwable throwable) {
		super(message, throwable);
	}

	public M2Exception(Throwable throwable) {
		super(throwable);
	}

}
