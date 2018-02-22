package login;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Users;

public class UsersJson {
	public UsersJson(){
	} 	
	public void saveUsersToJSon(Users users){		
		ObjectMapper mapper = new ObjectMapper();
		String myLoc= (System.getProperty("user.dir")+"/src/login/users.json");
		try{
    		File file = new File(myLoc);
    		file.delete();
    	}catch(Exception e){
    	}
		try {
			mapper.writeValue(new File(myLoc), users);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong. Changes are not saved.");
			return;
		}
	}	
	
	public Users loadUsersFromJSon(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
		Users users = new Users();
		try {
			String myLoc= (System.getProperty("user.dir")+"/src/login/users.json");
			users = mapper.readValue(new File(myLoc), Users.class);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong. Please restart application");
			System.exit(0);
		}
		return users;
	}
}
