package substitute;

import java.io.IOException;
import java.util.Arrays;

import substitute.model.Parameter;
import substitute.plugin.httpclient.AnalyzerHttpClient;

/**
 * 测试方法<br>
 * java substitute.MainClass -h<br>
 * java substitute.MainClass -c 5 -n 5 http://www.baidu.com/<br>
 * java substitute.MainClass -c 5 -n 5 -b 10240 -C k=v -H Accept-Encoding:gzip -k -t 10000 http://www.baidu.com/
 */
public class MainClass {

    private static Analyzer analyzer;

    public static void main(final String args[]) {
        System.out.println("Parameters: " + Arrays.toString(args));
        analyzer = getAnalyzer();
        Parameter p = ParameterManager.genParameter(args);
        if (p == null) {
            analyzer.showHelp();
            return;
        }
        try {
            analyzer.analyze(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Analyzer getAnalyzer() {
        return new AnalyzerHttpClient();
        // return new AnalyzerJmeter();
    }
}
