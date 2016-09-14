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
	private static final String URL = "jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME;
	private Connection connection;
	
	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // DriverManager
			connection=DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD); // establishing a connection
			if (!checkDBExists(DB_NAME)) { // checks if the DB schema exists
				makeDBAndTables(); // creates the DB schema and the tables in it if the schema does not exist
			}
		} catch (ClassNotFoundException e) {
			// TODO handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static synchronized DBManager getInstance(){
		if (instance==null) {
			instance=new DBManager();
		}
		return instance;
	}
	
	Connection getConnection() throws CannotConnectToDBException {
		if (connection==null) {
			throw new CannotConnectToDBException();
		}
		return connection;
	}
	
	private boolean checkDBExists(String dbName) {
		Connection con=null;
		ResultSet resultSet=null;
		try {
			con=DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
			resultSet = con.getMetaData().getCatalogs(); //retrieving the db schemas' names
			
			while (resultSet.next()) {
	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(dbName)){
	                return true;
	            }
	        }    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private void makeDBAndTables() {
		Statement st1=null;
		Statement st2=null;
		Statement st3=null;
		Statement st4=null;
		Statement st5=null;
		Statement st6=null;
		Statement st7=null;
		try {
			connection.setAutoCommit(false); // stop auto committing
			String createSchema = "CREATE DATABASE traveler_db;";
			String useSchema = "USE traveler_db;";
			String createDestinationsTable  = "CREATE TABLE destinations " // creates destinations table
											+ "(name VARCHAR(64) NOT NULL PRIMARY KEY, "
											+ "description VARCHAR(500) NOT NULL, "
											+ "longitude VARCHAR(20) NOT NULL, "
											+ "lattitude VARCHAR(20) NOT NULL);";
			String createUsersTable = "CREATE TABLE users "  // creates users table
										+ "(email VARCHAR(64) NOT NULL PRIMARY KEY,"
										+ "password VARCHAR(64) NOT NULL,"
										+ "first_name VARCHAR(64) NOT NULL,"
										+ "last_name VARCHAR(64) NOT NULL,"
										+ "description VARCHAR(500) NOT NULL);";
			String createVisitedDestinationsTable = "CREATE TABLE visited_destinations (" // creates visited destinations... 
													+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," // ...table
													+ "destination_name VARCHAR(64) NOT NULL,"
													+ "CONSTRAINT FK_destination_name FOREIGN KEY (destination_name)"
													+ "REFERENCES destinations (name),"
													+ "user_email VARCHAR(64) NOT NULL,"
													+ "CONSTRAINT FK_user_email FOREIGN KEY (user_email)"
													+ "REFERENCES users (email));";
			String createCommentsTable = "CREATE TABLE comments (" // creates comments table
										+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
										+ "author_email VARCHAR(64) NOT NULL,"
										+ "CONSTRAINT FK_author_email FOREIGN KEY (author_email)"
										+ "REFERENCES users (email),"
										+ "place_name VARCHAR(64) NOT NULL,"
										+ "CONSTRAINT FK_place_name FOREIGN KEY (place_name)"
										+ "REFERENCES destinations (name),"
										+ "text VARCHAR(500) NOT NULL,"
										+ "number_of_likes INT NOT NULL);";
			String createCommentLikesTable = "CREATE TABLE comment_likes (" // creates comment likes table
											+ "commenter_email VARCHAR(64) NOT NULL,"
											+ "comment_id INT NOT NULL,"
											+ "CONSTRAINT FK_commenter_email FOREIGN KEY (commenter_email)"
											+ "REFERENCES users (email),"
											+ "CONSTRAINT FK_comment_id FOREIGN KEY (comment_id)"
											+ "REFERENCES comments (id),"
											+ "PRIMARY KEY (commenter_email, comment_id));";
			st1 = DBManager.getInstance().getConnection().createStatement();
			st1.executeQuery(createSchema);
			st2 = DBManager.getInstance().getConnection().createStatement();
			st2.executeQuery(useSchema);
			st3 = DBManager.getInstance().getConnection().createStatement();
			st3.executeQuery(createDestinationsTable);
			st4 = DBManager.getInstance().getConnection().createStatement();
			st4.executeQuery(createUsersTable);
			st5 = DBManager.getInstance().getConnection().createStatement();
			st5.executeQuery(createVisitedDestinationsTable);
			st6 = DBManager.getInstance().getConnection().createStatement();
			st6.executeQuery(createCommentsTable);
			st7 = DBManager.getInstance().getConnection().createStatement();
			st7.executeQuery(createCommentLikesTable);
			connection.commit(); // commit the two statements
		} catch (CannotConnectToDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				connection.rollback(); // if the commit fails
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		finally {
			try {
				connection.setAutoCommit(true);
				if (st1!=null) { st1.close(); }
				if (st2!=null) { st2.close(); }
				if (st3!=null) { st3.close(); }
				if (st4!=null) { st4.close(); }
				if (st5!=null) { st5.close(); }
				if (st6!=null) { st6.close(); }
				if (st7!=null) { st7.close(); }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}