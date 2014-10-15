package sshdemo.core.mq;

/**
 * 有2种方法可以接受消息，一种是注册后被动接收，另一种是调用主动获取。<br>
 * 被动接收暂无法支持退订阅;
 */
public interface MQTopicReceiver extends MQReceiver {

    void setClientID(final String clientID);

    void setSubscription(final String subscription);

    void setDurable(final boolean durable);

    void register(String dest, MQListener listener) throws MQException;

    void subscribe(String dest) throws MQException;

    void unsubscribe(String dest) throws MQException;
}

// 非持久化订阅持续到它们订阅对象的生命周期。这意味着，客户端只能在订阅者活动时看到相关主题发布的消息。如果订阅者不活动，它会错过相关主题的消息。
// 如果花费较大的开销，订阅者可以被定义为durable（持久化的）。持久化的订阅者注册一个带有JMS保持的唯一标识的持久化订阅（subscription）。带有相同标识的后续订阅者会再续前一个订阅者的订阅状态。如果持久化订阅没有活动的订阅者，JMS会保持订阅消息，直到消息被订阅接收或者过期。
