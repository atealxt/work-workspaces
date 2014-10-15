package easycache.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertiesUtils {

    private static Logger logger = Logger.getLogger(PropertiesUtils.class);
    private static Properties properties = new Properties();

    public static void load(final String fileName) throws FileNotFoundException, IOException {
        final String baseDir = PropertiesUtils.class.getResource("/").getPath();
        final FileInputStream inputStream = new FileInputStream(new File(baseDir.concat(fileName)));
        properties.load(inputStream);
        inputStream.close();
    }

    private PropertiesUtils() {}

    static {
        try {
            PropertiesUtils.load("/cache.properties");
        } catch (final FileNotFoundException e) {
            logger.warn("'cache.properties' not found, use default.");
            try {
                PropertiesUtils.load("/cache_default.properties");
            } catch (final Exception e1) {
                logger.error(e.getMessage(), e);
            }
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getValue(final String key) {
        return properties.getProperty(key);
    }
}
