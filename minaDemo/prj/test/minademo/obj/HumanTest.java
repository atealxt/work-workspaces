package minademo.obj;

import java.io.IOException;
import java.util.Arrays;

import minademo.Constants;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.vmpipe.VmPipeAcceptor;
import org.apache.mina.transport.vmpipe.VmPipeAddress;
import org.apache.mina.transport.vmpipe.VmPipeConnector;
import org.junit.Test;

public class HumanTest {

    @Test
    public void execute() throws IOException {

        IoAcceptor acceptor = new VmPipeAcceptor();
        VmPipeAddress address = new VmPipeAddress(Constants.PORT);

        // Set up server
        acceptor.setHandler(new HumanHandle("Acceptor"));
        acceptor.bind(address);

        // Connect to the server.
        VmPipeConnector connector = new VmPipeConnector();
        connector.setHandler(new HumanHandle("Connector"));
        ConnectFuture future = connector.connect(address);
        future.awaitUninterruptibly();
        IoSession session = future.getSession();

        session.write(genObj());

        // Wait until the match ends.
        session.getCloseFuture().awaitUninterruptibly();

        acceptor.unbind();
    }

    private Object genObj() {
        Human human = new Human(1, "tom和jerry", 30);
        human.setData("data数据".getBytes());
        human.setLanguages(Arrays.asList(new Language("中文"), new Language("English")));
        return human;
    }
}
