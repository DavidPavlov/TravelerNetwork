package models;

import java.util.ArrayList;

import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;

public class Comment {

	private User author;
	private String placeName;
	private String text;
	private int numberOfLikes;
	private ArrayList<User> userLikers; // List of users who like the comment

	public Comment(User author, String placeName, String text, int numberOfLikes)
			throws InvalidDataException, InvalidAuthorException {
		setAuthor(author);
		this.placeName = placeName;
		this.setText(text);
		this.numberOfLikes = numberOfLikes;
		this.userLikers = new ArrayList<>();
	}

	public void setAuthor(User author) {
		synchronized (this) {
			if (author != null) {
				this.author = author;
			}
		}
	}

	public User getAuthor() {
		synchronized (this) {
			return (User) author;
		}
	}

	public String getText() {
		synchronized (this) {
			return text;
		}
	}

	private void setText(String text) throws InvalidDataException {
		synchronized (this) {
			if (text != null && !text.isEmpty()) {
				this.text = text;
			} else {
				throw new InvalidDataException();
			}
		}
	}

	public int getNumberOfLikes() {
		synchronized (this) {
			return numberOfLikes;
		}
	}

	public void addLike() {
		synchronized (this) {
			this.numberOfLikes++;
		}
	}

	public ArrayList<User> getUserLikers() {
		synchronized (this) {
			ArrayList<User> copy = new ArrayList<>();
			copy.addAll(userLikers);
			return copy;
		}
	}

	public void addUserLiker(User user) {
		synchronized (this) {
			for (int i = 0; i < userLikers.size(); i++) {
				if (userLikers.get(i) == user) {
					return;
				}
			}
			userLikers.add(user);
		}
	}

	public String getPlaceName() {
		synchronized (this) {
			return placeName;
		}
	}

	public void setPlaceName(String placeName) {
		synchronized (this) {
			this.placeName = placeName;
		}
	}

	public void setNumberOfLikes(int numberOfLikes) {
		synchronized (this) {
			this.numberOfLikes = numberOfLikes;
		}
	}

}
