package sshdemo.core.mq;

import static sshdemo.core.mq.MQTestConstants.LOOP_CNT;
import static sshdemo.core.mq.MQTestConstants.QUEUE_NAME;
import static sshdemo.core.mq.MQTestConstants.TOPIC_NAME;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.junit.Assert;

import sshdemo.TestBase;
import sshdemo.core.DIManager;
import sshdemo.entity.Father;

public class PushReceiverTest extends TestBase {

    private MQSender sender = DIManager.getBean(MQSenderImpl.class);
    private MQQueueReceiver queueReceiver = DIManager.getBean(MQQueueReceiverImpl.class);
    private MQTopicReceiver topicReceiver = DIManager.getBean(MQTopicReceiverImpl.class);

    private MQListener listenerQueue = new MQListener() {

        @Override
        public void onMessage(final Message message) {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                try {
                    String obj = msg.getText();
                    Assert.assertNotNull(obj);
                    logger.debug(obj);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            } else {
                ObjectMessage msg = (ObjectMessage) message;
                try {
                    Serializable obj = msg.getObject();
                    Assert.assertNotNull(obj);
                    logger.debug(obj);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            try {
                message.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    };

    private MQListener istenerTopic = new MQListener() {

        @Override
        public void onMessage(final Message message) {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                try {
                    String obj = msg.getText();
                    Assert.assertNotNull(obj);
                    logger.debug(obj);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            } else {
                ObjectMessage msg = (ObjectMessage) message;
                try {
                    Serializable obj = msg.getObject();
                    Assert.assertNotNull(obj);
                    logger.debug(obj);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void execute() throws Exception {
        register();
        send();

        Waiter w = new Waiter();
        w.start();
        w.join();
    }

    private void register() throws MQException {
        queueReceiver.setAutoAcknowledge(false);
        topicReceiver.register(TOPIC_NAME, istenerTopic);
    }

    private void send() throws MQException {
        for (int j = 0; j < LOOP_CNT; j++) {
            sender.sendMessage2Queue(QUEUE_NAME, "queue_s" + j, j);
            Father f1 = new Father();
            f1.setName("queue_f" + j);
            sender.sendMessage2Queue(QUEUE_NAME, f1, j);

            sender.sendMessage2Topic(TOPIC_NAME, "topic_s" + j);
            Father f2 = new Father();
            f2.setName("topic_f" + j);
            sender.sendMessage2Topic(TOPIC_NAME, f2);
        }

        // 队列的优先级测试
        queueReceiver.register(QUEUE_NAME, listenerQueue);
    }
}

class Waiter extends Thread {

    int i = 0;

    @Override
    public void run() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                if (i > 10) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
