package dbModels;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exceptions.CannotConnectToDBException;
import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;
import models.Comment;

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

	public synchronized ArrayList<Comment> getAllComments() {
		System.out.println("Getting all comments from DB!!!!");
		ArrayList<Comment> comments = new ArrayList<>();
		Statement statement = null;
		ResultSet result = null;
		try {
			try {
				statement = DBManager.getInstance().getConnection().createStatement();
				String selectAllCommentsFromDB = "SELECT author_email, place_name, text, number_of_likes FROM comments;";
				result = statement.executeQuery(selectAllCommentsFromDB);
				while (result.next()) {
					try {
						Comment comment = new Comment(result.getString("author_email"),
								result.getString("place_name"),
								result.getString("text"),
								result.getInt("number_of_likes"));
						comments.add(comment);
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

	public synchronized boolean saveCommentToDB(Comment comment) {
		String insertCommentIntoDB = "INSERT INTO comments (author_email, place_name, text, number_of_likes) VALUES (?, ?, ?, ?);";
		PreparedStatement statement = null;
		try {
			statement = DBManager.getInstance().getConnection().prepareStatement(insertCommentIntoDB);
			statement.setString(1, comment.getAuthorEmail());
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
