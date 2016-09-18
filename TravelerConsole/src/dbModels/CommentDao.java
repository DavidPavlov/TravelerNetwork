package dbModels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import exceptions.CannotConnectToDBException;
import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;
import functionality.DestinationsManager;
import functionality.UsersManager;
import models.Comment;
import models.User;

public class CommentDao {

	private static CommentDao instance; // Singleton

	private CommentDao() {
	}

	public static synchronized CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}

	public Set<Comment> getAllComments() {
		System.out.println("Getting all comments from DB!!!!");
		Set<Comment> comments = new HashSet<Comment>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllCommentsFromDB = "SELECT author_email, place_name, text, number_of_likes FROM comments;";
				result = statement.executeQuery(selectAllCommentsFromDB);
				while (result.next()) {
					try {

						User author = UsersManager.getInstance().getUserFromCache(result.getString("author_email"));
						Comment comment = new Comment(author, result.getString("place_name"), result.getString("text"),
								result.getInt("number_of_likes"));
						comments.add(comment);
						DestinationsManager.getInstance().getDestinationFromCache(result.getString("place_name"))
								.addComment(comment);
					} catch (InvalidDataException | InvalidAuthorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("All comments returned from DB.");
				// TODO add destinations to each user (form DB)
			} catch (CannotConnectToDBException e) {
				// TODO handle exception - write to log and userFriendly screen
				e.getMessage();
				System.out.println("NO comments returned!!!!!");
				return comments;
			}
		} catch (SQLException e) {
			// TODO write in the log
			System.out.println("NO comments returned!!!!!");
			return comments;
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
		return comments;
	}

	public boolean saveCommentToDB(Comment comment) {
		String insertCommentIntoDB = "INSERT INTO comments (author_email, place_name, text, number_of_likes) VALUES (?, ?, ?, ?);";
		PreparedStatement statement = null;
		try {
			statement = DBManager.getInstance().getConnection().prepareStatement(insertCommentIntoDB);
			statement.setString(1, comment.getAuthor().getEmail());
			statement.setString(2, comment.getPlaceName());
			statement.setString(3, comment.getText());
			statement.setInt(4, comment.getNumberOfLikes());
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

}
