package com.multicache4j.remote.factory;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.multicache4j.config.model.RemoteSourceItem;
import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.channel.JedisChannelImpl;

public class JedisChannelFactoryImpl implements ChannelFactory {
    private static Logger log = Logger.getLogger(JedisChannelFactoryImpl.class);
    private RemoteSourceItem remoteSourceItem;

    public JedisChannelFactoryImpl(RemoteSourceItem remoteSourceItem) {
        this.remoteSourceItem = remoteSourceItem;
    }

    public Channel createChannel() throws Exception {
        if (log.isDebugEnabled()) {
        	log.debug("begin create JedisChannel...");
        }
        Jedis jedis = new Jedis(remoteSourceItem.getHost(), remoteSourceItem.getPort(), remoteSourceItem.getTimeout());
        Channel channel = new JedisChannelImpl(jedis, remoteSourceItem);
        channel.isAlive();
        return channel;
    }
}
