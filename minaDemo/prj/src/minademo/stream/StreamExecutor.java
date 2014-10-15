package minademo.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import minademo.Constants;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class StreamExecutor extends Thread {

    private static Logger logger = Logger.getLogger(StreamExecutor.class.getName());

    private IoSession session;
    private InputStream in;
    private OutputStream out;

    private boolean autoFlush;

    private boolean autoInClose;
    private boolean autoOutClose;

    public StreamExecutor(final InputStream in, final OutputStream out) {
        super();
        this.in = in;
        this.out = out;
    }

    public StreamExecutor(
            final IoSession session,
            final InputStream in,
            final OutputStream out,
            final boolean autoFlush,
            final boolean autoInClose,
            final boolean autoOutClose) {
        super();
        this.session = session;
        this.in = in;
        this.out = out;
        this.autoFlush = autoFlush;
        this.autoInClose = autoInClose;
        this.autoOutClose = autoOutClose;
    }

    @Override
    public void run() {

        logger.debug("writeStream begin");

        try {
            byte[] bufferByte = new byte[Constants.DEFAULT_FLUSH_SIZE];
            int tempData = 0;
            while ((tempData = in.read(bufferByte)) != -1) {

                out.write(bufferByte, 0, tempData);

                if (autoFlush) {
                    try {
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (autoInClose) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (autoOutClose) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        session.close(true);

        logger.debug("writeStream end");

    }

}
