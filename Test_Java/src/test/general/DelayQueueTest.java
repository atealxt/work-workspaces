package test.general;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DelayQueueTest {

    private static DelayQueue<DelayItem<String>> queue = new DelayQueue<DelayItem<String>>();

    public static void main(final String args[]) throws InterruptedException {
        System.out.println("test start at: " + new Date());
        queue.add(new DelayItem<String>("a", 5000));
        Thread.sleep(1000);
        queue.add(new DelayItem<String>("b", 5000));

        DelayItem<String> item = null;
        while ((item = queue.poll(5001, TimeUnit.MILLISECONDS)) != null) {
            if (item != null) {
                System.out.println("get data " + item.getItem() + " at: " + new Date());
            }
        }
        System.out.println("test end");
    }
}

class DelayItem<T> implements Delayed {

    private static final long currentTimeMillis = System.currentTimeMillis();// 用时间戳来模拟
    private final long time;
    private final T item;
    private static final AtomicLong sequencer = new AtomicLong(0);
    private final long sequenceNumber;

    final static long now() {
        return System.currentTimeMillis() - currentTimeMillis;
    }

    public DelayItem(final T item, final long timeout) {
        this.time = now() + timeout;
        this.item = item;
        this.sequenceNumber = sequencer.getAndIncrement();
    }

    @Override
    public int compareTo(final Delayed other) {
        if (other == this) {
            return 0;
        }
        @SuppressWarnings("rawtypes") final DelayItem x = (DelayItem) other;
        final long diff = time - x.time;
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else if (sequenceNumber < x.sequenceNumber) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public long getDelay(final TimeUnit unit) {
        final long d = unit.convert(time - now(), TimeUnit.MILLISECONDS);
        return d;
    }

    public T getItem() {
        return item;
    }
}
