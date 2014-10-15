package com.multicache4j.remote.channel;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.MemcachedClient;

import com.multicache4j.config.model.RemoteSourceItem;


//SpyMemcached最可贵的地方，就是提供了很强的健壮性，对某个Memcached服务器的倒掉与重起，
//SpyMemcached能够在后台进行很好的管理, 如不断Double间隔时间重连直到默认最大值30秒后不再增加等。
public class SpyChannelImpl implements Channel {
	
	MemcachedClient mcc;
	RemoteSourceItem remoteSourceItem;

	public SpyChannelImpl(MemcachedClient mcc, RemoteSourceItem remoteSourceItem) throws Exception {		
		this.mcc = mcc;
		this.remoteSourceItem = remoteSourceItem;
	}
	
	public boolean isAlive() {
		return mcc.isAlive();
	}

	public String get(String key) throws Exception {
		//return (String)client.get(key);
		
		// Try to get a value, for up to 5 seconds, and cancel if it doesn't return
        Object myObj=null;
        Future<Object> f=mcc.asyncGet(key);
        try {
            myObj=f.get(2, TimeUnit.SECONDS);
        } catch(Exception e) {
            // Since we don't need this, go ahead and cancel the operation.  This
            // is not strictly necessary, but it'll save some work on the server.
            f.cancel(false);
            // Do other timeout related stuff
        }
        return (String)myObj;
	}

	public void set(String key, String value) throws Exception {
		mcc.set(key, 0, value);
	}
	
	public void close() throws Exception {
		//mcc.shutdown(50, TimeUnit.SECONDS);不要设置时间，如果服务端关闭，创建时验证close会等50s，后面的都会被lock
		mcc.shutdown();
	}
	
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SpyChannelImpl[");
        buffer.append("name=").append(remoteSourceItem.getName());
        buffer.append(", host=").append(remoteSourceItem.getHost());
        buffer.append(", port=").append(remoteSourceItem.getPort());
        buffer.append("]");
        return buffer.toString();
	}
	
	public RemoteSourceItem getRemoteSourceItem() {
		return remoteSourceItem;
	}
	
	public static void main(String[] args) throws Exception {
		RemoteSourceItem remoteSourceItem = new RemoteSourceItem();
		remoteSourceItem.setName("tt0");
		remoteSourceItem.setHost("10.11.161.26");
		remoteSourceItem.setPort(1978);
		
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(remoteSourceItem.getHost(), remoteSourceItem.getPort()));
        Channel channel = new SpyChannelImpl(mcc, remoteSourceItem);
		String key = "foo";
		String value = "bar";
		System.out.println(channel.isAlive());
		
		System.out.println("===============" + channel.toString());   
		Long t1 = System.currentTimeMillis();
        for(int i=0;i<30000;i++)
    	channel.set(key+i, value+i);
		channel.set(key, value);
        Long t2 = System.currentTimeMillis();
        System.out.println("===============set: " + (t2-t1) + "ms");
        
        System.out.println("===============foo=" + channel.get(key));
        Long t3 = System.currentTimeMillis();
        System.out.println("===============get: " + (t3-t2) + "ms");
        channel.close();
	}
}
