package com.multicache4j.remote.channel;

import com.multicache4j.config.model.RemoteSourceItem;

/**
 * @author liuzhongbing
 * 对象类顶级接口
 */
public interface Channel {
	
	public boolean isAlive();

	public void set(String key, String value) throws Exception;

	public String get(String key) throws Exception;

	public void close() throws Exception;
	
	public RemoteSourceItem getRemoteSourceItem();
}
