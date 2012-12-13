package test_lab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Router.
 */
public class Router implements Runnable {

    private String name;
    private String data;
    private String nextpath;
    private boolean isEndData;

    /**
     * @param name Router name.
     */
    public Router(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        accept();
    }

    /**
     * Transport path.
     */
    public void setNextpath(String str) {

        //waiting data send
        while (this.data != null) {
            waiting(100);
        }
        this.nextpath = str;
    }

    /**
     * Transport data.
     */
    public void setData(String data, boolean isEndData) {

        //waiting data send
        while (this.data != null) {
            waiting(100);
        }
        //System.out.println("Router " + name + " get Data. ");
        this.data = data;
        this.isEndData = isEndData;
    }

    private void waiting(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void accept() {

        while (true) {
            //listening whether data is null
            if (data != null) {
                send();
            }
            waiting(500);
        }
    }

    private void send() {

        //System.out.println("Router " + name + " send Data.");
        if (!nextpath.equals("")) {
            Router router = LabUtil.getNextrouter(nextpath);
            router.setData(data, isEndData);
        } else {
            LabUtil.HOST_RECEIVE.setData(data, isEndData);
        }
        data = null;
    }
}
