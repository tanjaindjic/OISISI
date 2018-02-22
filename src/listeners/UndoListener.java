package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import controller.command.CommandManager;
import model.WorkspaceModel;
import view.Tree;

public class UndoListener implements ActionListener {	
	private WorkspaceModel model;
	private Tree tree;
	
	public UndoListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CommandManager cmd = CommandManager.getCMD();
		cmd.undoCommand();
		model.reload();
	}

	
}
