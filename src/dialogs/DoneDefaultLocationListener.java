package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;

import login.UsersJson;
import model.GeRuDokModel;

public class DoneDefaultLocationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ChooseDefaultPath.getInstance().tfName.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "You have to choose default path.");
			return;
		}
		File file = new File(ChooseDefaultPath.getInstance().tfName.getText());
		if(!file.exists()){
			JOptionPane.showMessageDialog(null, "Path is not valid.");
			return;
		}
		String s = ChooseDefaultPath.getInstance().tfName.getText();
		s=s.replace("\\", "/");
		GeRuDokModel.getInstance().getCurrentUser().setPathToUsersFolder(s);
		GeRuDokModel.getInstance().getCurrentUser().setWantsDialog(ChooseDefaultPath.cb.isSelected());
		ChooseDefaultPath.getInstance().dispose();
		for(int i=0; i<GeRuDokModel.getInstance().getUsers().getUsers().size();i++){
			if(GeRuDokModel.getInstance().getUsers().getUsers().get(i)==GeRuDokModel.getInstance().getCurrentUser()){
				GeRuDokModel.getInstance().getUsers().getUsers().get(i).setPathToUsersFolder(GeRuDokModel.getInstance().getCurrentUser().getPathToUsersFolder());
				GeRuDokModel.getInstance().getUsers().getUsers().get(i).setWantsDialog(GeRuDokModel.getInstance().getCurrentUser().isWantsDialog());
				
			}
		}
		UsersJson us = new UsersJson();
		us.saveUsersToJSon(GeRuDokModel.getInstance().getUsers());
	}

		
	
}
