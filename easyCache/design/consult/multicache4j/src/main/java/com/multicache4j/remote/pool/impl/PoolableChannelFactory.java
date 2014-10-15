package com.multicache4j.remote.pool.impl;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;

import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.factory.ChannelFactory;

public class PoolableChannelFactory implements PoolableObjectFactory {
    //private static Logger log = Logger.getLogger(PoolableChannelFactory.class);
    protected ChannelFactory channelFactory = null;
    protected ObjectPool pool = null;

    public PoolableChannelFactory(ChannelFactory channelFactory, ObjectPool pool) {
        this.pool = pool;
        this.pool.setFactory(this);
        this.channelFactory = channelFactory;
    }

    // 产生新的对象,对象能放回池中
    public Object makeObject() throws Exception {
		Channel channel = this.channelFactory.createChannel();
        if (channel != null) {
            channel = new PoolableChannel(channel, this.pool);
        }
        return channel;
    }
    
    // 销毁被validateObject判定为已失效的对象。 
    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof PoolableChannel) {
        	PoolableChannel poolableObject = (PoolableChannel) obj;
            poolableObject.trueClose();
        }
        //log.debug("xxx: 借出=" + pool.getNumActive() + "; 休眠=" + pool.getNumIdle());
    }

    // 校验一个具体的对象是否仍然有效，已失效的对象会被自动交给destroyObject方法销毁
    public boolean validateObject(Object obj) {
        if (obj instanceof PoolableChannel) {
        	PoolableChannel poolableObject = (PoolableChannel) obj;
            return poolableObject.isHealth();
        } else {
            return false;
        }
    }
    
	// 设置为使用状态
    public void activateObject(Object obj) throws Exception {
    }

    // 设置为空闲状态
    public void passivateObject(Object obj) throws Exception {
        if (obj instanceof PoolableChannel) {
        	PoolableChannel poolableObject = (PoolableChannel) obj;
            if (!poolableObject.isHealth()) {
                throw new Exception("PoolableChannel passivate fail.");
            }
        }
    }

}
