package easydao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import easydao.connector.Connector;
import easydao.connector.MultiDataSourceConnector;
import easydao.utils.PropertiesUtils;

public final class EasyDao {

    private static Connector connector = getConnector();

    private static Connector getConnector() {
        try {
            Class<?> clazz = Class.forName(PropertiesUtils.getValue("dbconnector.class"));
            Method getInstance = clazz.getDeclaredMethod("getInstance");
            Object connector = getInstance.invoke(clazz);
            return (Connector) connector;
        } catch (Exception e) {
            throw new RuntimeException("Init Connector failure, check the config key: 'dbconnector.class'!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return connector.getConnection();
    }

    public static Connection getConnection(final String dataSourceName) throws SQLException {
        if (!(connector instanceof MultiDataSourceConnector)) {
            throw new UnsupportedOperationException(connector.getClass().getName() + " not supported MultiDataSource!");
        }
        return ((MultiDataSourceConnector) connector).getConnection(dataSourceName);
    }
}
