package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Users;

public class AdminLogOutListener  implements ActionListener {
	
	public AdminLogOutListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Users u = AdminFrame.getInstance().getUsers();
		UsersJson json = new UsersJson();
		json.saveUsersToJSon(u);
		AdminFrame.getInstance().dispose();
	}
		
}

