package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.MainWindow;
import view.PageView;
import view.WorkArea;

public class EditSlotDialogListener implements ActionListener {
	private AddSlotDialog dial; 
	private Page page; 
	private int ind; 
	private WorkspaceModel model;
	
	public EditSlotDialogListener(AddSlotDialog asd, Page p, int i, WorkspaceModel model) {
		this.dial = asd;
		this.page = p;
		this.ind = i;
		this.model = model;
	}
	
	public EditSlotDialogListener(AddSlotDialog asd, Page p, int i) {
		this.dial = asd;
		this.page = p;
		this.ind = i;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		double height = 0;
		double width = 0;
		try{
			height = Double.parseDouble(dial.tfHeight.getText());
			width = Double.parseDouble(dial.tfWidth.getText());
			} catch(Exception exception ){
				JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
				return;	
			}
			if( width<1 || height<1){
				JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
				return;	
			}
			if( page.getWidth() < width || page.getHeight() < height){
				JOptionPane.showMessageDialog(null,"Width or Height is too big.");
				return;	
			}
			page.getSlots().get(ind).setHeight(height);
			page.getSlots().get(ind).setWidth(width);
			
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
						String name = g.getName() + " (" + g.getUniqueID().toString() +")";
						
						WorkArea.getInstance().add(name, new JScrollPane(pv));
						WorkArea.getInstance().revalidate();
						WorkArea.getInstance().repaint();
						MainWindow.getInstance().tabbedPane.revalidate();
						MainWindow.getInstance().tabbedPane.repaint();
						MainWindow.getInstance().tabbedPane.setVisible(true);
						
						break;
					}
				}
				
			//page.getSlots().add(slot);
			dial.dispose();
	}

	}
	
}
