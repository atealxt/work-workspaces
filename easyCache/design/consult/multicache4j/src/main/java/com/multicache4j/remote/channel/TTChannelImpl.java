package com.multicache4j.remote.channel;

import com.chenlb.ttserver.TTServerClient;
import com.multicache4j.config.model.RemoteSourceItem;

public class TTChannelImpl implements Channel {
	
	TTServerClient ttsc;
	RemoteSourceItem remoteSourceItem;

	public TTChannelImpl(TTServerClient ttsc, RemoteSourceItem remoteSourceItem) throws Exception {		
		this.ttsc = ttsc;
		this.remoteSourceItem = remoteSourceItem;
	}
	
	public boolean isAlive() {
		try {
			ttsc.getString("foo");
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public String get(String key) throws Exception {
		return ttsc.getString(key);
	}

	public void set(String key, String value) throws Exception {
		ttsc.putString(key, value);
	}
	
	public void close() throws Exception {
		ttsc.close();
	}
	
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("TTChannelImpl[");
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
		
		TTServerClient ttsc = new TTServerClient(remoteSourceItem.getHost(), remoteSourceItem.getPort());
        Channel channel = new TTChannelImpl(ttsc, remoteSourceItem);
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
