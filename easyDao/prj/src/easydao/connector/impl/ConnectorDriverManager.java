package easydao.connector.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import easydao.connector.Connector;
import easydao.connector.MultiDataSourceConnector;
import easydao.utils.PropertiesUtils;

public final class ConnectorDriverManager implements MultiDataSourceConnector {

    private static final Logger logger = Logger.getLogger(ConnectorDriverManager.class);
    private static final ConnectorDriverManager INSTANCE = new ConnectorDriverManager();
    private static final String KEY_URL = "jdbc.url";
    private static final String KEY_USERNAME = "jdbc.username";
    private static final String KEY_PASSWORD = "jdbc.password";
    private static final String URL = PropertiesUtils.getValue(KEY_URL);
    private static final String USERNAME = PropertiesUtils.getValue(KEY_USERNAME);
    private static final String PASSWORD = PropertiesUtils.getValue(KEY_PASSWORD);

    static {
        try {
            for (String s : PropertiesUtils.keys()) {
                if (!s.startsWith("jdbc.driverClassName")) {
                    continue;
                }
                Class.forName(PropertiesUtils.getValue(s));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private ConnectorDriverManager() {}

    public static Connector getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public Connection getConnection(final String dataSourceName) throws SQLException {
        return DriverManager.getConnection(PropertiesUtils.getValue(KEY_URL + "." + dataSourceName),//
                                           PropertiesUtils.getValue(KEY_USERNAME + "." + dataSourceName),//
                                           PropertiesUtils.getValue(KEY_PASSWORD + "." + dataSourceName));
    }
}
