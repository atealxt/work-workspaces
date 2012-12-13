/*
 * use this in Java 5.0 or later to replace Timer.
 *
 * scheduleAtFixedRate:
 * 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
 * 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，
 * 接着在 initialDelay + 2 * period 后执行，依此类推。如果任务的任何一个执行遇到异常，则后续执行都会被取消。
 * 否则，只能通过执行程序的取消或终止方法来终止该任务。如果此任务的任何一个执行要花费比其周期更长的时间，
 * 则将推迟后续执行，但不会同时执行。
 *
 * scheduleWithFixedDelay:
 * see jdk document.
 */
package test.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int count = 0;

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {

            public void run() {
                if (count == 5) {
                    throw new RuntimeException("test");
                }
                System.out.println(count++);
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 5, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {

            public void run() {
                System.out.println("cancel!");
                beeperHandle.cancel(true);
                scheduler.shutdown();
            }
        }, 15, TimeUnit.SECONDS);
    }

    public static void main(final String args[]){
        new ExecutorTest().beepForAnHour();
    }
}
