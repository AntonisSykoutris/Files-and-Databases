import java.sql.*;

public class Connector {

	String url = new String("jdbc:mysql://localhost");
	String databaseName = new String("sys");
	int port = 3306;
	String username = new String("main");
	String password = new String("root");
	
	public Connection create_connection() throws SQLException {
		Connection con = DriverManager.getConnection(url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8",
				username, password);
		
		return con;
	}

	public Connector(){}
	
	
}