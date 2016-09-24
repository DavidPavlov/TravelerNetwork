package dbModels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import exceptions.CannotConnectToDBException;
import models.Destination;
import models.Location;
import models.User;

public class DestinationDAO {

	private static DestinationDAO instance; // Singleton
	private ConcurrentHashMap<String, String> destinationsAndAuthors;

	private DestinationDAO() {
		destinationsAndAuthors = this.getAllDestinationsAndAuthors();
	}

	public static synchronized DestinationDAO getInstance() {
		if (instance == null) {
			instance = new DestinationDAO();
		}
		return instance;
	}

	public ConcurrentHashMap<String, String> getAllDestinationsAndAuthors() {
		if (this.destinationsAndAuthors != null) {
			return this.destinationsAndAuthors;
		}
		ConcurrentHashMap<String, String> destinationsAndAutors = new ConcurrentHashMap<>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllFromDB = "SELECT destination_name, user_email FROM visited_destinations;";
				result = statement.executeQuery(selectAllFromDB);
				while (result.next()) {
					destinationsAndAutors.put(result.getString("destination_name"), result.getString("user_email"));
				}
			} catch (CannotConnectToDBException e) {
				// TODO handle exception - write to log and userFriendly screen
				e.getMessage();
				return destinationsAndAutors;
			}
		} catch (SQLException e) {
			// TODO write in the log
			return destinationsAndAutors;
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
		return destinationsAndAutors;
	}

	public Set<Destination> getAllDestinations() {
		Set<Destination> destinations = new HashSet<Destination>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllDestinationsFromDB = "SELECT name, description, longitude, lattitude, picture FROM destinations;";
				result = statement.executeQuery(selectAllDestinationsFromDB);
				while (result.next()) {
					String destinationAuthorEmail = this.destinationsAndAuthors.get(result.getString("name"));
					Destination dest = new Destination(result.getString("name"), result.getString("description"),
							new Location(Double.parseDouble(result.getString("lattitude")),
									Double.parseDouble(result.getString("longitude"))),
							result.getString("picture"), destinationAuthorEmail);
					destinations.add(dest);
				}
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

	public synchronized boolean saveDestinationToDB(User u, Destination destination) {
		String insertDestinationInfoIntoDB = "INSERT INTO destinations (name, description, longitude, lattitude, picture) VALUES (?, ?, ?, ?, ?);";
		String insertIntoVisitedDestinations = "INSERT INTO visited_destinations (destination_name, user_email) VALUES (?, ?);";
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			DBManager.getInstance().getConnection().setAutoCommit(false);
			statement = DBManager.getInstance().getConnection().prepareStatement(insertDestinationInfoIntoDB);
			statement.setString(1, destination.getName());
			statement.setString(2, destination.getDescription());
			statement.setDouble(3, destination.getLocation().getLongitude());
			statement.setDouble(4, destination.getLocation().getLattitude());
			statement.setString(5, destination.getPicture());
			statement.executeUpdate();

			statement2 = DBManager.getInstance().getConnection().prepareStatement(insertIntoVisitedDestinations);
			statement2.setString(1, destination.getName());
			statement2.setString(2, u.getEmail());
			statement2.executeUpdate();
			DBManager.getInstance().getConnection().commit();
			return true;
		} catch (SQLException e) {
			try {
				DBManager.getInstance().getConnection().rollback();
			} catch (SQLException | CannotConnectToDBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} catch (CannotConnectToDBException e) {
			// TODO handle exception - write to log and userFriendly screen
			e.getMessage();
			return false;
		} finally {
			try {
				DBManager.getInstance().getConnection().setAutoCommit(true);
				if (statement != null) {
					statement.close();
				}
				if (statement2 != null) {
					statement2.close();
				}
			} catch (SQLException | CannotConnectToDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized boolean updateDestinationInDB(String name, String description, double longitude,
			double lattitude,
			String picture) {
		PreparedStatement prepStatement = null;
		String updateDestinationStatement = "UPDATE destinations SET description=?, longitude=?, lattitude=?, picture=?  WHERE name=?;";
		try {
			prepStatement = DBManager.getInstance().getConnection().prepareStatement(updateDestinationStatement);
			prepStatement.setString(1, description);
			prepStatement.setDouble(2, longitude);
			prepStatement.setDouble(3, lattitude);
			prepStatement.setString(4, picture);
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
