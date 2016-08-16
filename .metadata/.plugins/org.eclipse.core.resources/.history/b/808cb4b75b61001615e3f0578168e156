package models;

import java.util.ArrayList;

import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;

public class Comment {
	private static int id;
	
	private String text;
	private int numberOfLikes;
	private int idNumber;
	private User author;
	private ArrayList<User> userLikers; // List of users who like the comment

	public Comment(String text, User author) throws InvalidDataException, InvalidAuthorException {
		this.setText(text);
		setAuthor(author);
		this.userLikers=new ArrayList<>();
		this.idNumber = id;
		id++;
	}

	public void setAuthor(User author) throws InvalidAuthorException {
		if (author!=null) {
			this.author = author;
		} else {
			throw new InvalidAuthorException();
		}
	}
	
	public User getAuthor() throws CloneNotSupportedException {
		return (User) author.clone();
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

	public int getIdNumber() {
		return idNumber;
	}

	public void addLike() {
		this.numberOfLikes++;
	}
	
	public ArrayList<User> getUserLikers() {
		return (ArrayList<User>) userLikers.clone();
	}
	
	public void addUserLiker(User user) {
		for (int i = 0; i < userLikers.size(); i++) {
			if (userLikers.get(i)==user) {
				return;
			}
		}
		userLikers.add(user);
	}

}
