package ipchecker;

import ipchecker.plugin.nio.NioClient;
import ipchecker.plugin.nio.RspHandler;
import ipchecker.util.PropertiesUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestIpChecker {

    public static void main(final String args[]) throws UnknownHostException, IOException {
        runByIO();
//        runByNIO();
    }

    static void runByIO() throws IOException {
        for (int i = 0; i < MAX_CONNECT + 1; i++) {
            Socket server = new Socket(InetAddress.getLocalHost(), PORT);

            BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
            OutputStream out = server.getOutputStream();

            out.write(("1.1.1.1\n").getBytes());
            out.flush();
            System.out.println(br.readLine());

            br.close();
            server.close();
        }
    }

    static void runByNIO() throws IOException {
        try {
            NioClient client = new NioClient(InetAddress.getLocalHost(), PORT);
            Thread t = new Thread(client);
            t.setDaemon(true);
            t.start();

            for (int i = 0; i < MAX_CONNECT + 1; i++) {
                RspHandler handler = new RspHandler();
                client.send("1.1.1.1".getBytes(), handler);
                handler.waitForResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static int PORT = Integer.parseInt(PropertiesUtils.getValue("listen.port"));
    private final static int MAX_CONNECT = Integer.parseInt(PropertiesUtils.getValue("ip.maxconnect"));
}
