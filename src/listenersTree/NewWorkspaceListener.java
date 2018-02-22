package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.command.CommandManager;
import model.Clipboard;
import model.GeRuDokModel;
import model.JsonModel;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class NewWorkspaceListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public NewWorkspaceListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!tree.isShowing()){
			String name = JOptionPane.showInputDialog(null, "Please enter a name for your Workspace: ");
			if(name==null){
				return;
			}
			name=name.trim();
			if(!name.isEmpty()){
				
				Workspace root = new Workspace(name);
				WorkspaceModel wm = new WorkspaceModel(root);
				GeRuDokModel gmd=GeRuDokModel.getInstance();
				gmd.setActiveWorkspace(root);
				model.setRoot(root);
				//tree.setModel();
				tree.show();
				root.addObserver(tree);
				
			}
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save workspace before closing?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
		if(confirm==0){
			JsonModel jm = new JsonModel();
			jm.saveModel((Workspace)model.getRoot());
		}else if(confirm == 2){
			return;
		}
		String name = JOptionPane.showInputDialog(null, "Please enter a name for your Workspace: ");
		
		if(name == null){
			
			return;
		}
		
		name=name.trim();
		Workspace root = new Workspace();
		root.setName(name);
		
		GeRuDokModel gmd=GeRuDokModel.getInstance();
		gmd.setActiveWorkspace(root);
		model.setRoot(root);
	//	tree.setModel(new WorkspaceModel(root)); zbog ove linije nije radilo
		CommandManager.getCMD().deleteAll();
		Clipboard.getInstance().delete();
		tree.show();
		root.addObserver(tree);		
	}
	
}
