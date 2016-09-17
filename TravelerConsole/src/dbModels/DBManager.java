package dbModels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.CannotConnectToDBException;

class DBManager {

	private static DBManager instance; // Singleton
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "traveler_db";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "123456";
	private static final String commonURL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/";
	private static final String URL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
	private Connection connection;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // DriverManager
			setConnection(DriverManager.getConnection(commonURL, DB_USERNAME, DB_PASSWORD));
			if (!checkDBExists(DB_NAME)) { // checks if the DB schema exists
				makeDBAndTables(); // creates the DB schema and the tables in it
									// if the schema does not exist
			}
			setConnection(DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD)); // establishing
																						// a
																						// connection
		} catch (ClassNotFoundException e) {
			// TODO handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			System.out.println("getting DBManager conncetion instance = not null!");
			throw new CannotConnectToDBException();
		}
		return connection;
	}

	private boolean checkDBExists(String dbName) {
		ResultSet resultSet = null;
		try {
			resultSet = this.getConnection().getMetaData().getCatalogs(); // retrieving
																			// the
																			// db
																			// schemas'
																			// names

			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.equals(dbName)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotConnectToDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	private void makeDBAndTables() {
		Statement st = null;
		try {
			this.getConnection().setAutoCommit(false); // stop auto committing
			String createSchema = "CREATE DATABASE traveler_db;";
			String useSchema = "USE traveler_db;";
			String createDestinationsTable = "CREATE TABLE destinations " // creates
																			// destinations
																			// table
					+ "(name VARCHAR(64) NOT NULL PRIMARY KEY, "
					+ "description VARCHAR(500) NOT NULL, "
					+ "longitude VARCHAR(20) NOT NULL, "
					+ "lattitude VARCHAR(20) NOT NULL,"
					+ "picture VARCHAR(100));";
			String createUsersTable = "CREATE TABLE users " // creates users
															// table
					+ "(email VARCHAR(64) NOT NULL PRIMARY KEY,"
					+ "password VARCHAR(64) NOT NULL,"
					+ "first_name VARCHAR(64) NOT NULL,"
					+ "last_name VARCHAR(64) NOT NULL,"
					+ "description VARCHAR(500) NOT NULL,"
					+ "profilePic VARCHAR(100));";
			String createVisitedDestinationsTable = "CREATE TABLE visited_destinations (" // creates
																							// visited
																							// destinations...
					+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," // ...table
					+ "destination_name VARCHAR(64) NOT NULL,"
					+ "CONSTRAINT FK_destination_name FOREIGN KEY (destination_name)"
					+ "REFERENCES destinations (name),"
					+ "user_email VARCHAR(64) NOT NULL,"
					+ "CONSTRAINT FK_user_email FOREIGN KEY (user_email)"
					+ "REFERENCES users (email));";
			String createCommentsTable = "CREATE TABLE comments (" // creates
																	// comments
																	// table
					+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "author_email VARCHAR(64) NOT NULL,"
					+ "CONSTRAINT FK_author_email FOREIGN KEY (author_email)"
					+ "REFERENCES users (email),"
					+ "place_name VARCHAR(64) NOT NULL,"
					+ "CONSTRAINT FK_place_name FOREIGN KEY (place_name)"
					+ "REFERENCES destinations (name),"
					+ "text VARCHAR(500) NOT NULL,"
					+ "number_of_likes INT NOT NULL);";
			String createCommentLikesTable = "CREATE TABLE comment_likes (" // creates
																			// comment
																			// likes
																			// table
					+ "commenter_email VARCHAR(64) NOT NULL,"
					+ "comment_id INT NOT NULL,"
					+ "CONSTRAINT FK_commenter_email FOREIGN KEY (commenter_email)"
					+ "REFERENCES users (email),"
					+ "CONSTRAINT FK_comment_id FOREIGN KEY (comment_id)"
					+ "REFERENCES comments (id),"
					+ "PRIMARY KEY (commenter_email, comment_id));";
			st = this.getConnection().createStatement();
			st.executeUpdate(createSchema);
			st.executeUpdate(useSchema);
			st.executeUpdate(createDestinationsTable);
			st.executeUpdate(createUsersTable);
			st.executeUpdate(createVisitedDestinationsTable);
			st.executeUpdate(createCommentsTable);
			st.executeUpdate(createCommentLikesTable);
			this.getConnection().commit(); // commit the two statements
		} catch (CannotConnectToDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				this.getConnection().rollback(); // if the commit fails
			} catch (SQLException | CannotConnectToDBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				this.getConnection().setAutoCommit(true);
				if (st != null) {
					st.close();
				}
			} catch (SQLException | CannotConnectToDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setConnection(Connection con) {
		this.connection = con;
	}

}