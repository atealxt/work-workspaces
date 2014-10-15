package substitute.plugin.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

import substitute.AnalyzeTimeMonitor;
import substitute.AnalyzePrintable;
import substitute.AnalyzePrinter;
import substitute.Analyzer;
import substitute.model.AnalyzeResult;
import substitute.model.Parameter;

public class AnalyzerHttpClient implements Analyzer, AnalyzePrintable, Observer {

    private List<Worker> workers = new ArrayList<Worker>();
    private AtomicInteger living;
    private int requests = 1;

    @Override
    public void analyze(final Parameter p) throws IOException {
        requests = p.getRequests();
        int count = p.getConcurrency() * p.getRequests();
        living = new AtomicInteger(count);

        for (int i = 1; i <= p.getConcurrency(); i++) {
            Worker w = new Worker(p, i);
            w.addObserver(this);
            workers.add(w);
            new Thread(w).start();
        }
        if (p.getTimeLimit() > 0) {
            AnalyzeTimeMonitor.getInstance(p).start();
        }
    }

    @Override
    public void update(final Observable o, final Object arg) {
        int i = living.addAndGet(-1);
        if (i == 0) {
            AnalyzePrinter.print2Console(packagingResult());
        }
    }

    @Override
    public List<AnalyzeResult> packagingResult() {
        List<AnalyzeResult> results = new ArrayList<AnalyzeResult>(workers.size());
        for (Worker worker : workers) {
            for (int j = 0; j < requests; j++) {
                AnalyzeResult result = new AnalyzeResult();
                result.setThreadName(worker.getThreadName().get(j));
                result.setStartTime(worker.getStartTime().get(j));
                result.setDuring(worker.getDuring().get(j));
                result.setStatus(worker.getStatus().get(j));
                result.setBytes(worker.getBytes().get(j));
                results.add(result);
            }
        }
        return results;
    }

    @Override
    public void showHelp() {
        System.out.println("Options are:\n");
        System.out.println("    -n requests     Number of requests to perform");
        System.out.println("    -c concurrency  Number of multiple requests to make");
        System.out.println("    -b buffer size  ");
        System.out.println("    -C attribute    Add cookie, eg. 'Apache=1234' (repeatable)");
        System.out.println("    -H attribute    Add Arbitrary header line, eg. 'Accept-Encoding:gzip' 注意中间不能有空格！");
        System.out.println("                    Inserted after all normal header lines. (repeatable)");
        System.out.println("    -k              Use HTTP KeepAlive feature");
        System.out.println("    -i              Use HEAD instead of GET");
        System.out.println("    -m              Use HTTP method. 0-Get 1-HEAD 2-POST (default GET)");
        System.out
                .println("    -p postfile     File containing data to POST (ParameterName=FilePath), eg. 'userfile=D:/upload.jpg'");
        System.out.println("    -t timelimit    Seconds to max. wait for responses");
        System.out.println("    -h              Display usage information (this message)");
    }
}
