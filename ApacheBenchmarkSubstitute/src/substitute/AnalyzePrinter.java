package substitute;

import java.util.List;

import substitute.model.AnalyzeResult;

public class AnalyzePrinter {

    public static void print2Console(final List<AnalyzeResult> results) {
        System.out.println("output result:\n");
        System.out.println("No.\tThreadName\tstartTime\tduring\tStatus\tBytes");

        int i = 0;
        float sumTime = 0;
        float kbReceived = 0;

        for (AnalyzeResult result : results) {

            System.out.print(++i + "\t");
            System.out.print(result.getThreadName() + "\t");
            System.out.print(result.getStartTime() + "\t");
            System.out.print(result.getDuring() + "\t");
            System.out.print(result.getStatus() + "\t");
            System.out.print(result.getBytes() + "\t");

            System.out.print("\n");

            sumTime += result.getDuring();
            kbReceived += result.getBytes();
        }

        System.out.println();
        System.out.println("Samples: " + i);
        System.out.println("Average cost time: " + Math.round((sumTime / i)));
        System.out.println("Requests per second: " + Math.round(i / (sumTime / 1000)));
        System.out.println("Transfer rate: " + Math.round((kbReceived / sumTime)) + "[Kbytes/sec] received");

        AnalyzeTimeMonitor.stopMonitor();
    }
}
