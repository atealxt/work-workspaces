package sshdemo.core.mq;

import static sshdemo.core.mq.MQTestConstants.LOOP_CNT;
import static sshdemo.core.mq.MQTestConstants.QUEUE_NAME;
import static sshdemo.core.mq.MQTestConstants.TOPIC_NAME;

import java.util.concurrent.CountDownLatch;

import sshdemo.TestBase;
import sshdemo.core.DIManager;

public class MQSenderTest extends TestBase {

    @Override
    public void execute() throws Exception {
        CountDownLatch runningThreadNum = new CountDownLatch(LOOP_CNT);
        for (int i = 0; i < LOOP_CNT; i++) {
            new ThreadSendTest(runningThreadNum, i).start();
        }
        runningThreadNum.await();
    }

    class ThreadSendTest extends Thread {

        private CountDownLatch runningThreadNum;
        private int i;

        public ThreadSendTest(final CountDownLatch runningThreadNum, final int i) {
            this.runningThreadNum = runningThreadNum;
            this.i = i;
        }

        @Override
        public void run() {
            MQSender sender = DIManager.getBean(MQSenderImpl.class);
            for (int j = 0; j < LOOP_CNT; j++) {
                try {
                    if (i % 2 != 0) {
                        sender.sendMessage2Queue(QUEUE_NAME, "hello Queue", j);
                    } else {
                        sender.sendMessage2Topic(TOPIC_NAME, "hello Topic");
                    }
                } catch (MQException e) {
                    e.printStackTrace();
                }
            }
            runningThreadNum.countDown();
        }
    }

}
