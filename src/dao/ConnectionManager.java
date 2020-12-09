package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	
	private static final String DATABASE = "localhost:3306/sablonmodul1";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	private static Connection connection;
	
	public static void open() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?allowPublicKeyRetrieval=true&useSSL=false", USER_NAME, PASSWORD);
	}

	
	public static Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			throw new Exception("Connection does not exist!");
		}

		return connection;
	}

	public static void close() throws Exception {
		connection.close();
	}

}
