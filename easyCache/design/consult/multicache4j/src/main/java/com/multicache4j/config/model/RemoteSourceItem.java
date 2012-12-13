package com.multicache4j.config.model;

import java.io.Serializable;

public class RemoteSourceItem implements Serializable {

	private static final long serialVersionUID = -6548768493657025956L;
	
	// 连接池参数
	private String name; // 名字
	private String host; // 服务器名称
	private int port; // socket连接端口	
	private int timeout; // socet连接超时
	private int maxIdle; // 池中存在的连接数
	private int maxActive; // 池的最大激活数
	private int maxWait;// 从池冲查找一个对象试用的时间数	
	private String implementClass;//客户端实现类

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}	

	public String getImplementClass() {
		return implementClass;
	}

	public void setImplementClass(String implementClass) {
		this.implementClass = implementClass;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
}
