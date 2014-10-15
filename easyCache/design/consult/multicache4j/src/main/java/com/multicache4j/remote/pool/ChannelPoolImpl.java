package com.multicache4j.remote.pool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import com.multicache4j.remote.channel.Channel;
import com.multicache4j.remote.factory.ChannelFactory;
import com.multicache4j.remote.pool.impl.PoolableChannelFactory;
import com.multicache4j.config.model.RemoteSourceItem;

public class ChannelPoolImpl implements ChannelPool {
    private static Logger log = Logger.getLogger(ChannelPoolImpl.class);
    private RemoteSourceItem remoteSourceItem;

    // 池对象
    private boolean isCreatePool = false;
    private GenericObjectPool pool;

    public Channel getChannel() throws Exception {
    	log.debug("开始获取一个连接...");
        if (!this.isCreatePool) {
            createPool();
        }
        Channel channel = (Channel) pool.borrowObject();
        log.debug("获得一个连接" + remoteSourceItem.getName() + ": 借出=" + pool.getNumActive() + "; 休眠=" + pool.getNumIdle() + "; channel=" + channel);
        return channel;
    }

    // 创建池对象
    private synchronized void createPool() throws Exception {
        if (this.isCreatePool)
            return;
    	log.debug("开始创建池...");
        pool = new GenericObjectPool();
        
        // 对象数目
        pool.setMaxActive(remoteSourceItem.getMaxActive());//能从池中借出对象的最大数目
        pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);//超出最大数目时，BLOCK等待，GROW新建(maxActive失效)，FAIL抛异常
        pool.setMaxIdle(remoteSourceItem.getMaxIdle());//对象池最多空闲对象数
        pool.setMinIdle(0);//对象池最少空闲对象数，设置为相同可减少释放重建开销，0在空闲时全释放
        
        // 时间设置
        pool.setMaxWait(remoteSourceItem.getMaxWait());//对象池空时，最多等待毫秒数，超时抛异常，-1则无限等待
        
        // 检查选项
        pool.setTestOnBorrow(true);//借出时是否测试
        pool.setTestOnReturn(false);//归还时是否测试
        
        // 对象清理
        pool.setTimeBetweenEvictionRunsMillis(-1);//后台对象清理间隔ms，-1不会清理
        pool.setTestWhileIdle(false);//后台清理时是否对没过期对象进行测试，不能通过的对象将被回收
        pool.setNumTestsPerEvictionRun(3);//后台对象清理时每次检查几个对象，-n则为对象总数*1/n取整
        pool.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);//后台对象清理时回收休眠时间超过了多少毫秒的对象，-1对休眠时间没有约束

        // 创建Channel工厂类
        ChannelFactory channelFactory = (ChannelFactory)Class
        	.forName(remoteSourceItem.getImplementClass())
        	.getConstructor(RemoteSourceItem.class)
        	.newInstance(remoteSourceItem);
        
        // 创建池化工厂类
        PoolableChannelFactory channellPoolableObjectFactory = 
        	new PoolableChannelFactory(channelFactory, this.pool);
        
        // 验证
        validateChannellFactory(channellPoolableObjectFactory);
        
        this.isCreatePool = true;
    	log.debug("结束创建池...");
    }

    private static void validateChannellFactory(PoolableChannelFactory factory) throws Exception {
        Channel channel = null;
        try {
        	channel = (Channel) factory.makeObject();
            factory.activateObject(channel);
            factory.validateObject(channel);
            factory.passivateObject(channel);
        } finally {
            factory.destroyObject(channel);
        }
    }

    public synchronized void close() throws Exception {        
        GenericObjectPool oldpool = pool;
        pool = null;
        try {
            if (oldpool != null) {
                oldpool.close();
            }
        } catch (Exception e) {
            throw new Exception("Cannot close jedis connection pool", e);
        }
        log.info("close pool completed");
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("JedisChannelSourceImpl[");
        buffer.append("name=").append(remoteSourceItem.getName());
        buffer.append(", host=").append(remoteSourceItem.getHost());
        buffer.append(", port=").append(remoteSourceItem.getPort());
        buffer.append("]");
        return buffer.toString();
    }

    public ChannelPoolImpl(RemoteSourceItem remoteSourceItem) {
        this.remoteSourceItem = remoteSourceItem;
    }

}
