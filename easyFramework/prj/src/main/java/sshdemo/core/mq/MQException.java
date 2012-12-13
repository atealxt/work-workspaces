package sshdemo.core.mq;

public class MQException extends Exception {

    private static final long serialVersionUID = -1690282627655242819L;

    public MQException() {
        super();
    }

    public MQException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MQException(final String message) {
        super(message);
    }

    public MQException(final Throwable cause) {
        super(cause);
    }
}
