package sshdemo.core.mq;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MQSenderImpl implements MQSender {

    private static Log logger = LogFactory.getLog(MQSenderImpl.class);

    @Autowired
    @Qualifier("pooledConnectionFactory")
    private ConnectionFactory connectionFactory;
    private long timeToLive;
    private boolean persistent = true;
    private static final int PRIORITY_MIDDLE = 5;

    @Override
    public void sendMessage2Queue(final String dest, final String msg) throws MQException {
        sendMessage2Queue(dest, msg, PRIORITY_MIDDLE);
    }

    @Override
    public void sendMessage2Queue(final String dest, final String msg, final int priority) throws MQException {
        sendMessage(dest, msg, true, priority, true);
    }

    @Override
    public void sendMessage2Queue(final String dest, final Serializable obj) throws MQException {
        sendMessage2Queue(dest, obj, PRIORITY_MIDDLE);
    }

    @Override
    public void sendMessage2Queue(final String dest, final Serializable obj, final int priority) throws MQException {
        sendMessage(dest, obj, false, priority, true);
    }

    @Override
    public void sendMessage2Topic(final String dest, final String msg) throws MQException {
        sendMessage2Topic(dest, msg, PRIORITY_MIDDLE);
    }

    @Override
    public void sendMessage2Topic(final String dest, final String msg, final int priority) throws MQException {
        sendMessage(dest, msg, true, priority, false);
    }

    @Override
    public void sendMessage2Topic(final String dest, final Serializable obj) throws MQException {
        sendMessage2Topic(dest, obj, PRIORITY_MIDDLE);
    }

    @Override
    public void sendMessage2Topic(final String dest, final Serializable obj, final int priority) throws MQException {
        sendMessage(dest, obj, false, priority, false);
    }

    private void sendMessage(
            final String dest,
            final Serializable msg,
            final boolean simpleMsg,
            final int priority,
            final boolean isQueue) throws MQException {
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        Message requestMessage = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if (isQueue) {
                destination = session.createQueue(dest);
            } else {
                destination = session.createTopic(dest);
            }
            producer = session.createProducer(destination);

            producer.setTimeToLive(timeToLive);
            producer.setPriority(priority);
            if (persistent) {
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            } else {
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            }

            // logger.debug("connection: " + ((PooledConnection)connection).getConnection().hashCode());
//            logger.debug("connection: " + connection);
//            logger.debug("session: " + session);
//            logger.debug("destination: " + destination);
//            logger.debug("producer: " + producer);

            if (simpleMsg) {
                requestMessage = session.createTextMessage((String) msg);
            } else {
                requestMessage = session.createObjectMessage(msg);
            }
            producer.send(requestMessage);
        } catch (JMSException e) {
            throw new MQException(e);
        } finally {
            if (producer != null) {
                try {
                    producer.close();
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
    public void setTimeToLive(final long millisecond) {
        this.timeToLive = millisecond;
    }

    @Override
    public void setPersistent(final boolean persistent) {
        this.persistent = persistent;
    }
}