package com.easyjf.simpleblog.service;

public class LogicException extends RuntimeException {
	public LogicException() {
		this("逻辑错误！");
	}

	public LogicException(String msg) {
		super(msg);
	}
}
