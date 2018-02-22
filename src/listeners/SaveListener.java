package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import controller.command.CommandManager;
import model.Clipboard;
import model.GeRuDokModel;
import model.JsonModel;

public class SaveListener implements ActionListener {
	
	public SaveListener() {


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JsonModel jm = new JsonModel();
		if(GeRuDokModel.getInstance().getActiveWorkspace()==null)
			return;		
		jm.saveModel(GeRuDokModel.getInstance().getActiveWorkspace());
		CommandManager.getCMD().deleteAll();
		Clipboard.getInstance().delete();
	}

	

}
