package listenersTree;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import dialogs.ChooseDefaultPath;
import login.AdminFrame;
import login.LogInFrame;
import model.GeRuDokModel;
import model.UserType;
import model.Users;
import view.MainWindow;

public class LoginEnterListener implements KeyListener {
	
	private String username;
	private String password;

	public LoginEnterListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			
			boolean gotIt = false;
			Users users = GeRuDokModel.getInstance().getUsers();
			for(int i=0; i<users.getUsers().size(); i++){
				if(LogInFrame.getInstance().textName.getText().toString().equals(users.getUsers().get(i).getUsername())){
					if(LogInFrame.getInstance().passField.getText().equals(users.getUsers().get(i).getPassword())){
						if(users.getUsers().get(i).getTypeOfUser()==UserType.DESIGNER){
							GeRuDokModel.getInstance().setCurrentUser(users.getUsers().get(i));
							MainWindow main = MainWindow.getInstance();
							if(!users.getUsers().get(i).isWantsDialog()){
								ChooseDefaultPath cdp = ChooseDefaultPath.getInstance();
								cdp.show();
							}
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
