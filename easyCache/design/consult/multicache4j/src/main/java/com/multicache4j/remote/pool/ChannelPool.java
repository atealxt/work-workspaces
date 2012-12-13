package com.multicache4j.remote.pool;

import com.multicache4j.remote.channel.Channel;

public interface ChannelPool {
    public Channel getChannel() throws Exception;
}
