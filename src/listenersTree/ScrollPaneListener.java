package listenersTree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;
import view.TreePopupMenu;
import view.WorkspacePopupMenu;

public class ScrollPaneListener extends MouseAdapter {
	private WorkspaceModel model;
	private Tree tree;
	
	public ScrollPaneListener(WorkspaceModel model, Tree tree){
		this.model = model;
		this.tree  = tree;
	}
	public void mousePressed(MouseEvent e){
		doPopWorkspace(e);
    }

    public void mouseReleased(MouseEvent e){
    	doPopWorkspace(e);
    }
    private void doPopWorkspace(MouseEvent e){
    	//novi dijalog
    	WorkspacePopupMenu menu = new WorkspacePopupMenu(model,tree);
    	menu.show(e.getComponent(), e.getX(), e.getY());
    }
    
	public WorkspaceModel getModel() {
		return model;
	}

	public void setModel(WorkspaceModel model) {
		this.model = model;
	}
}
