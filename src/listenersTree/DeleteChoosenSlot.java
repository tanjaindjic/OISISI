package listenersTree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.GeRuDokController;
import dialogs.EditDeleteSlotDialog;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import view.MainWindow;
import view.PageView;
import view.Tree;
import view.WorkArea;

public class DeleteChoosenSlot implements ActionListener{
	
	private EditDeleteSlotDialog dial;
	private Tree tree;
	private Page page; 
	
	public DeleteChoosenSlot(EditDeleteSlotDialog edsd, Page p, Tree tree) {
		this.dial = edsd;
		this.tree = tree;
		this.page = p;
	}

	private Object getParent(Object node){
		Workspace wsp = (Workspace) tree.getModel().getRoot();
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
		GeRuDokument g = (GeRuDokument) getParent(page);
		Project pr = (Project) getParent(g);
		//provera da li je projekat readonly 
		if(GeRuDokController.getInstance().isReadOnly(pr)){
			JOptionPane.showMessageDialog(null, "Shared Project is read-only.");
			return;
		}
		
		if(GeRuDokController.getInstance().isReadOnly(g)){
			JOptionPane.showMessageDialog(null, "Shared GeRuDocument is read-only.");
			return;
		}
	
	
		
		if(dial.slot.getSelectedIndex()==-1){
			JOptionPane.showMessageDialog(null, "There is no Slot to be deleted.");
			return;
		}
		dial.getPage().getSlots().remove(dial.slot.getSelectedIndex());
		
		Workspace w = (Workspace) tree.getModel().getRoot();
		ArrayList<Project> projs = w.getProjects();
		for(Project p : projs){
			ArrayList<GeRuDokument> docs = p.getGerudokuments();
			for(GeRuDokument grd : docs){
				if(grd.containsChild(page.getUniqueID())){
					
					PageView pv = new PageView(grd);
					if(grd instanceof GeRuDokument){				
						if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
							
							WorkArea.getInstance().removeTabAt(0);		
					}
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						for(int j=0; j<a; j++){
							if(WorkArea.getInstance().getTitleAt(j).contains(grd.getUniqueID().toString())){
								WorkArea.getInstance().removeTabAt(j);	
							}
						}
					}
					String name = (grd.getName() + " (" + grd.getUniqueID().toString() +")");
					
					WorkArea.getInstance().add(name, new JScrollPane(pv));
					
//dodvanje X na tabove
					
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
		}
		
		
		dial.dispose();
	}

	
	
}
