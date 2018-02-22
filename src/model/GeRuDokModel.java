package model;

import login.UsersJson;

public class GeRuDokModel {

	private static GeRuDokModel instance;
	private User currentUser;
	private Workspace activeWorkspace;
	private Users users;
	private  Clipboard clipboard;
	
	public Clipboard getClipboard(){
		return clipboard;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Workspace getActiveWorkspace() {
		return activeWorkspace;
	}

	public void setActiveWorkspace(Workspace activeWorkspace) {
		this.activeWorkspace = activeWorkspace;
	}

	public Users getUsers() {
		return users;
	}

	public void logout(){
		currentUser=null;
		activeWorkspace=null;
	}
	
	
	
	public static GeRuDokModel getInstance() {
		
		if (instance == null){
			instance = new GeRuDokModel();
			
		}
		
		return instance;
	}
	
	private GeRuDokModel(){
		currentUser = null;
		activeWorkspace = null;
		clipboard=Clipboard.getInstance();
		UsersJson uj = new UsersJson();
		users = uj.loadUsersFromJSon();
	}
	
	private boolean logInUser(String username, String password){
		
		if(!users.login(username, password)){
			return false;
		}
		User user = users.getUser(username);
		
		currentUser = user;
		if(user.getTypeOfUser()==UserType.DESIGNER){
		loadData(user.getPathToUsersFolder());
		}		
		return true;
	}
	
	public boolean loadUsers(){
		boolean retVal = false;
		
		return retVal;
	}
	
	public boolean loadData(String path){
		boolean retVal = false;
		
		return retVal;
	}
	public boolean save(String path){

		boolean retVal = false;
		
		return retVal;
		
	}
	public boolean registerUser(String username){
		return users.addUser(username);
	}
	
}
