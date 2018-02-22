package view;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.GeRuDokController;
import listeners.CopyListener;
import listeners.CutListener;
import listeners.OpenListener;
import listeners.PasteListener;
import listenersTree.AddGeRuDokumentListener;
import listenersTree.AddPageListener;
import listenersTree.AddProjectListener;
import listenersTree.AddSlotListener;
import listenersTree.CloseListener;
import listenersTree.DeleteMouseListener;
import listenersTree.DeleteSlotListener;
import listenersTree.EditSelected;
import listenersTree.EditSlotListener;
import listenersTree.PropertiesMouseListener;
import listenersTree.ShareListener;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;

@SuppressWarnings("serial")
public class TreePopupMenu extends JPopupMenu{
	private JMenu mi0;
	private JMenuItem mi00;
	private JMenuItem mi01;
	private JMenuItem mi02;
	private JMenuItem mi03;
	private JMenuItem mi1;
	private JMenuItem mi2;
	private JMenuItem mi3;
	private JMenuItem mi5;
	private JMenuItem mi6;
	private JMenuItem mi7;
	private JMenuItem mi8;	
	private JMenuItem mi9;	
	private JMenuItem mi10;	
	private JMenu mi13;	
	private JMenu mi11;
	
	private WorkspaceModel model;
	private Tree tree;
	
	public TreePopupMenu(WorkspaceModel m, Tree t){
		this.model = m;
		this.tree=t;
		mi0 = new JMenu("Add");
		mi00 = new JMenuItem("Project"); 
		mi00.addActionListener(new AddProjectListener(model, tree));
		mi01 = new JMenuItem("GeRuDocument");
		mi01.addActionListener(new AddGeRuDokumentListener(model, tree));
		mi02 = new JMenuItem("Page");
		mi02.addActionListener(new AddPageListener(model, tree));
		mi03 = new JMenuItem("Slot");
		mi03.addActionListener(new AddSlotListener(model, tree));
		
		mi5 = new JMenuItem("Open");
		mi5.addActionListener(new OpenListener(model, tree));
		mi6 = new JMenuItem("Close");
		mi6.addActionListener(new CloseListener(model, tree));
		mi7 = new JMenuItem("Cut");
		mi7.addActionListener(new CutListener(model, tree));
		mi8 = new JMenuItem("Copy");
		mi8.addActionListener(new CopyListener(model, tree));
		mi9 = new JMenuItem("Paste");
		mi8.addActionListener(new PasteListener(model, tree));
		mi1 = new JMenuItem("Edit");
		mi1.addActionListener(new EditSelected(model, tree));
		mi2 = new JMenuItem("Delete");
		mi2.addActionListener(new DeleteMouseListener(model, tree));
		mi3 = new JMenuItem("Share");
		mi3.addActionListener(new ShareListener(model, tree));
		mi10=new JMenuItem("Properties");
		mi10.addActionListener(new PropertiesMouseListener(tree));
		
		mi13=new JMenu("Slots");
		JMenuItem mi14 = new JMenuItem("Edit slot");
		mi14.addActionListener(new EditSlotListener(model, tree));
		JMenuItem mi15 = new JMenuItem("Delete slot");
		mi15.addActionListener(new DeleteSlotListener(model, tree));
		mi13.add(mi14); mi13.add(mi15); 
				
		if(tree.getSelected() instanceof Workspace){
			mi01.setEnabled(false);
			mi02.setEnabled(false);
			mi03.setEnabled(false);
			mi7.setEnabled(false);
			mi8.setEnabled(false);
			mi9.setEnabled(false);
			mi3.setEnabled(false);
		}
		else if(tree.getSelected() instanceof Project){
			if(GeRuDokController.getInstance().isReadOnly((Project)tree.getSelected())){
				mi0.setEnabled(false);
				mi3.setEnabled(false);
				mi1.setEnabled(false);
				mi13.setEnabled(false);
				mi03.setEnabled(false);
				mi7.setEnabled(false);
				mi8.setEnabled(false);
				mi9.setEnabled(false);
				mi02.setEnabled(false);
				mi00.setEnabled(false);
				mi01.setEnabled(false);
				mi03.setEnabled(false);
				mi5.setEnabled(false);
				mi1.setEnabled(false);
				mi2.setEnabled(false);
				mi10.setEnabled(false);
				mi01.setEnabled(false);
				mi00.setEnabled(false);
				mi02.setEnabled(false);
				mi03.setEnabled(false);
				mi5.setEnabled(false);
				mi7.setEnabled(false);
				mi8.setEnabled(false);
				mi1.setEnabled(false);
				mi0.setEnabled(false);
				mi2.setEnabled(false);
				mi10.setEnabled(false);
				mi6.setEnabled(false);
			}else{
				mi00.setEnabled(false);
				mi02.setEnabled(false);
				mi03.setEnabled(false);
			}
		}
		else if(tree.getSelected() instanceof GeRuDokument){
			GeRuDokument g = (GeRuDokument) tree.getSelected() ;
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly((GeRuDokument)tree.getSelected()) || GeRuDokController.getInstance().isReadOnly(pr)) {//TREBA SE I EDIT IZBACITI
				mi0.setEnabled(false);
				mi1.setEnabled(false);
				mi13.setEnabled(false);
				mi03.setEnabled(false);
				mi7.setEnabled(false);
				mi8.setEnabled(false);
				mi9.setEnabled(false);
				mi02.setEnabled(false);
				mi00.setEnabled(false);
				mi01.setEnabled(false);
				mi03.setEnabled(false);
				mi5.setEnabled(false);
				mi1.setEnabled(false);
				mi2.setEnabled(false);
				mi10.setEnabled(false);
				mi01.setEnabled(false);
				mi00.setEnabled(false);
				mi02.setEnabled(false);
				mi03.setEnabled(false);
				mi5.setEnabled(false);
				mi7.setEnabled(false);
				mi8.setEnabled(false);
				mi1.setEnabled(false);
				mi0.setEnabled(false);
				mi2.setEnabled(false);
				mi10.setEnabled(false);
				mi6.setEnabled(false);
				mi3.setEnabled(false);
				}else{
					mi00.setEnabled(false);
					mi01.setEnabled(false);
					mi03.setEnabled(false);
				}
		}
		else{
			mi00.setEnabled(false);
			mi01.setEnabled(false);
			mi02.setEnabled(false);
			mi3.setEnabled(false);
			mi6.setEnabled(false);
			mi5.setEnabled(false);
		}
		
