package minademo.obj;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class HumanHandle extends IoHandlerAdapter {

    private String handleName;

    public HumanHandle(final String handleName) {
        super();
        this.handleName = handleName;
    }

    @Override
    public void exceptionCaught(final IoSession session, final Throwable cause) {
        cause.printStackTrace();
        session.close(true);
    }

    @Override
    public void messageReceived(final IoSession session, final Object message) throws Exception {
        Human human = (Human) message;
        System.out.println(handleName + " received:\n" + human);
        session.close(true);
    }

    @Override
    public void messageSent(final IoSession session, final Object message) throws Exception {
        Human human = (Human) message;
        System.out.println(handleName + " sent:\n" + human);
    }

}
