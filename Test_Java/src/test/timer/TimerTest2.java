/*
 A Timer creates only a single thread for executing timer tasks.
 If a timer task takes too long to run, the timing accuracy of other TimerTasks can suffer.
 If a recurring TimerTask is scheduled to run every 10 ms and another Timer-Task takes 40 ms to run,
 the recurring task either (depending on whether it was scheduled at fixed rate or fixed delay)
 gets called four times in rapid succession after the long-running task completes,
 or "misses" four invocations completely.
 Scheduled thread pools address this limitation by letting you provide multiple
 threads for executing deferred and periodic tasks.

 Another problem with Timer is that it behaves poorly if a TimerTask throws an unchecked exception.
 The Timer thread doesn't catch the exception,
 so an unchecked exception thrown from a TimerTask terminates the timer thread.
 Timer also doesn't resurrect the thread in this situation;
 instead, it erroneously assumes the entire Timer was cancelled.
 In this case, TimerTasks that are already scheduled but not yet executed are never run,
 and new tasks cannot be scheduled.
 */
package test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest2 {

    static void throwMethod() throws Exception {
        throw new Exception("test throwMethod");
    }

    static class ThrowTask extends TimerTask {

        //TimerTask's method run cannot throw exception except RuntimeException.
        @Override
        public void run() {
            throw new RuntimeException("test ThrowTask");
        }
    }

    public static void main(final String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        System.out.println("aaa");
        Thread.sleep(100);
        //now exception happend,should stop run but not.
        System.out.println("bbb");
        throwMethod();
        //unreachable
        System.out.println("ccc");
    }
}