		if(tree.getSelected() instanceof Page){
			
			Page p = (Page) tree.getSelected();
			
			GeRuDokument g = (GeRuDokument) getParent(p);
			Project pr = (Project) getParent(g);
			
						if(GeRuDokController.getInstance().isReadOnly(pr) || GeRuDokController.getInstance().isReadOnly(g)){
						
							mi0.setEnabled(false);
							mi1.setEnabled(false);
							mi13.setEnabled(false);
							mi03.setEnabled(false);
							mi7.setEnabled(false);
							mi8.setEnabled(false);
							mi9.setEnabled(false);
							mi02.setEnabled(false);
							mi00.setEnabled(false);
							mi01.setEnabled(false);
							mi03.setEnabled(false);
							mi5.setEnabled(false);
							mi1.setEnabled(false);
							mi2.setEnabled(false);
							mi10.setEnabled(false);
							mi01.setEnabled(false);
							mi00.setEnabled(false);
							mi02.setEnabled(false);
							mi03.setEnabled(false);
							mi5.setEnabled(false);
							mi7.setEnabled(false);
							mi8.setEnabled(false);
							mi1.setEnabled(false);
							mi0.setEnabled(false);
							mi2.setEnabled(false);
							mi10.setEnabled(false);
							mi6.setEnabled(false);
						
							
						}
		}
	
		mi0.add(mi00); mi0.add(mi01);
		mi0.add(mi02); mi0.add(mi03);
		add(mi0);
		addSeparator();

		add(mi5);
		add(mi6);
		addSeparator();
		add(mi7);
		add(mi8);
		add(mi9);
		addSeparator();
		add(mi1);
		if(tree.getSelected() instanceof Page)
			add(mi13);
		add(mi2);
		add(mi3);
		addSeparator();
		add(mi10);
		
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


	
	public WorkspaceModel getModel() {
		return model;
	}

	public void setModel(WorkspaceModel model) {
		this.model = model;
	}
}
