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
		if (author != null) {
			this.author = author;
		}
	}

	public User getAuthor() {
		return (User) author;
	}

	public String getText() {
		return text;
	}

	private void setText(String text) throws InvalidDataException {
		if (text != null && !text.isEmpty()) {
			this.text = text;
		} else {
			throw new InvalidDataException();
		}
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void addLike() {
		this.numberOfLikes++;
	}

	public ArrayList<User> getUserLikers() {
		return (ArrayList<User>) userLikers.clone();
	}

	public void addUserLiker(User user) {
		for (int i = 0; i < userLikers.size(); i++) {
			if (userLikers.get(i) == user) {
				return;
			}
		}
		userLikers.add(user);
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

}
