package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Users implements Serializable {

	private ArrayList<User> users = new ArrayList<User>();


	public Users(ArrayList<User> users) {
		this.users = users;
	}
	public Users(){
		
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public boolean login(String username, String password) {
		boolean retVal = false;
		for (User user : users) {
			if (username == user.getUsername()) {
				if (user.getTypeOfUser() != UserType.CLOSED)
					return user.isPassword(password);
			}
		}
		return retVal;
	}

	public boolean removeUser(String username) {
		boolean retVal = false;
		for (User user : users) {
			if (username == user.getUsername()) {
				user.setTypeOfUser(UserType.CLOSED);
			}
		}
		return retVal;
	}

	public boolean addUser(String username) {
		boolean retVal = true;
		User addUser = new User(username);
		for (User user : users) {
			if (user.getUsername() == username)
				return false;
		}
		users.add(addUser);
		return retVal;
	}

	public User getUser(String username) {
		for (User user : users) {
			if (user.getUsername() == username)
				return user;
		}
		return null;
	}

}

