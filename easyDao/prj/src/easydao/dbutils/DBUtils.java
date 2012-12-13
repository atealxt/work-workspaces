package easydao.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import easydao.EasyDao;
import easydao.utils.PropertiesUtils;

public class DBUtils {

    private Connection connection;
    private boolean autoCloseConn = Boolean.parseBoolean(PropertiesUtils.getValue("jdbc.showSQL"));
    private boolean showSql = Boolean.parseBoolean(PropertiesUtils.getValue("jdbc.autoCloseConnection"));
    private boolean columnOrder = Boolean.parseBoolean(PropertiesUtils.getValue("jdbc.columnOrder"));
    private String dataSourceName = null;

    public DBUtils() {}

    public DBUtils(final boolean autoCloseConn) {
        this.autoCloseConn = autoCloseConn;
    }

    public DBUtils(final String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public DBUtils(final boolean autoCloseConn, final String dataSourceName) {
        this.autoCloseConn = autoCloseConn;
        this.dataSourceName = dataSourceName;
    }

    /** You must close connection manual. */
    public DBUtils(final Connection connection) {
        this.connection = connection;
        if (autoCloseConn) {
            autoCloseConn = false;
        }
    }

    public List<Map<String, Object>> query(final String sql, final Object... params) throws SQLException {
        QueryRunner run = new QueryRunner(true);

        Connection connection = null;
        List<Map<String, Object>> result = null;
        try {
            connection = getConnection();
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            if (!columnOrder) {
                result = run.query(connection, sql, HANDLER_MULTI, params);
            } else {
                result = run.query(connection, sql, HANDLER_MULTI_ORDER, params);
            }
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }

        return result;
    }

    public <T> T querySingle(final Class<T> clazz, final String sql, final Object... params) throws SQLException {
        ResultSetHandler<T> handler = new BeanHandler(clazz);

        QueryRunner run = new QueryRunner(true);

        T t = null;
        Connection connection = null;
        try {
            connection = getConnection();
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            t = run.query(connection, sql, handler, params);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }
        return t;
    }

    public <T> List<T> queryList(final Class<T> clazz, final String sql, final Object... params) throws SQLException {
        ResultSetHandler<List<T>> handler = new BeanListHandler(clazz);

        QueryRunner run = new QueryRunner(true);

        List<T> tList = null;
        Connection connection = null;
        try {
            connection = getConnection();
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            tList = run.query(connection, sql, handler, params);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }
        return tList;
    }

    public <T> List<T> queryList(
            final Connection connection,
            final Class<T> clazz,
            final String sql,
            final Object... params) throws SQLException {
        ResultSetHandler<List<T>> handler = new BeanListHandler(clazz);

        QueryRunner run = new QueryRunner(true);

        List<T> tList = null;
        try {
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            tList = run.query(connection, sql, handler, params);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }
        return tList;
    }

    public int count(final String sql, final Object... params) throws SQLException {

        QueryRunner run = new QueryRunner(true);

        Connection connection = null;
        Object[] result = null;
        try {
            connection = getConnection();
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            result = run.query(connection, sql, HANDLER_SINGLE, params);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }

        return Integer.parseInt(result[0].toString());
    }

    public boolean execute(final String sql, final Object... params) throws SQLException {
        QueryRunner run = new QueryRunner(true);

        Connection connection = null;
        try {
            connection = getConnection();
            if (showSql) {
                logger.debug("sql: " + sql);
                logger.debug("params: " + Arrays.toString(params));
            }
            return run.update(connection, sql, params) != 0;
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }
    }

    public void executeBatch(final String sql, final Object[][] params) throws SQLException {
        QueryRunner run = new QueryRunner(true);

        Connection connection = null;
        try {
            connection = getConnection();

            if (showSql && logger.isDebugEnabled()) {
                logger.debug("sql: " + sql);
                StringBuilder paramStr = new StringBuilder();
                for (Object[] pArr : params) {
                    paramStr.append("\n");
                    paramStr.append(Arrays.toString(pArr));
                }
                logger.debug("params: " + paramStr.toString());
            }
            run.batch(connection, sql, params);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }
    }

    /**
     * 只适用于oracle<br>
     * 由于拼sql安全问题，慎重使用
     */
    public long getSequence(final String seqName) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT " + seqName + ".NEXTVAL FROM DUAL");
        QueryRunner run = new QueryRunner(true);

        Connection connection = null;
        Object[] result = null;
        try {
            connection = getConnection();
            logger.debug("sql: " + sql);
            result = run.query(connection, sql.toString(), HANDLER_SINGLE);
        } finally {
            if (autoCloseConn) {
                closeConnection();
            }
        }

        return Long.parseLong(result[0].toString());
    }

    private Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        if (dataSourceName == null) {
            connection = EasyDao.getConnection();
        } else {
            connection = EasyDao.getConnection(dataSourceName);
        }
        connection.setAutoCommit(true);
        return connection;
    }

    public void beginTransaction() {
        try {
            Connection conn = connection;
            if (conn != null && conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            } else {
                connection = getConnection();
                conn = connection;
            }
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void commitTransaction() {
        Connection conn = connection;
        if (conn == null) {
            return;
        }
        try {
            conn.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void rollbackTransaction() {
        Connection conn = connection;
        if (conn == null) {
            return;
        }
        try {
            conn.rollback();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void closeConnection() {
        Connection conn = connection;
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static Logger logger = Logger.getLogger(DBUtils.class.getName());
    private final static ResultSetHandler<Object[]> HANDLER_SINGLE = new ResultSetHandler<Object[]>() {

        public Object[] handle(final ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            Object[] result = new Object[cols];

            for (int i = 0; i < cols; i++) {
                result[i] = rs.getObject(i + 1);
            }

            return result;
        }
    };

    private final static ResultSetHandler<List<Map<String, Object>>> HANDLER_MULTI = new ResultSetHandler<List<Map<String, Object>>>() {

        public List<Map<String, Object>> handle(final ResultSet rs) throws SQLException {

            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> keyvalue = new HashMap<String, Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    Object value = rs.getObject(columnIndex);
                    String key = rs.getMetaData().getColumnName(columnIndex);
                    keyvalue.put(key, value);
                }
                data.add(keyvalue);
            }

            return data;
        }
    };

    private final static ResultSetHandler<List<Map<String, Object>>> HANDLER_MULTI_ORDER = new ResultSetHandler<List<Map<String, Object>>>() {

        public List<Map<String, Object>> handle(final ResultSet rs) throws SQLException {

            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> keyvalue = new LinkedHashMap<String, Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    Object value = rs.getObject(columnIndex);
                    String key = rs.getMetaData().getColumnName(columnIndex);
                    keyvalue.put(key, value);
                }
                data.add(keyvalue);
            }

            return data;
        }
    };

    public void setColumnOrder(final boolean columnOrder) {
        this.columnOrder = columnOrder;
    }

    public void setShowSql(final boolean debug) {
        this.showSql = debug;
    }
}
