package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    final static ExecutorService MyServicePool = Executors.newFixedThreadPool(2);

    public static void main(final String args[]) {
//        testjdk4();
         testjdk5();
    }

    private static void testjdk4() {
        for (int i = 1; i <= 10; i++) {
            new Thread(new MyService(i)).start();
        }
    }

    private static void testjdk5() {
        for (int i = 1; i <= 10; i++) {
            MyServicePool.execute(new MyService(i));// asynchronization
        }
        MyServicePool.shutdown(); // Disable new tasks
    }
}

// class MyService implements Runnable {
class MyService implements Runnable {

    private final int i;

    public MyService(final int i) {
        this.i = i;
    }

    public void run() {
        try {
            Thread.sleep(1000 * i);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run run run! " + i + " " + this + " " + Thread.currentThread().getName());
    }
}
