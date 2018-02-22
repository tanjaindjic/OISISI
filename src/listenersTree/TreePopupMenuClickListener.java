package listenersTree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.MainWindow;
import view.PageView;
import view.Tree;
import view.TreePopupMenu;
import view.TreePopupShare;
import view.WorkArea;
import view.WorkspacePopupMenu;

public class TreePopupMenuClickListener extends MouseAdapter {
	private WorkspaceModel model;
	private Tree tree;
	private PageView pages;
	
	
	public TreePopupMenuClickListener(WorkspaceModel model, Tree tree){
		this.model = model;
		this.tree  = tree;
	}
	public void mouseClicked(MouseEvent e){
		if (e.getClickCount() == 2) {
			if(tree.getSelected() instanceof GeRuDokument){		
				if(((GeRuDokument)tree.getSelected()).getName().equals("freeGeRuDocuments")){
					return;
				}
				if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
					
					WorkArea.getInstance().removeTabAt(0);		
				
				int a= WorkArea.getInstance().getTabCount();
				if(a>0){
					for(int j=0; j<a; j++){
						if(WorkArea.getInstance().getTitleAt(j).contains(((GeRuDokument)tree.getSelected()).getUniqueID().toString())){
							//WorkArea.getInstance().removeTabAt(j);	
							WorkArea.getInstance().revalidate();
							WorkArea.getInstance().repaint();
							MainWindow.getInstance().tabbedPane.revalidate();
							MainWindow.getInstance().tabbedPane.repaint();
							MainWindow.getInstance().tabbedPane.setVisible(true);
							return;
						}	
					
					}
				}
				PageView pv = new PageView(tree);
				String name = ((GeRuDokument)tree.getSelected()).getName() + " (" + ((GeRuDokument)tree.getSelected()).getUniqueID().toString() +")";
				
				WorkArea.getInstance().addTab(name, pv);
								
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
				model.reload();
			}
		}
	}
	public void mousePressed(MouseEvent e){
		if(SwingUtilities.isRightMouseButton(e)){
	         int selRow = tree.getRowForLocation(e.getX(), e.getY());
	         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
	                 tree.setSelectionPath(selPath); 
	                 if (selRow>-1){
	                    tree.setSelectionRow(selRow); 
	                 }
		}
		if(!(tree.getSelected() instanceof Workspace ||
				tree.getSelected() instanceof GeRuDokument ||
				tree.getSelected() instanceof Page ||
				tree.getSelected() instanceof Project) && e.isPopupTrigger()){
					doPopWorkspace(e);
					return;
		}
		if(tree.getSelected() instanceof Project){
			if(((Project)tree.getSelected()).getName().equals("freeGeRuDocuments"))
				return;
		}
		if(tree.getSelected() instanceof GeRuDokument){
			Project last = ((Workspace)model.getRoot()).getProjects().get(((Workspace)model.getRoot()).getProjects().size()-1);
			if(last.getName().equals("freeGeRuDocuments")){
				for(GeRuDokument g : last.getGerudokuments()){
					if(g==((GeRuDokument)tree.getSelected())){
						if (e.isPopupTrigger())
							doPopShare(e);
						return;
					}
				}
			}
		}
		if(tree.getSelected() instanceof Page){
			Project last = ((Workspace)model.getRoot()).getProjects().get(((Workspace)model.getRoot()).getProjects().size()-1);
			if(last.getName().equals("freeGeRuDocuments")){
				for(GeRuDokument g : last.getGerudokuments()){
					for(Page pp : g.getPages()){
						if(pp==((Page)tree.getSelected())){
							return;
						}
					}
					
				}
			}
		}
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
    	if(SwingUtilities.isRightMouseButton(e)){
	         int selRow = tree.getRowForLocation(e.getX(), e.getY());
	         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
	                 tree.setSelectionPath(selPath); 
	                 if (selRow>-1){
	                    tree.setSelectionRow(selRow); 
	                 }
		}
		if(!(tree.getSelected() instanceof Workspace ||
				tree.getSelected() instanceof GeRuDokument ||
				tree.getSelected() instanceof Page ||
				tree.getSelected() instanceof Project) && e.isPopupTrigger()){
					doPopWorkspace(e);
					return;
		}
		if(tree.getSelected() instanceof Project){
			if(((Project)tree.getSelected()).getName().equals("freeGeRuDocuments"))
				return;
		}
		if(tree.getSelected() instanceof GeRuDokument){
			Project last = ((Workspace)model.getRoot()).getProjects().get(((Workspace)model.getRoot()).getProjects().size()-1);
			if(last.getName().equals("freeGeRuDocuments")){
				for(GeRuDokument g : last.getGerudokuments()){
					if(g==((GeRuDokument)tree.getSelected())){
						if (e.isPopupTrigger())
							doPopShare(e);
						return;
					}
				}
			}
		}
		if(tree.getSelected() instanceof Page){
			Project last = ((Workspace)model.getRoot()).getProjects().get(((Workspace)model.getRoot()).getProjects().size()-1);
			if(last.getName().equals("freeGeRuDocuments")){
				for(GeRuDokument g : last.getGerudokuments()){
					for(Page pp : g.getPages()){
						if(pp==((Page)tree.getSelected())){
							return;
						}
					}
					
				}
			}
		}
       if (e.isPopupTrigger())
           doPop(e);
    }
    private void doPopWorkspace(MouseEvent e){
    	WorkspacePopupMenu menu = new WorkspacePopupMenu(model,tree);
    	menu.show(e.getComponent(), e.getX(), e.getY());
    }
    private void doPop(MouseEvent e){
        TreePopupMenu menu = new TreePopupMenu(model, tree);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
    private void doPopShare(MouseEvent e){
        TreePopupShare menu = new TreePopupShare(model, tree);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

	public WorkspaceModel getModel() {
		return model;
	}

	public void setModel(WorkspaceModel model) {
		this.model = model;
	}
}
