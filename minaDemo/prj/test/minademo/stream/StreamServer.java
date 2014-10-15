package minademo.stream;

import java.io.IOException;
import java.net.InetSocketAddress;

import minademo.Constants;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class StreamServer {

    private static Logger logger = Logger.getLogger(StreamServer.class.getName());

    public void listen() throws IOException {

        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setHandler(new StreamReceiveHandler(Constants.TEST_FILE_TO));
        acceptor.bind(new InetSocketAddress(Constants.ADDRESS_SERVER_FILE, Constants.PORT_FILE));
    }

    public static void main(final String[] args) throws IOException {

        new StreamServer().listen();
        logger.debug("File server started.");
    }
}
