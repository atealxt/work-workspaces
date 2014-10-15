package minademo.string;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

public class StringReceiveHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(final IoSession session, final Object msg) throws Exception {

        System.out.println("messageReceived: " + msg);

        Date date = new Date();
        session.write(date.toString());
        System.out.println("Message written...");
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

    @Override
    public void exceptionCaught(final IoSession session, final Throwable t) throws Exception {
        t.printStackTrace();
        session.close(false);
    }
}
