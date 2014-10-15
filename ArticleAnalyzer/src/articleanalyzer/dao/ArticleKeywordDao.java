package articleanalyzer.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import easydao.dbutils.DBUtils;

public class ArticleKeywordDao {

    public static void removeAll(final DBUtils dBUtils) {
        try {
            dBUtils.execute("delete from article_keyword");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(final DBUtils dBUtils, final int type, final String keyword, final double weight) {
        try {
            dBUtils.execute("insert into article_keyword(type,keyword,weight) values(?,?,?)", type, keyword, weight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> fetchKeywordAndWeightByTypeId(final DBUtils dBUtils, final int type) {
        try {
            return dBUtils.query("select keyword,weight from article_keyword where type = ?", type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
