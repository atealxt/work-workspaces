package com.multicache4j.remote.pool.impl;

import org.apache.commons.pool.ObjectPool;

import com.multicache4j.config.model.RemoteSourceItem;
import com.multicache4j.remote.channel.Channel;

/**
 * @author liuzhongbing
 * 池化的Channel，从而能够保存到ObjectPool中
 * 它包装了Channel对象，以便捕捉各种异常，用于健康状况监测
 * 并有一个ObjectPool的引用，以便归还channel
 */
public class PoolableChannel implements Channel {

	private Channel channel;
	private ObjectPool pool;
	private boolean health = true;// 健康状态

	public PoolableChannel(Channel channel, ObjectPool pool) {
		this.pool = pool;
		this.channel = channel;
	}

	public void close() throws Exception {
		// check this status
		try {
			this.pool.returnObject(this);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public boolean isAlive() {
		boolean bResult = channel.isAlive();
		if (!bResult) {
			this.setHealth(false);
		}
		return bResult;
	}

	public void trueClose() throws Exception {
		channel.close();
	}

	public boolean isHealth() {
		return health;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}

	public String get(String key) throws Exception {
		try {
			return channel.get(key);
		} catch (Exception e) {
			this.setHealth(false);
			throw e;
		}
	}

	public void set(String key, String value) throws Exception {
		try {
			channel.set(key, value);
		} catch (Exception e) {
			this.setHealth(false);
			throw e;
		}
	}
	
	public RemoteSourceItem getRemoteSourceItem() {
		return channel.getRemoteSourceItem();
	}

	public String toString() {
		return channel.toString();
	}
}
