package test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolExecutorTest {

    //拿异步保存日志的配置举例
    private final static int corePoolSize = 2;
    private final static int maximumPoolSize = corePoolSize + 2;
    private final static long keepAliveTime = 200;
    private final static TimeUnit unit = TimeUnit.MILLISECONDS;
    private final static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(corePoolSize * 2);
    private final static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private final static RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

    public static Future<?> submit(final Runnable task) {
        return EXECUTOR.submit(task);
    }

    public static void main(final String args[]) {
        //正常提交任务
        test1();

        //超出最大线程数，提交失败
        test2();

        //测试用，直接停止线程池，终止测试 ，真正的运行环境可直接跳过这步，返回原函数调用就行了
        EXECUTOR.shutdown();
    }

    private static void test1() {
        final Future<?> future = EXECUTOR.submit(new MyService2());
        while(true){
            if(future.isDone()){
                System.out.println("complete: " + future.isDone());
                break;
            }else{
                System.out.println("complete: " + future.isDone());
                try {
                    Thread.sleep(1000 * 1);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test2() {
        for(int i = 0;i<20;i++){
            try {
                EXECUTOR.submit(new MyService2());
            } catch (final RejectedExecutionException e) {
                System.out.println("提交任务失败，线程池已满。");
            }
        }
    }


}

class MyService2 implements Runnable {

    public MyService2() {
    }

    public void run() {
        System.out.println("task start");
        try {
            Thread.sleep(1000 * 10);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task end");
    }
}


/*

整个ThreadPoolExecutor的任务处理有4步操作：

    第一步，初始的poolSize < corePoolSize，提交的runnable任务，会直接做为new一个Thread的参数，立马执行
    第二步，当提交的任务数超过了corePoolSize，就进入了第二步操作。会将当前的runable提交到一个block queue中
    第三步，如果block queue是个有界队列，当队列满了之后就进入了第三步。如果poolSize < maximumPoolsize时，会尝试new 一个Thread的进行救急处理，立马执行对应的runnable任务
    第四步，如果第三步救急方案也无法处理了，就会走到第四步执行reject操作。

几点说明：(相信这些网上一搜一大把，我这里简单介绍下，为后面做一下铺垫)

    block queue有以下几种实现：
    1. ArrayBlockingQueue :  有界的数组队列
    2. LinkedBlockingQueue : 可支持有界/无界的队列，使用链表实现
    3. PriorityBlockingQueue : 优先队列，可以针对任务排序
    4. SynchronousQueue : 队列长度为1的队列，和Array有点区别就是：client thread提交到block queue会是一个阻塞过程，直到有一个worker thread连接上来poll task。
    RejectExecutionHandler是针对任务无法处理时的一些自保护处理：
    1. Reject 直接抛出Reject exception
    2. Discard 直接忽略该runnable，不可取
    3. DiscardOldest 丢弃最早入队列的的任务
    4. CallsRun 直接让原先的client thread做为worker线程，进行执行


容易被人忽略的点：
1.  pool threads启动后，以后的任务获取都会通过block queue中，获取堆积的runnable task.

所以建议： block size >= corePoolSize ，不然线程池就没任何意义
2.  corePoolSize 和 maximumPoolSize的区别， 和大家正常理解的数据库连接池不太一样。
  *  据dbcp pool为例，会有minIdle , maxActive配置。minIdle代表是常驻内存中的threads数量，maxActive代表是工作的最大线程数。
  *  这里的corePoolSize就是连接池的maxActive的概念，它没有minIdle的概念(每个线程可以设置keepAliveTime，超过多少时间多有任务后销毁线程，但不会固定保持一定数量的threads)。
  * 这里的maximumPoolSize，是一种救急措施的第一层。当threadPoolExecutor的工作threads存在满负荷，并且block queue队列也满了，这时代表接近崩溃边缘。这时允许临时起一批threads，用来处理runnable，处理完后立马退出。

所以建议：  maximumPoolSize >= corePoolSize =期望的最大线程数。 (我曾经配置了corePoolSize=1, maximumPoolSize=20, blockqueue为无界队列，最后就成了单线程工作的pool。典型的配置错误)

3. 善用blockqueue和reject组合. 这里要重点推荐下CallsRun的Rejected Handler，从字面意思就是让调用者自己来运行。
我们经常会在线上使用一些线程池做异步处理，比如我前面做的(业务层)异步并行加载技术分析和设计, 将原本串行的请求都变为了并行操作，但过多的并行会增加系统的负载(比如软中断，上下文切换)。所以肯定需要对线程池做一个size限制。但是为了引入异步操作后，避免因在block queue的等待时间过长，所以需要在队列满的时，执行一个callsRun的策略，并行的操作又转为一个串行处理，这样就可以保证尽量少的延迟影响。

所以建议：  RejectExecutionHandler = CallsRun ,  blockqueue size = 2 * poolSize (为啥是2倍poolSize，主要一个考虑就是瞬间高峰处理，允许一个thread等待一个runnable任务)

http://www.iteye.com/topic/1118660
*/
