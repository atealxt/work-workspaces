/*
 * 1.如果请求地址有转发或者验证功能，则有可能抓取失败。这在动态网站比较常见。
 * 2.如果请求地址的Header不含charset=***，可能发生乱码问题。
 */
package test_javase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Test_url {

    private static String DEFAULT_FILE_OUTPUT_PATH = "E:/Data/a.html";
    private static String charset = Charset.defaultCharset().displayName();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            charset = args[0];
        }

        //test
        connect("http://forum.ubuntu.org.cn/");
    }

    private static void connect(String sUrl) {

        try {
            URL url = new URL(sUrl);
            URLConnection con = url.openConnection();
            //con.connect();

            System.out.println(con.getHeaderFields());
            setCharset(con.getHeaderField("Content-type"));
            if (con.getDoInput()) {
                catchContentToFile(new BufferedReader(new InputStreamReader(con.getInputStream(), charset)));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Test_url.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test_url.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void catchContentToFile(BufferedReader reader) {
        try {

            File file = new File(DEFAULT_FILE_OUTPUT_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));

            int i = 0;
            while ((i = reader.read()) != -1) {
                writer.write(i);
            }
            writer.close();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Test_url.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setCharset(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String pattern = "charset=";
        String[] strArr = str.split(";");
        for (String s : strArr) {
            if (s.contains(pattern)) {
                int i = s.indexOf(pattern);
                charset = s.substring(i + pattern.length()).trim();
                break;
            }
        }
    }
}
