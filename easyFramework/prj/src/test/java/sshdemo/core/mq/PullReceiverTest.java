package sshdemo.core.mq;

import static sshdemo.core.mq.MQTestConstants.CLIENT_ID;
import static sshdemo.core.mq.MQTestConstants.QUEUE_NAME;
import static sshdemo.core.mq.MQTestConstants.SUBSCRIPTION_NAME;
import static sshdemo.core.mq.MQTestConstants.TOPIC_NAME;

import org.junit.Assert;

import sshdemo.TestBase;
import sshdemo.core.DIManager;
import sshdemo.entity.Father;

public class PullReceiverTest extends TestBase {

    private MQSender sender = DIManager.getBean(MQSenderImpl.class);
    private MQQueueReceiver queueReceiver = DIManager.getBean(MQQueueReceiverImpl.class);
    private MQTopicReceiver topicReceiver = DIManager.getBean(MQTopicReceiverImpl.class);

    @Override
    public void execute() throws Exception {
        subscribe();
        send();
        receive();
        unsubscribe();
    }

    private void subscribe() throws MQException {
        topicReceiver.setClientID(CLIENT_ID);
        topicReceiver.setSubscription(SUBSCRIPTION_NAME);
        topicReceiver.subscribe(TOPIC_NAME);
    }

    private void send() throws MQException {
        sender.sendMessage2Queue(QUEUE_NAME, "s1");
        Father f1 = new Father();
        f1.setName("f1");
        sender.sendMessage2Queue(QUEUE_NAME, f1);

        sender.sendMessage2Topic(TOPIC_NAME, "s2");
        Father f2 = new Father();
        f2.setName("f2");
        sender.sendMessage2Topic(TOPIC_NAME, f2);
    }

    private void receive() throws Exception {
        String s1 = queueReceiver.receiveString(QUEUE_NAME, 100);
        Father f1 = (Father) queueReceiver.receiveObject(QUEUE_NAME, 100);
        Assert.assertNotNull(s1);
        Assert.assertNotNull(f1);

        String s2 = topicReceiver.receiveString(TOPIC_NAME, 100);
        Father f2 = (Father) topicReceiver.receiveObject(TOPIC_NAME, 100);
        Assert.assertNotNull(s2);
        Assert.assertNotNull(f2);
    }

    private void unsubscribe() throws MQException {
        topicReceiver.unsubscribe(TOPIC_NAME);
    }
}
