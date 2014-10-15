package test.nio;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueTest {

    public static void main(final String[] args) {
        testConcurrentLinkedQueue();
    }

    // 当多个线程共享访问一个公共 collection 时，ConcurrentLinkedQueue 是一个恰当的选择。此队列不允许使用 null 元素。
    // 需要小心的是，与大多数 collection 不同，size 方法不是 一个固定时间操作。由于这些队列的异步特性，确定当前元素的数量需要遍历这些元素。
    private static void testConcurrentLinkedQueue() {
        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
