package com.multicache4j.remote.factory;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.multicache4j.config.model.RemoteSourceItem;
import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.channel.SpyChannelImpl;

public class SpyChannelFactoryImpl implements ChannelFactory {
    private static Logger log = Logger.getLogger(SpyChannelFactoryImpl.class);
    private RemoteSourceItem remoteSourceItem;

    public SpyChannelFactoryImpl(RemoteSourceItem remoteSourceItem) {
        this.remoteSourceItem = remoteSourceItem;
    }

    public Channel createChannel() throws Exception {
        if (log.isDebugEnabled()) {
        	log.debug("begin create SpyChannel...");
        }
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(remoteSourceItem.getHost(), remoteSourceItem.getPort()));
        Channel channel = new SpyChannelImpl(mcc, remoteSourceItem);
        return channel;
    }
}
