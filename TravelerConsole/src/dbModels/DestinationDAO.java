package dbModels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import exceptions.CannotConnectToDBException;
import exceptions.InvalidCoordinatesException;
import models.Destination;
import models.Location;
import models.User;

public class DestinationDAO {

	private static DestinationDAO instance; // Singleton

	private DestinationDAO() {
	}

	public static synchronized DestinationDAO getInstance() {
		if (instance == null) {
			instance = new DestinationDAO();
		}
		return instance;
	}

	public Set<Destination> getAllDestinations() {
		Set<Destination> destinations = new HashSet<Destination>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllDestinationsFromDB = "SELECT name, description, longitude, latitude, FROM destinations;";
				result = statement.executeQuery(selectAllDestinationsFromDB);
				while (result.next()) {
					try {
						destinations.add(new Destination(result.getString("name"),
								result.getString("description"),
								new Location(result.getDouble("latitude"), result.getDouble("longitude"))));
					} catch (InvalidCoordinatesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// TODO add destinations to each user (form DB)
			} catch (CannotConnectToDBException e) {
				// TODO handle exception - write to log and userFriendly screen
				e.getMessage();
				return destinations;
			}
		} catch (SQLException e) {
			// TODO write in the log
			return destinations;
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
		return destinations;
	}

	public boolean saveDestinationToDB(Destination destination) {
		String insertDestinationInfoIntoDB = "INSERT INTO users (first_name, last_name, password, email, description) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement statement = null;
		try {
			statement = DBManager.getInstance().getConnection().prepareStatement(insertDestinationInfoIntoDB);
			statement.setString(1, destination.getFirstName());
			statement.setString(2, destination.getLastName());
			statement.setString(3, destination.getPassword());
			statement.setString(4, destination.getEmail());
			statement.setString(5, destination.getDescription());
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

	public boolean updateDestinationInDB(String email, String password, String firstName, String lastName, String description) {
		PreparedStatement prepStatement = null;
		String updateDestinationStatement = "UPDATE users SET password=?, first_name=?, last_name=?, description=? WHERE email=?;";
		try {
			prepStatement = DBManager.getInstance().getConnection().prepareStatement(updateDestinationStatement);
			prepStatement.setString(1, password);
			prepStatement.setString(2, firstName);
			prepStatement.setString(3, lastName);
			prepStatement.setString(4, description);
			prepStatement.setString(5, email);
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

}
