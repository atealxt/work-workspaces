package test.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(final String args[]) throws UnknownHostException, IOException {

        Socket server = new Socket(InetAddress.getLocalHost(), 12345);

//        PrintWriter serverOutput = new PrintWriter(server.getOutputStream());
//        serverOutput.println("a啊b" + "\n");
//        serverOutput.println(("a啊b" + "\n").getBytes("GBK"));
//        serverOutput.flush();

//        BufferedWriter sw = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
//        sw.write("a啊b" + "\n");
        OutputStream sw = server.getOutputStream();
        sw.write(("a啊b" + "\n").getBytes("gbk"));
        sw.flush();

        server.close();
    }
}