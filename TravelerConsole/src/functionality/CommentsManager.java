package functionality;

import java.util.ArrayList;

import dbModels.CommentDao;
import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;
import models.Comment;

public class CommentsManager {

	private static CommentsManager instance; // Singleton
	private ArrayList<Comment> allComments; // all cached comments

	private CommentsManager() {
		allComments = new ArrayList<>();
		for (Comment c : CommentDao.getInstance().getAllComments()) { // adds
																		// all
																		// comments
																		// from
																		// DB to
																		// collection
			allComments.add(c);
		}
	}

	public static synchronized CommentsManager getInstance() {
		if (instance == null) {
			instance = new CommentsManager();
		}
		return instance;
	}

	public synchronized void saveComment(String userEmail, String placeName, String text) {
		try {
			Comment comment = new Comment(userEmail, placeName, text, 0);
			allComments.add(comment); // adds the new
										// comment to
										// the
										// collection
			CommentDao.getInstance().saveCommentToDB(comment); // saves comment
																// to
																// DB
			DestinationsManager.getInstance().getDestinationFromCache(placeName).addComment(comment);
		} catch (InvalidDataException | InvalidAuthorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized ArrayList<Comment> getAllComments() {
		ArrayList<Comment> copy = new ArrayList<>();
		copy.addAll(allComments);
		return copy;
	}

}
