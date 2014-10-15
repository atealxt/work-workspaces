package easydao.connector.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import easydao.connector.Connector;
import easydao.utils.PropertiesUtils;

public final class ConnectorDbcp implements Connector {

    private static final Logger logger = Logger.getLogger(ConnectorDbcp.class);
    private static final ConnectorDbcp INSTANCE = new ConnectorDbcp();
    private static final String CLASSNAME = PropertiesUtils.getValue("jdbc.driverClassName");
    private static final String URL = PropertiesUtils.getValue("jdbc.url");
    private static final String USERNAME = PropertiesUtils.getValue("jdbc.username");
    private static final String PASSWORD = PropertiesUtils.getValue("jdbc.password");
    private static DataSource dataSource;

    static {
        try {
            PropertiesUtils.load("dbcp.properties");
            dataSource = setupDriverManualPoolingDataSource();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private ConnectorDbcp() {}

    public static Connector getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    protected static DataSource setupDriverManualPoolingDataSource() throws ClassNotFoundException, SQLException {
        Class.forName(CLASSNAME);
        Class.forName("org.apache.commons.dbcp.PoolingDriver");

        GenericObjectPool connectionPool = new GenericObjectPool();
        connectionPool.setMaxActive(Integer.parseInt(PropertiesUtils.getValue("dbpool.maxActive")));
        connectionPool.setMaxIdle(Integer.parseInt(PropertiesUtils.getValue("dbpool.maxIdle")));
        connectionPool.setMinIdle(Integer.parseInt(PropertiesUtils.getValue("dbpool.minIdle")));

        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(URL, USERNAME, PASSWORD);
        new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
        PoolingDataSource dataSource = new PoolingDataSource(connectionPool);
        return dataSource;
    }

}
