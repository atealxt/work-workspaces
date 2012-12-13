package minademo.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import minademo.Utils;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.stream.StreamIoHandler;

public class StreamReceiveHandler extends StreamIoHandler {

    private static Logger logger = Logger.getLogger(StreamReceiveHandler.class.getName());

    private String savePath;

    public StreamReceiveHandler(final String savePath) {
        super();
        this.savePath = Utils.addCurrentTime(savePath);
    }

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

    @Override
    protected void processStreamIo(final IoSession session, final InputStream in, final OutputStream out) {

        logger.debug("session: " + session);

        File receiveFile = new File(savePath);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(receiveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        new StreamExecutor(session, in, outputStream, true, false, true).start();
    }
}
