package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static void main(final String args[]) throws UnknownHostException, IOException {

        ServerSocket s = new ServerSocket(12345, 0);
        Socket socket = s.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        System.out.println(br.readLine());

        socket.close();
        s.close();
    }
}
