package com.multicache4j;

//测试使用了连接池
public class TestMultiCache {
	
	public static void testSet(final String key, final String value, final int start, final int end) {
    	// 测试写入
        Long t1 = System.currentTimeMillis();       	
       	//new Thread() {
        //	public void run() {
        		for(int i=start;i<end;i++) {
               		//System.out.println("set " + i + key);
               		MultiCacheFactory.getInstance().set(i+key, i+value);
               		MultiCacheFactory.getInstance().get(i+key);
               		sleep(100);
               	}
        //	}
        //}.start();
        Long t2 = System.currentTimeMillis();
        System.out.println("set: " + (t2-t1) + "ms");
	}
	
	public static void testGet(final String key, final int start, final int end) {
        // 测试读取
        Long t1 = System.currentTimeMillis();
        for(int i=start;i<end;i++) {
        //new Thread() {
        //	public void run() {
        		
               		//String value = 
               			MultiCacheFactory.getInstance().get(key);
               		//System.out.println("get " + i + "key=" + value);
               		sleep(100);
        //	}
        //}.start();
       	}
        
        Long t2 = System.currentTimeMillis();
        System.out.println("get: " + (t2-t1) + "ms");
	}
	
	public static void sleep(long time) {
		try {
        	Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		// 测试memd-spy [先关闭OK，启动关闭启动OK]
		//TestCache.testSet("foo0", "bar", 0, 10000); //写+读
		//TestCache.testGet("foo0", 0, 10000);
		
		// 测试redis-jedis [先关闭OK，启动关闭启动OK]
		TestMultiCache.testSet("fooa", "bar", 0, 10000); //写+读
		//TestCache.testGet("fooa", 0, 10000);
		
		// 测试ttserver-ttclient [先关闭OK，启动关闭启动 Error]
		//TestCache.testSet("fooy", "bar", 0, 10000); //写+读
		//TestCache.testGet("fooy", 0, 10000);
		
		// 测试ttserver-spy [先关闭OK，启动关闭启动OK]
		//TestCache.testSet("fooz", "bar", 0, 10000); //写+读
		//MultiCacheFactory.getInstance().set("fooz", "aaa");
		//TestCache.testGet("fooz", 0, 10000);
		
		//TestCache.sleep(3000);
        
		//测试结果
		//SpyChannelImpl[host=10.10.82.79, port=11210] set: 188ms
		//SpyChannelImpl[host=10.10.82.79, port=11210] get: 13500ms
		//JedisChannelImpl[host=10.11.161.26, port=6379] set: 9828ms
		//JedisChannelImpl[host=10.11.161.26, port=6379] get: 10641ms
		//TTChannelImpl[host=10.11.161.26, port=1978] set: 9516ms
		//TTChannelImpl[host=10.11.161.26, port=1978] get: 9407ms
	}
}
