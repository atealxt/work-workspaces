package com.multicache4j.remote.channel;

import redis.clients.jedis.Jedis;

import com.multicache4j.config.model.RemoteSourceItem;

public class JedisChannelImpl implements Channel {
	Jedis jedis;
	RemoteSourceItem remoteSourceItem;

	public JedisChannelImpl(Jedis jedis, RemoteSourceItem remoteSourceItem) throws Exception {
		this.jedis = jedis;
		this.remoteSourceItem = remoteSourceItem;
	}
	
	public boolean isAlive() {
		//return jedis.isConnected();
		try {
			jedis.get("foo");
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public String get(String key) throws Exception {
		return jedis.get(key);
	}

	public void set(String key, String value) throws Exception {
		jedis.set(key, value);
	}
	
	public void close() throws Exception {
		jedis.quit();
	}
	
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("JedisChannelImpl[");
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
		remoteSourceItem.setName("redis1");
		remoteSourceItem.setHost("10.11.161.26");
		remoteSourceItem.setPort(6379);
		
		Jedis jedis = new Jedis(remoteSourceItem.getHost(), remoteSourceItem.getPort(), remoteSourceItem.getTimeout());
        Channel channel = new JedisChannelImpl(jedis, remoteSourceItem);
		String key = "foo";
		String value = "bar";
		System.out.println(channel.isAlive());
		
		System.out.println("===============" + channel.toString());   
		Long t1 = System.currentTimeMillis();
        //for(int i=0;i<30000;i++)
    	//channel.set(key+i, value+i);
		channel.set(key, value);
        Long t2 = System.currentTimeMillis();
        System.out.println("===============set: " + (t2-t1) + "ms");
		System.out.println(channel.isAlive());
        
        System.out.println("===============foo=" + channel.get(key));
        Long t3 = System.currentTimeMillis();
        System.out.println("===============get: " + (t3-t2) + "ms");
        channel.close();
	}
}
