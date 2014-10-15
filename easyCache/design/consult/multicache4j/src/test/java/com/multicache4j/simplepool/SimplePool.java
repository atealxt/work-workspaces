package com.multicache4j.simplepool;

public class SimplePool {

	
	public static void main(String[] args) {
		Factory factory = new MyFactory();
		final SimplePool pool = new SimplePool(5, 10, factory);
		for (int i =0;i<7;i++) {
			new Thread() {
				public void run() {
					while(true) {
						Object object = pool.fetchFromPool();
						pool.print();
						try { Thread.sleep(1000); } catch (Exception e){}
						pool.putInPool(object);
						pool.print();
					}
				}
			}.start();
		}
	}

	private final int initialPoolSize;
	private final int maxPoolSize;
	private final Factory factory;
	private transient Object[] pool;
	private transient int nextAvailable;
	private transient Object mutex = new Object();
	
	public void print() {
		System.out.println(pool.length + " in " + nextAvailable);
	}

	public SimplePool(int initialPoolSize, int maxPoolSize, Factory factory) {
		this.initialPoolSize = initialPoolSize;
		this.maxPoolSize = maxPoolSize;
		this.factory = factory;
	}

	public Object fetchFromPool() {
		Object result;
		synchronized (mutex) {
			if (pool == null) {
				pool = new Object[maxPoolSize];
				for (nextAvailable = initialPoolSize; nextAvailable > 0;) {
					putInPool(factory.newInstance());
				}
			}
			while (nextAvailable == maxPoolSize) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException("Interrupted whilst waiting "
							+ "for a free item in the pool : " + e.getMessage());
				}
			}
			result = pool[nextAvailable++];
			/*if (result == null) {
				result = factory.newInstance();
				putInPool(result);
				++nextAvailable;
			}*/
		}
		return result;
	}

	protected void putInPool(Object object) {
		synchronized (mutex) {
			pool[--nextAvailable] = object;
			mutex.notify();
		}
	}

	private Object readResolve() {
		mutex = new Object();
		return this;
	}
}
