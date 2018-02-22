package view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import listenersTree.ShareListener;
import model.WorkspaceModel;

public class TreePopupShare extends JPopupMenu{
	
	private JMenuItem mi3;
	private WorkspaceModel model;
	private Tree tree;
	
	public TreePopupShare(WorkspaceModel m, Tree t){
		this.model = m;
		this.tree=t;
		
		mi3 = new JMenuItem("Share");
		mi3.addActionListener(new ShareListener(model, tree));
		add(mi3);
	}

	public WorkspaceModel getModel() {
		return model;
	}

	public void setModel(WorkspaceModel model) {
		this.model = model;
	}
}
