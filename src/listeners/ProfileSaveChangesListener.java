package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import login.UsersJson;
import model.GeRuDokModel;
import model.Users;
import view.ProfileDetails;

public class ProfileSaveChangesListener implements ActionListener {
	
	private ProfileDetails dial;
	
	public ProfileSaveChangesListener(ProfileDetails d) {
		this.dial = d;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String path = dial.workspacePath.getText();
		
			GeRuDokModel.getInstance().getCurrentUser().setPathToUsersFolder(path);
			if(dial.passwordField.getText().trim().isEmpty()){
				dial.dispose();
				return;
			}
			if(dial.passwordField.getText().equals(dial.passwordField_1.getText())){
				GeRuDokModel.getInstance().getCurrentUser().setPassword(dial.passwordField.getText());
				dial.dispose();
				UsersJson uj = new UsersJson();
				Users u = GeRuDokModel.getInstance().getUsers();
				for(int i=0; i< u.getUsers().size(); i++){
					if(u.getUsers().get(i).getUsername().equals(GeRuDokModel.getInstance().getCurrentUser().getUsername())){
						u.getUsers().set(i, GeRuDokModel.getInstance().getCurrentUser());
						uj.saveUsersToJSon(u);
					}
				}
				JOptionPane.showMessageDialog(null, "Changes are saved successfully.");
				return;
			}
			JOptionPane.showMessageDialog(null, "Passwords are not matching. Please, type it again.");
	}

}
