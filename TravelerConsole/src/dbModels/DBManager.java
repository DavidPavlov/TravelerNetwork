package dbModels;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.CannotConnectToDBException;

class DBManager {

	private static DBManager instance; // Singleton
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "traveler_db";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "123456";
	private static final String URL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
	private Connection connection;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(URL);
			// checks if the schema exists in the DB. If not - creates it
			DatabaseMetaData meta = connection.getMetaData();
			// ResultSet result = meta
			// TODO !!!!!!!!!!!!!

		} catch (SQLException e) {
			// TODO handle exception
			e.printStackTrace();
		}
	}

	static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	Connection getConnection() throws CannotConnectToDBException {
		if (connection == null) {
			throw new CannotConnectToDBException();
		}
		return connection;
	}

}
