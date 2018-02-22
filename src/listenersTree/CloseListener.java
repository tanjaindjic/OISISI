package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.TreeModel;

import model.GeRuDokModel;
import model.GeRuDokument;
import model.JsonModel;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;
import view.WorkArea;

public class CloseListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public CloseListener(WorkspaceModel model, Tree t) {
		this.model = model;
		this.tree=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = tree.getSelected();
		if(o instanceof Workspace){
			int com = JOptionPane.showConfirmDialog(null, "Do you want to save your work before exiting?", "WARNING",
			        JOptionPane.YES_NO_OPTION);
			if (com == 0) {
			   
				JsonModel jm = new JsonModel();
				jm.saveModel(GeRuDokModel.getInstance().getActiveWorkspace());
				
				Workspace w = (Workspace) model.getRoot();
				w.setName("orengvoiseur823ru");
			//	tree.setModel((TreeModel) w);
				tree.setEditable(false);
				tree.show(false);
				
				
				int a= WorkArea.getInstance().getTabCount();
				if(a>0){
					WorkArea.getInstance().removeAll();
				}
				return;
				
			}
			if(com==1) {
				Workspace wsp = (Workspace)model.getRoot();
				for(int i=0; i<wsp.getProjects().size(); i++){
					wsp.getProjects().remove(i);
				}
				
				Workspace w = (Workspace) model.getRoot();
				w.setName("orengvoiseur823ru");
				//tree.setModel((TreeModel) w);
				tree.setEditable(false);
				tree.show(false);
				
				int a= WorkArea.getInstance().getTabCount();
				if(a>0){
					WorkArea.getInstance().removeAll();
				}
			}
		}
		if(o instanceof Project){
			if(tree.getLastSelectedPathComponent() instanceof Project || tree.getLastSelectedPathComponent() instanceof GeRuDokument)
				tree.collapsePath(tree.getSelectionPath());
			return;
		}
	}

}
