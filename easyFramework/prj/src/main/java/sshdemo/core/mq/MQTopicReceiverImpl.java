package sshdemo.core.mq;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MQTopicReceiverImpl implements MQTopicReceiver {

    private static Log logger = LogFactory.getLog(MQTopicReceiverImpl.class);

    private boolean autoAcknowledge = true;
    /** 是否支持支持队列离线 */
    private boolean durable = true;

    private String clientID;
    private String subscription;

    @Autowired
    @Qualifier("pooledConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Autowired
    @Qualifier("connectionFactory")
    private ConnectionFactory topicConnFactory;// TODO 现在这个factory的connection没做缓存

    public MQTopicReceiverImpl() {}

    public MQTopicReceiverImpl(final String clientID, final String subscription) {
        this.clientID = clientID;
        this.subscription = subscription;
    }

    @Override
    public void register(final String dest, final MQListener listener) throws MQException {
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        try {
            connection = connectionFactory.createConnection();
            if (autoAcknowledge) {
                // 注意如果设为CLIENT_ACKNOWLEDGE的话，需要主动调用message.acknowledge才能清除数据！
                session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            } else {
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
            destination = session.createTopic(dest);
            consumer = session.createConsumer(destination);
            connection.start();

            // logger.debug("connection: " + connection);
            // logger.debug("session: " + session);
            // logger.debug("destination: " + destination);
            // logger.debug("consumer: " + consumer);

            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            throw new MQException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.stop();
                    connection.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void subscribe(final String dest) throws MQException {
        TopicConnection connection = null;
        TopicSession session = null;
        Topic destination = null;
        @SuppressWarnings("unused") MessageConsumer consumer = null;
        TopicConnectionFactory connFactory = (TopicConnectionFactory) topicConnFactory;
        try {
            connection = connFactory.createTopicConnection();
            connection.setClientID(clientID);
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(dest);
            if (durable) {
                consumer = session.createDurableSubscriber(destination, subscription);
            } else {
                consumer = session.createSubscriber(destination);
            }
        } catch (JMSException e) {
            throw new MQException(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void unsubscribe(final String dest) throws MQException {
        TopicConnection connection = null;
        TopicSession session = null;
        TopicConnectionFactory connFactory = (TopicConnectionFactory) topicConnFactory;
        try {
            connection = connFactory.createTopicConnection();
            connection.setClientID(clientID);
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            session.unsubscribe(subscription);
        } catch (JMSException e) {
            throw new MQException(e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public String receiveString(final String dest) throws MQException {
        return (String) receiveMessage(dest, -1, true);
    }

    @Override
    public String receiveString(final String dest, final long timeout) throws MQException {
        return (String) receiveMessage(dest, timeout, true);
    }

    @Override
    public Serializable receiveObject(final String dest) throws MQException {
        return receiveMessage(dest, -1, false);
    }

    @Override
    public Serializable receiveObject(final String dest, final long timeout) throws MQException {
        return receiveMessage(dest, timeout, false);
    }

    private Serializable receiveMessage(final String dest, final long timeout, final boolean simpleMsg)
            throws MQException {
        TopicConnection connection = null;
        TopicSession session = null;
        Topic destination = null;
        TopicSubscriber consumer = null;
        TopicConnectionFactory connFactory = (TopicConnectionFactory) topicConnFactory;
        try {
            connection = connFactory.createTopicConnection();
            connection.setClientID(clientID);
            session = connection.createTopicSession(false, Session.CLIENT_ACKNOWLEDGE);
            destination = session.createTopic(dest);
            if (durable) {
                consumer = session.createDurableSubscriber(destination, subscription);
            } else {
                consumer = session.createSubscriber(destination);
            }
            connection.start();

            Message message = null;
            if (timeout > 0) {
                message = consumer.receive(timeout);
            } else {
                message = consumer.receive();
            }
            if (message == null) {
                return null;
            }
            if (simpleMsg) {
                TextMessage msg = (TextMessage) message;
                String obj = msg.getText();
                msg.acknowledge();
                return obj;
            } else {
                ObjectMessage msg = (ObjectMessage) message;
                Serializable obj = msg.getObject();
                msg.acknowledge();
                return obj;
            }
        } catch (JMSException e) {
            throw new MQException(e);
        } finally {
            if (consumer != null) {
                try {
                    consumer.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void setClientID(final String clientID) {
        this.clientID = clientID;
    }

    @Override
    public void setSubscription(final String subscription) {
        this.subscription = subscription;
    }

    @Override
    public void setDurable(final boolean durable) {
        this.durable = durable;
    }

    @Override
    public void setAutoAcknowledge(final boolean autoAcknowledge) {
        this.autoAcknowledge = autoAcknowledge;
    }
}
