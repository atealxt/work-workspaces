    package minademo.stream;

import java.net.InetSocketAddress;

import minademo.Constants;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class StreamClient {

    public void sendFile() {

        SocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(5000);

        connector.setHandler(new StreamSendHandler(Constants.TEST_FILE_FROM));

        ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();
        factory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
        factory.setEncoderMaxObjectSize(Integer.MAX_VALUE);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(factory));

        ConnectFuture future = connector.connect(new InetSocketAddress(Constants.ADDRESS_SERVER_FILE,
                                                                       Constants.PORT_FILE));
        // wait until the summation is done
        future.awaitUninterruptibly();
        future.getSession().getCloseFuture().awaitUninterruptibly();

        connector.dispose();
    }

    public static void main(final String[] args) {
        new StreamClient().sendFile();
    }
}