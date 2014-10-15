package minademo.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import minademo.Constants;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class StringClient {

    public void sendMsgSocket() throws UnknownHostException, IOException {
        Socket server = new Socket(InetAddress.getLocalHost(), Constants.PORT);

        PrintWriter serverOutput = new PrintWriter(server.getOutputStream());
        serverOutput.println("Hello MySocketClient");
        serverOutput.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        System.out.println(in.readLine());

        server.close();
    }

    public void sendMsg() {

        SocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(5000);

        connector.getFilterChain().addLast("codec",
                                           new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setHandler(new StringSendHandler("hello mina"));
        ConnectFuture future = connector.connect(new InetSocketAddress(Constants.PORT));

        // wait until the summation is done
        future.awaitUninterruptibly();
        future.getSession().getCloseFuture().awaitUninterruptibly();

        connector.dispose();
    }

    public static void main(final String[] args) throws Exception {

        // new StringClient().sendMsgSocket();
        new StringClient().sendMsg();
    }
}
