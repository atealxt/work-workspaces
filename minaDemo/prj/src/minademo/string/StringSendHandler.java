package minademo.string;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

public class StringSendHandler extends IoHandlerAdapter {

    private String message;

    public StringSendHandler(final String message) {
        super();
        this.message = message;
    }

    @Override
    public void sessionCreated(final IoSession session) throws Exception {
        System.out.println("Session created...");

        if (session instanceof SocketSessionConfig) {
            ((SocketSessionConfig) session.getConfig()).setReceiveBufferSize(2048);
        }

        // Idle time settings are all disabled by default
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
    }

    /**
     * 当连接空闲时被触发。使用IoSessionConfig中的setIdleTime(IdleStatus status, int
     * idleTime)方法可以设置session的空闲时间。如果该Session的空闲时间超过设置的值，该方法被触发，可以通过
     * session.getIdleCount(status)来获取sessionIdle被触发的次数。(类似心跳)
     */
    @Override
    public void sessionIdle(final IoSession session, final IdleStatus status) throws Exception {

        if (status == IdleStatus.READER_IDLE) {
            session.close(true);
        }
    }

    @Override
    public void sessionOpened(final IoSession session) throws Exception {
        session.write(message);
    }

    @Override
    public void messageSent(final IoSession session, final Object message) throws Exception {
        System.out.println("messageSent: " + message);
    }

    @Override
    public void messageReceived(final IoSession session, final Object message) throws Exception {
        System.out.println("Response from server: " + message);
        session.close(true);
    }

    @Override
    public void exceptionCaught(final IoSession session, final Throwable t) throws Exception {
        t.printStackTrace();
        session.close(true);
    }
}
