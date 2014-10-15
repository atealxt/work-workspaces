package test.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemTimer {

    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private static final long tickUnit = Long.parseLong(System.getProperty("notify.systimer.tick", "50"));
    private static volatile long time = System.currentTimeMillis();

    private static class TimerTicker implements Runnable {

        public void run() {
            time = System.currentTimeMillis();
        }
    }

    public static long currentTimeMillis() {
        return time;
    }

    static {
        executor.scheduleAtFixedRate(new TimerTicker(), tickUnit, tickUnit, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                executor.shutdown();
            }
        });
    }

    public static void main(final String args[]) {
        for (int i = 0; i < 10; i++) {
            System.out.println(SystemTimer.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

}


/*

网络服务器通常需要频繁地获取系统时间：定时器、协议时间戳、缓存过期等等。
System.currentTimeMillis
Linux调用gettimeofday，需要切换到内核态

在不是需要特别高特别高精度的时候，可以用
SystemTimer.currentTimeMillis
独立线程定期更新时间缓存
currentTimeMillis直接返回缓存值
精度取决于定期间隔
1000万次调用降低到59毫秒

 */