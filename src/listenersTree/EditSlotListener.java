package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.GeRuDokController;
import dialogs.EditDeleteSlotDialog;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class EditSlotListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public EditSlotListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}

	private Object getParent(Object node){
		Workspace wsp = (Workspace) model.getRoot();
		if(node instanceof Project)
			return (Object)wsp;
		for(int i=0;i<wsp.getChildCount(); i++){
			if(wsp.getChildAt(i)==node)
				return wsp;
			if(node instanceof Page || node instanceof GeRuDokument){
				Project project = (Project)wsp.getChildAt(i);
				for(int j=0; j<project.getChildCount(); j++){
					if(project.getChildAt(j)==node)
						return project;				
					if(node instanceof Page){
						GeRuDokument grd =  (GeRuDokument) project.getChildAt(j);
						for(int k=0; k<grd.getChildCount(); k++){
							if(grd.getChildAt(k)==node)
								return grd;
						}
					}
				}
			}
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(tree.getSelected()==null)
			return;
		if(tree.getSelected() instanceof Page){
			Page p = (Page) tree.getSelected();
			GeRuDokument g = (GeRuDokument) getParent(p);
			Project pr = (Project) getParent(g);
			//provera da li su dokument ili gerudokument read only
			if(GeRuDokController.getInstance().isReadOnly(pr)){
				JOptionPane.showMessageDialog(null, "Shared Project is read-only.");
				return;
			}
			if(GeRuDokController.getInstance().isReadOnly(g)){
				JOptionPane.showMessageDialog(null, "Shared GeRuDocument is read-only.");
				return;
			}
			EditDeleteSlotDialog edsd = new EditDeleteSlotDialog((Page)tree.getSelected(),model, tree);
			edsd.show();
			model.reload();
			return;
		}
		
	}

	
}
