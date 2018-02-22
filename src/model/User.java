package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	private String username;
	private String password;
	private String pathToUsersFolder="";	
	private UserType typeOfUser;
	private boolean wantsDialog = true;
	
	public User(){
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPathToUsersFolder() {
		return pathToUsersFolder;
	}

	public void setPathToUsersFolder(String pathToUsersFolder) {
		this.pathToUsersFolder = pathToUsersFolder;
	}

	public UserType getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(UserType typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String username) {
		this.username = username;
		this.password = "grok";
		this.pathToUsersFolder = "";
		this.typeOfUser = UserType.DESIGNER;
	}
	public User(String username, String password, String pathToUsersFolder, UserType typeOfUser, boolean wantsDialog){
		this.username = username;
		this.password = password;
		this.pathToUsersFolder = pathToUsersFolder;
		this.typeOfUser = typeOfUser;
		this.wantsDialog = wantsDialog;
	}

	public boolean isPassword(String password){
		return this.password==password;
	}

	public boolean isWantsDialog() {
		return wantsDialog;
	}

	public void setWantsDialog(boolean wantsDialog) {
		this.wantsDialog = wantsDialog;
	}
	
}

