/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CompletionServiceTest {

    public static void solve(final Executor e, final Collection<Callable<Object>> solvers)
        throws InterruptedException, ExecutionException {
        CompletionService<Object> ecs = new ExecutorCompletionService<Object>(e);
        for (Callable<Object> s : solvers) {
            ecs.submit(s);
        }
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Future<Object> f = ecs.take();
            Object r = f.get();
            if (r != null) {
                System.out.println(r);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Callable<Object> task1 = new Callable<Object>() {

            public List<Integer> call() throws Exception {
                List<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(50);
                    list.add(i);
                }
                return list;
            }
        };

        Callable<Object> task2 = new Callable<Object>() {

            public List<Integer> call() throws Exception {
                List<Integer> list = new ArrayList<Integer>();
                for (int i = 100; i < 200; i++) {
                    Thread.sleep(50);
                    list.add(i);
                }
                return list;
            }
        };
        List<Callable<Object>> solvers = new ArrayList<Callable<Object>>();
        solvers.add(task1);
        solvers.add(task2);

        ExecutorService e = Executors.newFixedThreadPool(2);
        //if use executor.invokeAny,next line will not be excute util all above task complete.

        try {
            CompletionServiceTest.solve(e, solvers);
        } catch (InterruptedException ex) {
            Logger.getLogger(CompletionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CompletionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            e.shutdown();
        }

    }
}
