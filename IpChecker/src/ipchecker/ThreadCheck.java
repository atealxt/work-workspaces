package ipchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadCheck extends Thread {

    private Socket socket;

    ThreadCheck(final Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            String ip = br.readLine();

            // 验证
            boolean result = IpChecker.valid(ip);
            socket.getOutputStream().write((Boolean.toString(result) + "\n").getBytes());

            if (!result) {
                System.out.println(ip + " access denied.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
