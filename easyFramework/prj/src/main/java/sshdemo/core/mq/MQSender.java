package sshdemo.core.mq;

import java.io.Serializable;

public interface MQSender {

    void setPersistent(final boolean persistent);

    /** 发送出去的消息的存活时间。默认为0，即永不超时 */
    void setTimeToLive(long millisecond);

    void sendMessage2Queue(final String dest, final String msg) throws MQException;

    void sendMessage2Queue(final String dest, final String msg, int priority) throws MQException;

    void sendMessage2Queue(final String dest, final Serializable obj) throws MQException;

    void sendMessage2Queue(final String dest, final Serializable obj, int priority) throws MQException;

    void sendMessage2Topic(final String dest, final String msg) throws MQException;

    /** 发送Topic设优先级的话，有可能把前面的message冲掉，容易造成丢数据 */
    @Deprecated
    void sendMessage2Topic(final String dest, final String msg, int priority) throws MQException;

    void sendMessage2Topic(final String dest, final Serializable obj) throws MQException;

    /** 发送Topic设优先级的话，有可能把前面的message冲掉，容易造成丢数据 */
    @Deprecated
    void sendMessage2Topic(final String dest, final Serializable obj, int priority) throws MQException;

    //TODO 支持异步发送
}
