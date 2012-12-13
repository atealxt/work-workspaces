package sshdemo.core.mq;

import java.io.Serializable;

public interface MQReceiver {

    void setAutoAcknowledge(final boolean autoAcknowledge);

    void register(String dest, MQListener listener) throws MQException;

    String receiveString(final String dest) throws MQException;

    String receiveString(final String dest, long timeout) throws MQException;

    Serializable receiveObject(final String dest) throws MQException;

    Serializable receiveObject(final String dest, long timeout) throws MQException;
}