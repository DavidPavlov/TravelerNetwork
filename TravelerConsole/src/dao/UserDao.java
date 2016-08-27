package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.InvalidDataException;
import functionality.DaoParser;
import models.User;

public class UserDao {

	private Connection connection;

	public UserDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("Unable to find and load driver");
		}
	}

	public void connectToDB() {
		try {
			this.connection = DriverManager.getConnection(DaoParser.PATH_TO_BASE);
		} catch (SQLException e) {
			displaySqlErrors(e);
		}
	}

	public boolean insertUser(User user) {
		if (user == null) {
			return false;
		}
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery(String.format("select email from users where email = '%s'", user.getEmail()));
			if (!rs.next()) {
				int i = statement.executeUpdate(
						String.format("insert into users values('%s', '%s', '%s', '%s', '%s')", user.getEmail(),
								user.getFirstName(), user.getLastName(), user.getPassword(), user.getDescription()));
				rs.close();
				statement.close();
				connection.close();
				return true;
			}

			rs.close();
			statement.close();
			connection.close();
			return false;

		} catch (SQLException e) {
			displaySqlErrors(e);
			return false;
		}
	}

	public User getUser(String email) {
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from users where email = '%s'", email));
			if (!rs.next()) {
				return null;
			}

			User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(5));
			// TODO populate user's visited places
			rs.close();
			statement.close();
			connection.close();
			return user;
		} catch (SQLException e) {
			displaySqlErrors(e);
			return null;
		} catch (InvalidDataException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean validateUser(String email, String password) {
		java.sql.Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from users where email = '%s'", email));
			if (!rs.next()) {
				return false;
			}

			String pass = rs.getString(4);
			if (pass.equals(password)) {
				return true;
			}

			return false;
		} catch (SQLException e) {
			displaySqlErrors(e);
		}
		return false;
	}

	private void displaySqlErrors(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("Vendor error: " + e.getErrorCode());
	}
}
