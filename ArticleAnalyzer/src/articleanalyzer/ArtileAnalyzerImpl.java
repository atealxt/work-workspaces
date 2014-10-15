package articleanalyzer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import articleanalyzer.dao.ArticleKeywordDao;
import articleanalyzer.dao.ArticleTypeDao;
import articleanalyzer.plugin.lucene.LuceneWordSpliter;
import articleanalyzer.util.PropertiesUtils;
import easydao.dbutils.DBUtils;

public class ArtileAnalyzerImpl implements ArtileAnalyzer {

    private TermSpliter spliter;

    public ArtileAnalyzerImpl() {
        spliter = new LuceneWordSpliter();
    }

    /** 生成索引数据 */
    @Override
    public void index() {

        DBUtils dBUtils = new DBUtils();
        ArticleKeywordDao.removeAll(dBUtils);
        ArticleTypeDao.removeAll(dBUtils);

        int i = 0;
        File articleDir = new File(ARTICLE_HOME);
        for (String dir : articleDir.list()) {
            File subDir = new File(ARTICLE_HOME + dir);
            if (!subDir.isDirectory()) {
                continue;
            }
            System.out.println("Creating index " + ARTICLE_HOME + dir + " " + dir);
            ArticleTypeDao.insert(dBUtils, ++i, dir);
            try {
                index(ARTICLE_HOME + dir, dir, i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void index(final String artileDir, final String indexType, final int indexId) throws IOException {

        File artileFileDir = new File(artileDir);

        String[] arrFile = artileFileDir.list();
        int fileCnt = arrFile.length;

        // Document Frequency. 有多少文档包含此词
        Map<String, Integer> mapDF = new HashMap<String, Integer>();
        for (String dir : arrFile) {
            Reader reader = new FileReader(artileDir + "/" + dir);

            boolean calculatedDf = false;

            for (String term : spliter.term(reader)) {
                if (!chkKeyWord(term)) {
                    continue;
                }

                if (!mapDF.containsKey(term)) {
                    mapDF.put(term, 1);
                } else if (!calculatedDf) {
                    mapDF.put(term, mapDF.get(term) + 1);
                    calculatedDf = true;
                }
            }

            reader.close();
        }

        Map<String, Double> mapWeight = new HashMap<String, Double>();

        for (String dir : arrFile) {
            Reader reader = new FileReader(artileDir + "/" + dir);

            // Term Frequency. 此词在此文档中出现了多少次
            Map<String, Integer> mapTF = new HashMap<String, Integer>();

            for (String term : spliter.term(reader)) {
                if (!chkKeyWord(term)) {
                    continue;
                }

                Integer j = mapTF.get(term);
                if (j == null) {
                    mapTF.put(term, 1);
                } else {
                    mapTF.put(term, j + 1);
                }
            }

            reader.close();

            for (Entry<String, Integer> entry : mapTF.entrySet()) {
                int df = mapDF.get(entry.getKey());
                if (df == 0) {
                    continue;
                }
                // 该词在该文档的权重
                double weight = entry.getValue() * Math.log(fileCnt / (double) df);

                if (!mapWeight.containsKey(entry.getKey())) {
                    mapWeight.put(entry.getKey(), weight);
                } else {
                    mapWeight.put(entry.getKey(), mapDF.get(entry.getKey()) + weight);
                }
            }
        }

        DBUtils dbUtils = new DBUtils(false);
        try {
            for (Entry<String, Double> entry : mapWeight.entrySet()) {
                double weight = (entry.getValue()) / fileCnt;
                weight = addSalt(indexId, weight, entry.getKey());
                ArticleKeywordDao.insert(dbUtils, indexId, entry.getKey(), weight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.closeConnection();
        }
    }

    @Override
    public void statistics() {
        System.out.println();
        File articleDir = new File(ARTICLE_HOME);
        for (String f : articleDir.list()) {
            File file = new File(ARTICLE_HOME + f);
            if (!file.isFile()) {
                continue;
            }
            System.out.println("Statistics File: " + f);
            statistics(f);
        }
    }

    private void statistics(final String fileName) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            Reader reader = new FileReader(ARTICLE_HOME + fileName);

            for (String term : spliter.term(reader)) {
                if (!chkKeyWord(term)) {
                    continue;
                }

                Integer i = map.get(term);
                if (i == null) {
                    map.put(term, 1);
                } else {
                    map.put(term, i + 1);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int articleType = 0;
        double scoreMax = 0;
        DBUtils dbUtils = new DBUtils(false);
        try {
            int typeCnt = ArticleTypeDao.fetchMaxTypeId(dbUtils);
            for (int i = 1; i <= typeCnt; i++) {
                List<Map<String, Object>> list = ArticleKeywordDao.fetchKeywordAndWeightByTypeId(dbUtils, i);
                double score = 0;
                for (Map<String, Object> m : list) {
                    String keyword = (String) m.get("keyword");
                    if (map.containsKey(keyword)) {
                        // 该词在文章中出现的次数 * 该词的平均权重
                        double d = (map.get(keyword) * (Double) m.get("weight"));
                        score += d;
                    }
                }

                if (scoreMax < score) {
                    scoreMax = score;
                    articleType = i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.closeConnection();
        }
        statResult.put(fileName, articleType);
    }

    @Override
    public void analyzeResult() {
        System.out.println("\nAnalyze result: \n");
        List<Map<String, Object>> types = ArticleTypeDao.fetchTypes(new DBUtils());
        int sum = 0, ok = 0, error = 0;
        try {
            for (Entry<String, Integer> entry : statResult.entrySet()) {
                System.out.print(entry.getKey());
                String testFileName = entry.getKey();
                String relationFileName = getRelFileName(entry.getValue(), types);
                boolean assertAnalyzeTrue = false;
                if (relationFileName != null) {
                    assertAnalyzeTrue = assertAnalyzeTrue(testFileName, relationFileName);
                }
                System.out.print(": " + assertAnalyzeTrue);
                sum++;
                if (assertAnalyzeTrue) {
                    ok++;
                } else {
                    error++;
                    System.out.print(" (calculated to " + relationFileName + ")");
                }
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sum == 0) {
            return;
        }
        double d1 = ok;
        double d2 = sum;
        BigDecimal rate = new BigDecimal(d1 / d2 * 100);
        String sRate = rate.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%";
        System.out.println("\nSUM: " + sum + " OK: " + ok + " ERROR: " + error + " ACCURACY: " + sRate);
    }

    private boolean chkKeyWord(final String term) {
        if (PATTERN_NUMBER.matcher(term).matches()) {
            // 忽略数字关键词
            return false;
        }
        if (term.length() < KEYWORD_MIN_LEN) {
            return false;
        }
        if (term.length() > KEYWORD_MAX_LEN) {
            return false;
        }
        return true;
    }

    private String getRelFileName(final Integer value, final List<Map<String, Object>> types) {
        for (Map<String, Object> m : types) {
            if (m.get("ID").equals(value)) {
                return (String) m.get("NAME");
            }
        }
        return null;
    }

    private boolean assertAnalyzeTrue(final String testFileName, final String relationFileName) throws IOException {
        File f = new File(REL_FILE_PATH + relationFileName + ".txt");
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(f), 1024);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String s = null;
            while ((s = reader.readLine()) != null) {
                if (s.equals(testFileName)) {
                    return true;
                }
            }
        } finally {
            reader.close();
        }
        return false;
    }

    /** TODO 加的盐效果不好，待提高 */
    private double addSalt(final int type, final double weight, final String key) {
        double weightSalt = weight;
        boolean addSaltGrammar = false;
        boolean addSaltName = false;
        if (type == 1) {
            // alt.bible.prophecy
            addSaltName = hit(key, NAME_BIBLE_PROPHECY);
        } else if (type == 2) {
            // comp.lang.javascript
            addSaltGrammar = hit(key, KEYWORDS_JAVASCRIPT);
            addSaltName = hit(key, NAME_JAVASCRIPT);
        } else if (type == 4) {
            // comp.lang.python
            addSaltGrammar = hit(key, KEYWORDS_PYTHON);
        }
        if (addSaltGrammar) {
            weightSalt = weight * SALT_GRAMMAR;
        }
        if (addSaltName) {
            weightSalt = weight * SALT_NAME;
        }
        return weightSalt;
    }

    private boolean hit(final String key, final String[] keys) {
        for (String k : keys) {
            if (key.contains(k)) {
                return true;
            }
        }
        return false;
    }

    /**key:文件名 value:typeId*/
    private Map<String, Integer> statResult = new LinkedHashMap<String, Integer>();

    private final static Pattern PATTERN_NUMBER = Pattern.compile("^\\d*$");
    private final static String ARTICLE_HOME = PropertiesUtils.getValue("artileanalyzer.article.home");
    private final static int KEYWORD_MIN_LEN = Integer.parseInt(PropertiesUtils
            .getValue("artileanalyzer.article.keyword.minlength"));
    private final static int KEYWORD_MAX_LEN = Integer.parseInt(PropertiesUtils
            .getValue("artileanalyzer.article.keyword.maxlength"));
    private final static String REL_FILE_PATH = PropertiesUtils.getValue("artileanalyzer.article.relations.path");

    private final static String[] KEYWORDS_PYTHON = "and assert break class continue def del elif else except exec finally for from global if import in is lambda not or pass print raise return try yield while"
            .split(" ");
    private final static String[] KEYWORDS_JAVASCRIPT = "break case catch continue default delete do else false for function if in instanceof new null return super switch this throw true try typeof var while with window xmlhttp"
            .split(" ");

    private final static String[] NAME_BIBLE_PROPHECY = "bible prophecy".split(" ");
    private final static String[] NAME_JAVASCRIPT = "javascript".split(" ");

    private final static double SALT_NAME = Double.parseDouble(PropertiesUtils
            .getValue("artileanalyzer.article.keyword.salt.name"));

    private final static double SALT_GRAMMAR = Double.parseDouble(PropertiesUtils
            .getValue("artileanalyzer.article.keyword.salt.grammar"));
}
