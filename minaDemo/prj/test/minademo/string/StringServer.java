package minademo.string;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import minademo.Constants;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 测试1. "telnet 127.0.0.1 9123" （注意Windows下中文默认编码为GB2312）<br>
 * 测试2. "java SocketClient"
 */
public class StringServer {

    public void listen() throws IOException {

        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("codec",
                                          new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new StringReceiveHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.bind(new InetSocketAddress(Constants.PORT));
        System.out.println("String server started.");
    }

    public static void main(final String[] args) throws IOException {
        new StringServer().listen();
    }
}
