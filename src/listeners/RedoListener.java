package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import controller.command.CommandManager;
import model.WorkspaceModel;
import view.Tree;

public class RedoListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public RedoListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}
	public void actionPerformed(ActionEvent e) {		
		CommandManager cmd = CommandManager.getCMD();
		int aktivna =cmd.getActivePosition();
		cmd.doCommand();
		model.reload();
	}
	
}
