package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.GeRuDokController;
import controller.command.CommandManager;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class CopyListener implements ActionListener{

	private WorkspaceModel model;
	private Tree tree;	
	
	public CopyListener(WorkspaceModel m, Tree t){
		this.model = m;
		this.tree = t;
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
	public void actionPerformed(ActionEvent arg0) {
		Object selected = tree.getSelected();
		if(tree.getSelected() instanceof Project){
			if(GeRuDokController.getInstance().isReadOnly((Project)tree.getSelected())){
				JOptionPane.showMessageDialog(null, "Shared project is read-only.");
				return;
			}
		}
		else if(tree.getSelected() instanceof GeRuDokument){
			GeRuDokument g = (GeRuDokument) tree.getSelected() ;
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly((GeRuDokument)tree.getSelected())) {
				JOptionPane.showMessageDialog(null, "Shared GeRuDocument is read-only.");
				return;
			}
			if(GeRuDokController.getInstance().isReadOnly(pr)) {
				JOptionPane.showMessageDialog(null, "Shared Project is read-only.");
				return;
			}
		}
		else if(tree.getSelected() instanceof Page){
			Page p = (Page) tree.getSelected();
			GeRuDokument g = (GeRuDokument) getParent(p);
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly(pr)){
				JOptionPane.showMessageDialog(null, "Shared Project is read-only.");
				return;
			}
			if(GeRuDokController.getInstance().isReadOnly(g)){
				JOptionPane.showMessageDialog(null, "Shared GeRuDocument is read-only.");
				return;
			}
		}else
			GeRuDokController.getInstance().copy(selected);
	}

}
