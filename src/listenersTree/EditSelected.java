package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.GeRuDokController;
import dialogs.AddPageDialog;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class EditSelected implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public EditSelected(WorkspaceModel m, Tree t) {
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
		if(tree.getSelected() instanceof Workspace){
			String name = JOptionPane.showInputDialog(null, "Please enter NEW name for your Workspace: ");
			if(name==null)
				return;
			if(name.trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Name can not be empty.");
				return;
			}
			((Workspace)tree.getSelected()).setName(name);
			model.reload();
			return;
		}
		if(tree.getSelected() instanceof Project){
			if(GeRuDokController.getInstance().isReadOnly((Project)tree.getSelected())){
				JOptionPane.showMessageDialog(null, "Shared project is read-only.");
				return;
			}
			String name = JOptionPane.showInputDialog(null, "Please enter NEW name for your Project: ");
			
			if(name == null){
				return;
			}
			
			if(name.trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Name can not be empty.");
				return;
			}
			((Project)tree.getSelected()).setName(name);
			model.reload();
			return;
		}
		if(tree.getSelected() instanceof GeRuDokument){
			GeRuDokument g = (GeRuDokument) tree.getSelected() ;
			Project pr = (Project) getParent(g);
			//provera da li su proj ili gerufokument readonly
			if(GeRuDokController.getInstance().isReadOnly((GeRuDokument)tree.getSelected())) {
				JOptionPane.showMessageDialog(null, "Shared GeRuDocument is read-only.");
				return;
			}
			if(GeRuDokController.getInstance().isReadOnly(pr)) {
				JOptionPane.showMessageDialog(null, "Shared Project is read-only.");
				return;
			}
			
			String name = JOptionPane.showInputDialog(null, "Please enter NEW name for your GeRuDocument: ");
			if(name==null)
				return;
			if(name.trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Name can not be empty.");
				return;
			}
			((GeRuDokument)tree.getSelected()).setName(name);
			model.reload();
			return;
		}
		if(tree.getSelected() instanceof Page){
			Page p = (Page) tree.getSelected();
			GeRuDokument g = (GeRuDokument) getParent(p);
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly(pr) || GeRuDokController.getInstance().isReadOnly(g)){
				JOptionPane.showMessageDialog(null, "Shared project or GeRuDocument is read-only.");
				return;
			}
			AddPageDialog adp = new AddPageDialog(model, (Page)tree.getSelected());
			adp.show();
		}
	}

	
}
