package test_lab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Host.
 */
public class Host implements Runnable {

    /**
     * Host target
     * 0: source
     * 1: destination
     */
    private int target = -1;
    private StringBuffer data = new StringBuffer();
    private final int UNIT = 10;
    private boolean isEndData;
    /** Host name */
    private String name;

    /**
     * @param name Host name
     * @param target Host target: 0: source, 1: destination
     */
    public Host(String name, int target) {
        super();

        this.target = target;
        this.name = name;
    }

    @Override
    public void run() {

        if (target == 0) {
            send();
        } else {
            accept();
        }
    }

    /**
     * Transport data.
     */
    public void setData(String data, boolean isEndData) {

        System.out.println("Host " + name + " get Data.");
        this.data.append(data);
        this.isEndData = isEndData;
    }

    private synchronized void savaAndend() {

        System.out.println("Host " + name + " save Data: ");
        System.out.println(data);

        File file = new File("D:/data-reverse.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(data.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }

        //saving completed
        System.exit(0);
    }

    private void accept() {

        while (!isEndData) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        savaAndend();
    }

    /**
     * send "data.txt"
     */
    private void send() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            int i = 0;
            Stack<String> stackData = new Stack<String>();
            do {
                i = reader.read();
                if (i != -1) {
                    char c = (char) i;
                    data.append(c);
                }

                //cut
                if (data.length() >= UNIT) {
                    stackData.push(data.toString());
                    data.delete(0, data.length());
                }
            } while (i != -1);

            //save last data
            if (data.length() > 0) {
                stackData.push(data.toString());
            }
            reader.close();
            in.close();

            System.out.println("POST DATA: " + stackData);

            //send
            int iCount = 0;
            while (stackData.size() > 0) {

                String str = stackData.pop();
                String path = LabUtil.getCurrentShortestPath();
                System.out.println("Packet " + (++iCount) + ": " + path);

                Router r = LabUtil.getFirstrouter(path);
                System.out.println("Host " + name + " send Data: " + str);
                if (stackData.size() != 0) {
                    r.setData(str, false);
                } else {
                    r.setData(str, true);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
