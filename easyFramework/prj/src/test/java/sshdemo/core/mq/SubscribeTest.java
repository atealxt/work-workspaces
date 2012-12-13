package sshdemo.core.mq;

import static sshdemo.core.mq.MQTestConstants.CLIENT_ID;
import static sshdemo.core.mq.MQTestConstants.LOOP_CNT;
import static sshdemo.core.mq.MQTestConstants.SUBSCRIPTION_NAME;
import static sshdemo.core.mq.MQTestConstants.TOPIC_NAME;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;

import sshdemo.TestBase;
import sshdemo.core.DIManager;

public class SubscribeTest extends TestBase {

    @Autowired
    private MQSender sender;
    private MQTopicReceiver topicReceiver = DIManager.getBean("MQTopicReceiverImpl", CLIENT_ID, SUBSCRIPTION_NAME);

    @Override
    public void execute() throws Exception {
        subscribe();
        publish();
        CountDownLatch runningThreadNum = new CountDownLatch(1);
        new Receiver(runningThreadNum, topicReceiver).start();
        runningThreadNum.await();
        unsubscribe();
    }

    private void subscribe() throws MQException {
        topicReceiver.subscribe(TOPIC_NAME);
    }

    private void publish() throws MQException {
        for (int j = 0; j < LOOP_CNT; j++) {
            sender.sendMessage2Topic(TOPIC_NAME, "hello Topic" + j);
            // sender.sendMessage2Topic(TOPIC_NAME, "hello Topic" + j, j);
        }
    }

    private void unsubscribe() throws MQException {
        topicReceiver.unsubscribe(TOPIC_NAME);
    }
}

class Receiver extends Thread {

    private CountDownLatch runningThreadNum;
    private MQTopicReceiver topicReceiver;

    public Receiver(final CountDownLatch runningThreadNum, final MQTopicReceiver receiver) {
        this.runningThreadNum = runningThreadNum;
        this.topicReceiver = receiver;
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < LOOP_CNT; j++) {
                try {
                    String s = topicReceiver.receiveString(TOPIC_NAME, 100);
                    System.out.println("messages: " + s);
                    Thread.sleep(100);// 模拟消耗
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            runningThreadNum.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
