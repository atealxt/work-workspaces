package easydao.connector;

import java.sql.Connection;
import java.sql.SQLException;

public interface MultiDataSourceConnector extends Connector {

    Connection getConnection(String dataSourceName) throws SQLException;
}
