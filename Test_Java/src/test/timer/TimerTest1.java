package test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest1 {

    Timer timer;

    public TimerTest1(final int millis) {
        timer = new Timer();
        timer.schedule(new TimerTestTask(), millis);
    }

    class TimerTestTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("In TimerTestTask, execute run method.");
            timer.cancel();
        }
    }

    public static void main(final String args[]) {
        new TimerTest1(3000);
    }

}
