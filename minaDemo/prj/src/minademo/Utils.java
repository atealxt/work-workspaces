package minademo;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {

    public static String addCurrentTime(final String fileName) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        StringBuilder trueFileName = new StringBuilder();

        int indexDot = fileName.lastIndexOf(".");
        trueFileName.append(fileName.substring(0, indexDot));
        trueFileName.append("_");
        trueFileName.append(format.format(new Date()));
        trueFileName.append(fileName.substring(indexDot));

        return trueFileName.toString();
    }
}
