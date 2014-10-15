/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CallableAndFutureTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    void start() throws Exception {
        final Callable<List<Integer>> task = new Callable<List<Integer>>() {

            public List<Integer> call() throws Exception {
                // get obj
                final List<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(50);
                    list.add(i);
                }
                return list;
            }
        };
        final Future<List<Integer>> future = executor.submit(task);

        //do sthing others..
        //example: due to show some data..

        try {
            final List<Integer> list = future.get(); //这个函数还可以接受超时检测http://www.javaeye.com/topic/671314
            System.out.println(list);
        } catch (final InterruptedException ex) {
            Logger.getLogger(CallableAndFutureTest.class.getName()).log(Level.SEVERE, null, ex);
            future.cancel(true);
        } catch (final ExecutionException ex) {
            Logger.getLogger(CallableAndFutureTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExecutionException(ex);
        } finally {
            executor.shutdown();
        }
    }

    public static void main(final String args[]) {
        try {
            new CallableAndFutureTest().start();
        } catch (final Exception ex) {
            Logger.getLogger(CallableAndFutureTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
