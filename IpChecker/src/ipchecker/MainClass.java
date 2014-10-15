package ipchecker;

import ipchecker.plugin.nio.EchoWorker;
import ipchecker.plugin.nio.NioServer;
import ipchecker.util.PropertiesUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {

    public static void main(final String[] args) throws IOException {
        runByIO();
        // runByNIO();
    }

    static void runByIO() throws IOException {
        ServerSocket s = new ServerSocket(PORT, 0);

        while (true) {
            Socket socket = null;
            try {
                socket = s.accept();
                new ThreadCheck(socket).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void runByNIO() {
        try {
            EchoWorker worker = new EchoWorker();
            new Thread(worker).start();
            new Thread(new NioServer(null, PORT, worker)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static int PORT = Integer.parseInt(PropertiesUtils.getValue("listen.port"));
}
