package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import dialogs.ChooseDefaultPath;
import login.AdminFrame;
import login.LogInFrame;
import model.GeRuDokModel;
import model.UserType;
import model.Users;
import view.MainWindow;

public class LoginSubmitListener implements ActionListener {

	public LoginSubmitListener() {
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		boolean gotIt = false;
		Users users = GeRuDokModel.getInstance().getUsers();
		for(int i=0; i<users.getUsers().size(); i++){
			if(LogInFrame.getInstance().textName.getText().toString().equals(users.getUsers().get(i).getUsername())){
				if(LogInFrame.getInstance().passField.getText().equals(users.getUsers().get(i).getPassword())){
					//za dizajnera
					if(users.getUsers().get(i).getTypeOfUser()==UserType.DESIGNER){
						GeRuDokModel.getInstance().setCurrentUser(users.getUsers().get(i));
						MainWindow main = MainWindow.getInstance();
						if(!users.getUsers().get(i).isWantsDialog()){
							ChooseDefaultPath cdp = ChooseDefaultPath.getInstance();
							cdp.show();
						}
						LogInFrame.getInstance().dispose();
						gotIt=true;
					}
					else if(users.getUsers().get(i).getTypeOfUser()==UserType.ADMIN){
						AdminFrame admFrame = AdminFrame.getInstance();
						LogInFrame.getInstance().dispose();
						gotIt=true;
					}
				}
			}
		}
		if(!gotIt){
			JOptionPane.showMessageDialog(null,"Your user name or password is incorrect.");		
			return;
		}
		LogInFrame.getInstance().dispose();		
		
	}

}
