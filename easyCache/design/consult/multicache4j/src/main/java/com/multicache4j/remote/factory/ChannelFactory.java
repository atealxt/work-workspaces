package com.multicache4j.remote.factory;

import com.multicache4j.remote.channel.Channel;

/**
 * @author liuzhongbing
 * Channel对象的创建工厂
 */
public interface ChannelFactory {
	public Channel createChannel() throws Exception;
}
