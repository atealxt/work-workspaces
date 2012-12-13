package articleanalyzer.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import easydao.dbutils.DBUtils;

public class ArticleTypeDao {

    public static void removeAll(final DBUtils dBUtils) {
        try {
            dBUtils.execute("delete from article_type");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(final DBUtils dBUtils, final int id, final String name) {
        try {
            dBUtils.execute("insert into article_type values(?,?)", id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int fetchMaxTypeId(final DBUtils dBUtils) {
        try {
            return (Integer) (dBUtils.query("select max(id) max from article_type").get(0).get("max"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Map<String, Object>> fetchTypes(final DBUtils dBUtils) {
        try {
            return dBUtils.query("select * from article_type");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
