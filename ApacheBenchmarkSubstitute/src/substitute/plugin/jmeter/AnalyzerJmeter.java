package substitute.plugin.jmeter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import substitute.AnalyzePrintable;
import substitute.AnalyzePrinter;
import substitute.Analyzer;
import substitute.model.AnalyzeResult;
import substitute.model.Parameter;
import substitute.util.PropertiesUtils;

public class AnalyzerJmeter implements Analyzer, AnalyzePrintable {

    public void analyze(final Parameter p) throws IOException {
        clearOutputFile();
        initTestFile(p);
        execute();
        AnalyzePrinter.print2Console(packagingResult());
        clearTestFile();
    }

    private void initTestFile(final Parameter p) throws IOException {
        File f = new File(TEST_FILE + ".temp");
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new RuntimeException("create file error: " + TEST_FILE + ".temp");
            }
        }
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(TEST_FILE), 1024);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        String s = null;
        while ((s = reader.readLine()) != null) {
            writer.write(replace(s, p) + "\n");
        }
        reader.close();
        writer.close();
    }

    private String replace(final String s, final Parameter p) {
        if (!searchedParameterLoop && s.indexOf("LoopController.loops") != -1) {
            searchedParameterLoop = true;
            return s.substring(0, s.indexOf(">") + 1) + p.getRequests() + s.substring(s.lastIndexOf("</"));
        }
        if (!searchedParameterThreads && s.indexOf("ThreadGroup.num_threads") != -1) {
            searchedParameterThreads = true;
            return s.substring(0, s.indexOf(">") + 1) + p.getConcurrency() + s.substring(s.lastIndexOf("</"));
        }
        return s;
    }

    private void execute() {
        String cmd = "cmd /c " + JMETER_HOME + "/bin/" + "jmeter -n -t " + TEST_FILE + ".temp -l " + OUTPUT_FILE;
        try {
            Process process = Runtime.getRuntime().exec(cmd);

            InputStreamReader error = new InputStreamReader(process.getErrorStream());
            InputStreamReader reader = new InputStreamReader(process.getInputStream());

            BufferedReader brError = new BufferedReader(error);
            BufferedReader brReader = new BufferedReader(reader);

            String s = null;
            while ((s = brError.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = brReader.readLine()) != null) {
                System.out.println(s);
            }

            brError.close();
            brReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AnalyzeResult> packagingResult() {
        List<AnalyzeResult> results = new ArrayList<AnalyzeResult>();
        BufferedInputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(OUTPUT_FILE), 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LineNumberReader reader = new LineNumberReader(bufferedReader, 1024);
        String s = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.S");

        try {
            while ((s = reader.readLine()) != null) {
                if ("".equals(s)) {
                    break;
                }
                if (reader.getLineNumber() < 3) {
                    continue;
                }
                Map<String, String> m = getValueMap(s);

                AnalyzeResult result = new AnalyzeResult();
                result.setThreadName(m.get("tn"));
                result.setStartTime(format.format(new Date(Long.valueOf(m.get("ts")))));
                result.setDuring(Integer.valueOf(m.get("t")));
                result.setStatus(Integer.valueOf(m.get("rc")));
                result.setBytes(Long.valueOf(m.get("by")));
                results.add(result);
                results.add(result);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private Map<String, String> getValueMap(final String input) {
        Map<String, String> map = new HashMap<String, String>();
        Matcher m = PATTERN_OUTPUT.matcher(input);
        while (m.find()) {
            String sTemp = input.substring(m.start(), m.end());
            map.put(sTemp.substring(0, sTemp.indexOf("=")), sTemp.substring(sTemp.indexOf("=") + 2, sTemp.length() - 1));
        }
        return map;
    }

    private void clearOutputFile() {
        delFile(OUTPUT_FILE);
    }

    private void clearTestFile() {
        delFile(TEST_FILE + ".temp");
    }

    private void delFile(final String path) {
        File f = new File(path);
        if (f.exists()) {
            if (!f.delete()) {
                System.out.println("Delete file error: " + path);
            }
        }
    }

    private boolean searchedParameterLoop = false;
    private boolean searchedParameterThreads = false;

    private final static String OUTPUT_FILE = PropertiesUtils.getValue("substitute.plugin.jmeter.outputfile");
    private final static String JMETER_HOME = PropertiesUtils.getValue("substitute.plugin.jmeter.home");
    private final static String TEST_FILE = PropertiesUtils.getValue("substitute.plugin.jmeter.testfile");

    private static Pattern PATTERN_OUTPUT = Pattern.compile("\\w*=\"[^\"]*\"");

    @Override
    public void showHelp() {
        System.out.println("Options are:\n");
        System.out.println("    -n requests     Number of requests to perform");
        System.out.println("    -c concurrency  Number of multiple requests to make");
        System.out.println("    -h              Display usage information (this message)");
    }
}
