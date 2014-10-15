package easydao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public final class PropertiesUtils {

    private static Logger logger = Logger.getLogger(PropertiesUtils.class);
    private static Properties properties = new Properties();

    public static void load(final String fileName) throws FileNotFoundException, IOException {
        String baseDir = PropertiesUtils.class.getResource("/").getPath();
        FileInputStream inputStream = new FileInputStream(new File(baseDir.concat(fileName)));
        properties.load(inputStream);
        inputStream.close();
    }

    static {
        try {
            PropertiesUtils.load("/jdbc.properties");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getValue(final String key) {
        return properties.getProperty(key);
    }

    public static Set<String> keys() {
        return properties.stringPropertyNames();
    }
}
