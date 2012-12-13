package substitute.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtils {

    private static Properties properties = new Properties();

    public static void load(final String fileName) throws FileNotFoundException, IOException {
        String baseDir = PropertiesUtils.class.getResource("/").getPath();
        FileInputStream inputStream = new FileInputStream(new File(baseDir.concat(fileName)));
        properties.load(inputStream);
        inputStream.close();
    }

    static {
        try {
            PropertiesUtils.load("/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(final String key) {
        return properties.getProperty(key);
    }
}
