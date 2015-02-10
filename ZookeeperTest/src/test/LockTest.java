package test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.recipes.lock.WriteLock;

public class LockTest {

	public static void main(String[] args) throws Exception {
		int port = 2181;
		String zkConnect = "localhost:" + port;

		System.err.println("Connecting to: " + zkConnect);
		ZooKeeper zk = new ZooKeeper(zkConnect, 10000, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println("Watcher processed!");
			}
		});

		WriteLock lock = new WriteLock(zk, "/lock/test", null);

		boolean b = false;
		try {
			while (true) {
				b = lock.lock();
				if (b) {
					break;
				}
				System.out.println("didn't get the lock, retrying...");
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("get the lock!");
		
		Thread.sleep(5000);
		System.out.println("free the lock!");
		lock.unlock();

		Thread.sleep(10000);

		zk.close();

	}
}
