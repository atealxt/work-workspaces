package easydao.connector.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import easydao.connector.Connector;
import easydao.utils.PropertiesUtils;

public final class ConnectorDataSource implements Connector {

    private static final Logger logger = Logger.getLogger(ConnectorDataSource.class);
    private static final ConnectorDataSource INSTANCE = new ConnectorDataSource();
    private static DataSource dataSource;

    static {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup(PropertiesUtils.getValue("jdbc.datasource"));
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private ConnectorDataSource() {}

    public static Connector getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
