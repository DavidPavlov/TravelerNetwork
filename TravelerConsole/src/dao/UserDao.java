package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.InvalidDataException;
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
			this.connection = DriverManager
					.getConnection("jdbc:mysql://localhost/traveler_db?user=root&password=123456");
		} catch (SQLException e) {
			displaySqlErrors(e);
		}
	}

	public String insertUser(User user) {
		if (user == null) {
			return null;
		}
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery(String.format("select email from users where email = '%s'", user.getEmail()));
			if (!rs.next()) {
				int i = statement.executeUpdate(
						String.format("insert into users values('%s', '%s', '%s', '%s', '%s', now())", user.getEmail(),
								user.getFirstName(), user.getLastName(), user.getPassword(), user.getDescription()));
				rs.close();
				statement.close();
				connection.close();
				return "User Registered Succesfully";
			}

			rs.close();
			statement.close();
			connection.close();
			return "There is already a user with this email";

		} catch (SQLException e) {
			displaySqlErrors(e);
			return null;
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

	private void displaySqlErrors(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("Vendor error: " + e.getErrorCode());
	}
}
