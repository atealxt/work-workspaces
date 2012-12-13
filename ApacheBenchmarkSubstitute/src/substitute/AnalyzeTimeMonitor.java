package substitute;

import substitute.model.Parameter;

public class AnalyzeTimeMonitor extends Thread {

    private static boolean doMonitor = true;
    private static AnalyzeTimeMonitor monitor;

    public static AnalyzeTimeMonitor getInstance(final Parameter p) {
        synchronized (AnalyzeTimeMonitor.class) {
            if (monitor == null) {
                monitor = new AnalyzeTimeMonitor(p);
            }
        }
        return monitor;
    }

    private int timeLimit;

    private AnalyzeTimeMonitor(final Parameter p) {
        super();
        timeLimit = p.getTimeLimit();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (doMonitor) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - startTime > (timeLimit * 1000)) {
                throw new RuntimeException("Analyze timeout, system exit now.");
            }
        }
    }

    public static void stopMonitor() {
        synchronized (AnalyzeTimeMonitor.class) {
            doMonitor = false;
        }
    }
}
