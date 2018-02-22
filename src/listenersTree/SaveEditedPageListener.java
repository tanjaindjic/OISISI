package listenersTree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import dialogs.AddPageDialog;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import view.MainWindow;
import view.PageView;
import view.WorkArea;

public class SaveEditedPageListener implements ActionListener {
	private String name;
	private double width;
	private double height;	
	private AddPageDialog apd;
	private Page page;
	private DefaultTreeModel model;
	
	public SaveEditedPageListener(AddPageDialog apd, DefaultTreeModel model, Page p) {
		this.apd = apd;
		this.model= model;
		this.page = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(apd.tfName.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Name can not be empty");
			return;
		}
		try{
		height = Double.parseDouble(apd.tfHeight.getText());
		width = Double.parseDouble(apd.tfWidth.getText());
		} catch(Exception exception ){
			JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
			return;	
		}
		if( width<1 || height<1){
			JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
			return;	
		}
		page.setName(apd.tfName.getText().trim());
		page.setHeight(height);
		page.setWidth(width);
		
		Workspace root = (Workspace) model.getRoot();
		ArrayList<Project> proj = root.getProjects();
		for(Project p : proj){
			ArrayList<GeRuDokument> docs = p.getGerudokuments();
			for(GeRuDokument g : docs){
				if(g.containsChild(page.getUniqueID())){
					PageView pv = new PageView(g);
					int a= WorkArea.getInstance().getTabCount();
					if(a>0){
						for(int j=0; j<a; j++){
							if(WorkArea.getInstance().getTitleAt(j).contains(g.getUniqueID().toString())){
								WorkArea.getInstance().removeTabAt(j);	
							}
						}
					}
					String name = (g).getName() + " (" + g.getUniqueID().toString() +")";					
					WorkArea.getInstance().add(name, new JScrollPane(pv));
					
//dodavanje X na tabove
					
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
					
					break;
				}
			}
		}
		
		model.reload();
		apd.dispose();
	}

	
	
}

