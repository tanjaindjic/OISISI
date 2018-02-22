package listenersTree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.GeRuDokController;
import controller.command.CommandManager;
import controller.command.RemoveDocument;
import controller.command.RemovePage;
import controller.command.RemoveProject;
import model.GeRuDokument;
import model.JsonModel;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.MainWindow;
import view.PageView;
import view.Tree;
import view.WorkArea;

public class DeleteMouseListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public DeleteMouseListener(WorkspaceModel m, Tree t) {
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
		if(tree.getSelected() instanceof Workspace){
			int confirm = JOptionPane.showConfirmDialog(null, "If you delete workspace, you will also close all projects. Do you want to save projects before closing?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
			switch (confirm){
				case 0://YES
					JsonModel jm= new JsonModel();
					jm.saveModel((Workspace)tree.getSelected());
					CommandManager.getCMD().deleteAll();
					tree.setModel(null);
					tree.hide();
					
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						WorkArea.getInstance().removeAll();
					}
					
					break;
				case 1://NO
					CommandManager.getCMD().deleteAll();
					JsonModel jml= new JsonModel();
					jml.deleteAll();
					
					tree.hide();
					
					a = WorkArea.getInstance().getTabCount();
					if(a>0){
						WorkArea.getInstance().removeAll();
					}
					break;
			}
			model.reload();
			return;
		}
		if(tree.getSelected() instanceof Project){
			if(((Project)tree.getSelected()).getName().equals("freeGeRuDocuments")){
				int doDelete = JOptionPane.showConfirmDialog(null, " Are you sure that you want to delete freeGeRuDocuments?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
				if(doDelete==0){
					Workspace ws = (Workspace)model.getRoot();
					ws.getProjects().remove(ws.getProjects().size()-1);
					ws.setDefaultProject(null);					
				}
				model.reload();
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(null, " Do you want to delete GeRuDokuments from this project too? If no, they will be saved as free.","alert", JOptionPane.YES_NO_CANCEL_OPTION);
			if (confirm==0){//YES
				Object parent = getParent(tree.getSelected());
				if(parent instanceof Workspace){
					CommandManager cmd = CommandManager.getCMD();
					cmd.addAbstractCommand(new RemoveProject((Workspace)parent, (Project)tree.getSelected() ));
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						WorkArea.getInstance().removeAll();
					}
				}				
			}
			if (confirm==1){//NOOO
				Object parent = getParent(tree.getSelected());
				if(parent instanceof Workspace){
					Project p = (Project) tree.getSelected();
					boolean goOn = false;
					for(Project x : ((Workspace) parent).getProjects()){
						if(x.getName().equals("freeGeRuDocuments")){
							for(GeRuDokument g : p.getGerudokuments()){
								g.setPath("");
								g.setOwners(null);
								((Workspace) parent).getDefaultProject().getGerudokuments().add(g);
							}
							goOn=true;
						}
					}
					if(!goOn){
						((Workspace) parent).setDefaultProject(new Project("freeGeRuDocuments", p.getGerudokuments()));
						((Workspace) parent).getProjects().add(((Workspace) parent).getDefaultProject());
					}	
					CommandManager cmd = CommandManager.getCMD();
					cmd.addAbstractCommand(new RemoveProject((Workspace)parent, (Project)tree.getSelected() ));
					//((Workspace)parent).getProjects().remove((Project)tree.getSelected());
					
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						WorkArea.getInstance().removeAll();
					}
				}				
			}
			model.reload();
			return;
		}
		if(tree.getSelected() instanceof GeRuDokument){
			GeRuDokument g = (GeRuDokument) tree.getSelected() ;
			Project pr = (Project) getParent(g);
			if(GeRuDokController.getInstance().isReadOnly((GeRuDokument)tree.getSelected()) || GeRuDokController.getInstance().isReadOnly(pr)) {
				JOptionPane.showMessageDialog(null, "Shared project or GeRuDocument is read-only.");
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(null, " Are you sure that you want to delete this GeRuDokument?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
			if (confirm==0){//YES
				Object parent = getParent(tree.getSelected());
				if(parent instanceof Project){
					
					CommandManager cmd = CommandManager.getCMD();
					cmd.addAbstractCommand(new RemoveDocument((Project)parent, (GeRuDokument)tree.getSelected() ));
					
					//((Project)parent).getGerudokuments().remove((GeRuDokument)tree.getSelected());
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						for(int j=0; j<a; j++){
							if(WorkArea.getInstance().getTitleAt(j).contains(((GeRuDokument)tree.getSelected()).getUniqueID().toString())){
								WorkArea.getInstance().removeTabAt(j);	
							}
						}
					}
				}
			}
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
			
			int confirm = JOptionPane.showConfirmDialog(null, " Are you sure that you want to delete this page?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
			if (confirm==0){//YES
				Object parent = getParent(tree.getSelected());
				if(parent instanceof GeRuDokument){
					
					CommandManager cmd = CommandManager.getCMD();
					cmd.addAbstractCommand(new RemovePage((GeRuDokument)parent, (Page)tree.getSelected() ));
					//((GeRuDokument)parent).getPages().remove((Page)tree.getSelected());
					PageView pv = new PageView((GeRuDokument) parent);
					if((GeRuDokument)parent instanceof GeRuDokument){				
						if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
							
							WorkArea.getInstance().removeTabAt(0);		
					}
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						for(int j=0; j<a; j++){
							if(WorkArea.getInstance().getTitleAt(j).contains(((GeRuDokument) parent).getUniqueID().toString())){
								WorkArea.getInstance().removeTabAt(j);	
							}
						}
					}
					String name = (((GeRuDokument)parent).getName() + " (" + ((GeRuDokument)parent).getUniqueID().toString() +")");
					
					WorkArea.getInstance().add(name, new JScrollPane(pv));
					

					
					int index = WorkArea.getInstance().indexOfTab(name);
					JPanel pnlTab = new JPanel(new GridBagLayout());
					pnlTab.setOpaque(false);
					JLabel lblTitle = new JLabel(name);
					JButton btnClose = new JButton("x");
					btnClose.setBackground(Color.white); 
					btnClose.setOpaque(true); 
					btnClose.setMaximumSize(new Dimension(50,25));
					btnClose.setPreferredSize(new Dimension(50,25));
					btnClose.setMinimumSize(new Dimension(50,25));
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1;

					pnlTab.add(lblTitle, gbc);

					gbc.gridx++;
					gbc.weightx = 0;
					pnlTab.add(btnClose, gbc);

					WorkArea.getInstance().setTabComponentAt(index, pnlTab);

					btnClose.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							Component selected = WorkArea.getInstance().getSelectedComponent();
					        if (selected != null) {

					        	WorkArea.getInstance().remove(selected);
						}
						}
						
					});
					
					WorkArea.getInstance().revalidate();
					WorkArea.getInstance().repaint();
					MainWindow.getInstance().tabbedPane.revalidate();
					MainWindow.getInstance().tabbedPane.repaint();
					MainWindow.getInstance().tabbedPane.setVisible(true);
				}
			}
			model.reload();
			return;
		}
	}
}
