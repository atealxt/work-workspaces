package minademo.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.stream.StreamIoHandler;

public class StreamSendHandler extends StreamIoHandler {

    private static Logger logger = Logger.getLogger(StreamSendHandler.class.getName());

    private String srcPath;

    @Override
    public void sessionOpened(final IoSession session) {
        super.sessionOpened(session);
        logger.debug("session opened");
    }

    @Override
    public void sessionClosed(final IoSession session) throws Exception {
        super.sessionClosed(session);
        logger.debug("session closed");
    }

    public StreamSendHandler(final String srcPath) {
        super();
        this.srcPath = srcPath;
    }

    @Override
    protected void processStreamIo(final IoSession session, final InputStream in, final OutputStream out) {

        logger.debug("session: " + session);

        File sendFile = new File(srcPath);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sendFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        new StreamExecutor(session, inputStream, out, true, true, false).start();
    }
}
