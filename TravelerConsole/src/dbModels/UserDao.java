package dbModels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import exceptions.CannotConnectToDBException;
import models.User;

public class UserDao {

	private static UserDao instance; // Singleton

	private UserDao() {
	}

	public static synchronized UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public Set<User> getAllUsers() {
		System.out.println("Getting all users from DB!!!!");
		Set<User> users = new HashSet<User>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllUsersFromDB = "SELECT first_name, last_name, password, email, description, profilePic FROM users;";
				result = statement.executeQuery(selectAllUsersFromDB);
				while (result.next()) {
					User user = new User(result.getString("first_name"), result.getString("last_name"),
							result.getString("password"), result.getString("email"), result.getString("description"),
							result.getString("profilePic")); // creating a new
																// user with
																// info from DB

					users.add(user); // add user to allUsers cache
				}
				System.out.println("All users returned from DB.");
				// TODO add destinations to each user (form DB)
			} catch (CannotConnectToDBException e) {
				// TODO handle exception - write to log and userFriendly screen
				e.getMessage();
				System.out.println("NO users returned!!!!!");
				return users;
			}
		} catch (SQLException e) {
			// TODO write in the log
			System.out.println("NO users returned!!!!!");
			return users;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

	public synchronized boolean saveUserToDB(User user) {
		String insertUserInfoIntoDB = "INSERT INTO users (first_name, last_name, password, email, description, profilePic) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = null;
		try {
			statement = DBManager.getInstance().getConnection().prepareStatement(insertUserInfoIntoDB);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getDescription());
			statement.setString(6, user.getProfilePic());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (CannotConnectToDBException e) {
			// TODO handle exception - write to log and userFriendly screen
			e.getMessage();
			return false;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public synchronized boolean updateUserInDB(String email, String password, String firstName, String lastName,
			String description,
			String profilePic) {
		// Update all fields of the current user except email (primary key)
		PreparedStatement prepStatement = null;
		String updateUserStatement = "UPDATE users SET password=?, first_name=?, last_name=?, description=?, profilePic=? WHERE email=?;";
		try {
			prepStatement = DBManager.getInstance().getConnection().prepareStatement(updateUserStatement);
			prepStatement.setString(1, password);
			prepStatement.setString(2, firstName);
			prepStatement.setString(3, lastName);
			prepStatement.setString(4, description);
			prepStatement.setString(5, email);
			prepStatement.setString(6, profilePic);
			prepStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CannotConnectToDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			if (prepStatement != null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void displaySqlErrors(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("Vendor error: " + e.getErrorCode());
	}
}
