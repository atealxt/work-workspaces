package test_lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import static test_lab.LabUtil.ROUTERS;
import static test_lab.LabUtil.HOST_SEND;
import static test_lab.LabUtil.HOST_RECEIVE;

/**
 * Main class.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        start();
    }

    private static void start() {

        setTOPOLOGY();

        //create router
        for (int i = 0; i < 6; i++) {
            Router r = new Router(String.valueOf((char) (97 + i)));
            Thread t = new Thread(r);
            t.setPriority(Thread.MIN_PRIORITY);
            t.start();
            ROUTERS.put(String.valueOf((char) (97 + i)), r);
        }

        //create host
        Thread threadSend = new Thread(HOST_SEND);
        Thread threadReceive = new Thread(HOST_RECEIVE);
        threadSend.start();
        threadReceive.start();
    }

    /**
     * get from "topology.txt"
     */
    private static void setTOPOLOGY() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("topology.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String str = reader.readLine();
            while (str != null) {
                LabUtil.TOPOLOGY.add(str);
                str = reader.readLine();
            }

            reader.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
