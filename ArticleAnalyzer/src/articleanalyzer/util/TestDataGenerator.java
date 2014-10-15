package articleanalyzer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** 初始化正确的文章和文件夹的对应关系 */
public class TestDataGenerator {

    public static void main(final String args[]) throws IOException {

        File directory = new File(ARTICLE_HOME);
        for (String dir : directory.list()) {
            System.out.println("Generate " + dir);

            File f = new File(RELATION_PATH + dir + ".txt");
            if (!f.createNewFile()) {
                System.out.println("create file rror");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            File subDirectory = new File(ARTICLE_HOME + dir);
            for (String subDir : subDirectory.list()) {
                writer.write(subDir + "\n");
            }
            writer.close();

            System.out.println("Generate " + dir + " success.");
        }
    }

    private final static String ARTICLE_HOME = PropertiesUtils.getValue("artileanalyzer.article.home");
    private final static String RELATION_PATH = PropertiesUtils.getValue("artileanalyzer.article.relations.path");
}
