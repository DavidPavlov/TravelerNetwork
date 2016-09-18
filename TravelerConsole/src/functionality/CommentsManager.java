package functionality;

import java.util.concurrent.ConcurrentHashMap;

import dbModels.CommentDao;
import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;
import models.Comment;
import models.User;

public class CommentsManager {

	private static CommentsManager instance; // Singleton
	private ConcurrentHashMap<String, Comment> allComments;

	private CommentsManager() {
		allComments = new ConcurrentHashMap<>();
		for (Comment c : CommentDao.getInstance().getAllComments()) { // adds
																		// all
																		// comments
																		// from
																		// DB to
																		// collection
			allComments.put(c.getPlaceName(), c);
		}
	}

	public static synchronized CommentsManager getInstance() {
		if (instance == null) {
			instance = new CommentsManager();
		}
		return instance;
	}

	public void saveComment(User user, String placeName, String text, int numberOfLikes) {
		try {
			Comment comment = new Comment(user, placeName, text, numberOfLikes);
			allComments.put(comment.getPlaceName(), comment); // adds the new
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

	public ConcurrentHashMap<String, Comment> getAllComments() {
		return allComments;
	}

}
