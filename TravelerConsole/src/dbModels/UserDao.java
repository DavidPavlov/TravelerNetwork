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
	private UserDao() {}
	
	public static synchronized UserDao getInstance(){
		if (instance==null) {
			instance= new UserDao();
		}
		return instance;
	}

	public Set<User> getAllUsers () {
		Set<User> users = new HashSet<User>();
		try {
			try {
				Statement statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllUsersFromDB = "SELECT first_name, last_name, password, email, description FROM users;";
				ResultSet result = statement.executeQuery(selectAllUsersFromDB);
				while (result.next()) {
					users.add(new User( result.getString("first_name"), 
										result.getString("last_name"),
										result.getString("password"),
										result.getString("email"),	
										result.getString("description")
										));
				}
				//TODO add destinations to each user (form DB)
			} catch (CannotConnectToDBException e) {
				// TODO handle exception - write to log and userFriendly screen
				e.getMessage();
			}
		} catch (SQLException e) {
			//TODO write in the log
			return users;
		}
		return users;
	}

	public boolean saveUserToDB(User user) {
		String insertUserInfoIntoDB = "INSERT INTO users (first_name, last_name, password, email, description) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(insertUserInfoIntoDB);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getDescription());
			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (CannotConnectToDBException e) {
			// TODO handle exception - write to log and userFriendly screen
			e.getMessage();
			return false;
		}
		return true;
	}
	

	private void displaySqlErrors(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("Vendor error: " + e.getErrorCode());
	}
}
