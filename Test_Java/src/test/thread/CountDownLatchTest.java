package test.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountDownLatchTest{

    @Test
    public void execute() throws Exception {
        CountDownLatch runningThreadNum = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new ThreadSendTest(runningThreadNum).start();
        }
        runningThreadNum.await();
    }

    class ThreadSendTest extends Thread {

        private CountDownLatch runningThreadNum;

        public ThreadSendTest(final CountDownLatch runningThreadNum) {
            this.runningThreadNum = runningThreadNum;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runningThreadNum.countDown();
        }
    }
}
