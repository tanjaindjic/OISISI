package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;

import controller.GeRuDokController;
import controller.command.AddPageCommand;
import controller.command.CommandManager;
import dialogs.AddPageDialog;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;
import view.Tree;

public class AddPageDialogListener implements ActionListener {
	private String name;
	private double width;
	private double height;	
	private AddPageDialog apd;
	private GeRuDokument grd;
	private DefaultTreeModel model;
	private Tree tree;
	
	public AddPageDialogListener(AddPageDialog apd, GeRuDokument grd, DefaultTreeModel model, Tree t) {
		this.apd = apd;
		this.grd =grd;
		this.model= model;
		tree=t;
		name="";
	}
	//funkcija za vracanje parenta bilo kog noda
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
		//provera da li je projekat readonly
		if(tree.getSelected() instanceof Project){
			if(GeRuDokController.getInstance().isReadOnly((Project)tree.getSelected())){
				JOptionPane.showMessageDialog(null, "Shared project is read-only.");
				return;
			}
		}
		else if(tree.getSelected() instanceof GeRuDokument){
			//provera da li je gerufokument readonly
			GeRuDokument g = (GeRuDokument) tree.getSelected() ;
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly((GeRuDokument)tree.getSelected()) || GeRuDokController.getInstance().isReadOnly(pr)) {
				JOptionPane.showMessageDialog(null, "Shared project or GeRuDocument is read-only.");
				return;
			}
		}
		
		
		name=AddPageDialog.tfName.getText();
		try{
		height = Double.parseDouble(AddPageDialog.tfHeight.getText());
		width = Double.parseDouble(AddPageDialog.tfWidth.getText());
		} catch(Exception exception ){
			JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
			return;	
		}
		if(name.isEmpty()){
			JOptionPane.showMessageDialog(null,"Name is empty. Please, fill up.");
			return;			
		}
		if( width<1 || height<1){
			JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
			return;	
		}
		Page page = new Page();
		page.setName(name);
		page.setHeight(height);
		page.setWidth(width);
		page.setSlots(new ArrayList<Slot>());
		page.setUniqueID(UUID.randomUUID());
		page.addObserver(tree);
		
		
		CommandManager cmd = CommandManager.getCMD();
		cmd.addAbstractCommand(new AddPageCommand(  grd,page ));
		model.reload();
		apd.dispose();
	}

	
}

