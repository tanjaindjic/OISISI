package view;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import listenersTree.NewWorkspaceListener;
import listenersTree.OpenWorkspaceListener;
import model.WorkspaceModel;

public class WorkspacePopupMenu extends JPopupMenu{
	private JMenuItem mi00;
	private JMenuItem mi01;
	private WorkspaceModel model;
	private Tree tree;
	
	public WorkspacePopupMenu(WorkspaceModel m, Tree t){
		this.model = m;
		this.tree=t;
		
		mi00 = new JMenuItem("New Workspace");
		mi00.addActionListener(new NewWorkspaceListener(model, tree));
		mi01 = new JMenuItem("Open Workspace");
		mi01.addActionListener(new OpenWorkspaceListener(model,tree));
		
		add(mi00);
		add(mi01);
	}
}
