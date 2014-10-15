package sshdemo.core.mq;

/** 有2种方法可以接受消息，一种是注册后被动接收，另一种是调用主动获取。 */
public interface MQQueueReceiver extends MQReceiver {
}