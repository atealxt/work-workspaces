package sshdemo.core.mq;

import javax.jms.MessageListener;

/**
 * <p>
 * push模型，服务端主动发送数据给客户端。<br>
 * 如果流量过大的话，服务端需要做流量控制，无法最大化客户端的处理能力。
 * </p>
 *
 * <p>
 * 可以将push和pull结合起来，来应对过高并发。push小数据的通知信息，让客户端再来主动pull。<br>
 * 当然，这样就不能用MessageListener了。
 * </p>
 */
public interface MQListener extends MessageListener {
}