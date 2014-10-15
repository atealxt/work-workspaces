package com.multicache4j.remote.factory;

import org.apache.log4j.Logger;

import com.chenlb.ttserver.TTServerClient;
import com.multicache4j.config.model.RemoteSourceItem;
import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.channel.TTChannelImpl;

public class TTChannelFactoryImpl implements ChannelFactory {
    private static Logger log = Logger.getLogger(TTChannelFactoryImpl.class);
    private RemoteSourceItem remoteSourceItem;

    public TTChannelFactoryImpl(RemoteSourceItem remoteSourceItem) {
        this.remoteSourceItem = remoteSourceItem;
    }

    public Channel createChannel() throws Exception {
        if (log.isDebugEnabled()) {
        	log.debug("begin create TTChannel...");
        }
        
        TTServerClient ttsc = new TTServerClient(remoteSourceItem.getHost(), remoteSourceItem.getPort());
        Channel channel = new TTChannelImpl(ttsc, remoteSourceItem);
        return channel;
    }
}
