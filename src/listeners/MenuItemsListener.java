package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeModel;

import listenersTree.AddProjectListener;
import listenersTree.NewWorkspaceListener;
import login.LogInFrame;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.JsonModel;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.MainWindow;
import view.MenuItem;
import view.ProfileDetails;
import view.Tree;
import view.WorkArea;

public class MenuItemsListener implements ActionListener{
		private MenuItem mi;
		private Tree tree;
		
		public MenuItemsListener(MenuItem mi, Tree t){
			this.mi = mi;
			this.tree=t;
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String selected = arg0.getActionCommand();
		if(selected.equals("Close Project")){
			if(tree.getLastSelectedPathComponent() instanceof Project)
				tree.collapsePath(tree.getSelectionPath());
		}
		else if(selected.equals("Close Workspace")){
			WorkspaceModel model = mi.getWModel();
			if (JOptionPane.showConfirmDialog(null, "Do you want to save your work before exiting?", "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    // sve sacuva u JSON
				JsonModel jm = new JsonModel();
				jm.saveModel(GeRuDokModel.getInstance().getActiveWorkspace());
				int a= WorkArea.getInstance().getTabCount();
				if(a>0){

					WorkArea.getInstance().removeAll();	
				}
			
			/*	Workspace root = new Workspace();
				root.setName(name);
				
				GeRuDokModel gmd=GeRuDokModel.getInstance();
				gmd.setActiveWorkspace(root);
				model.setRoot(root);
			//	tree.setModel(new WorkspaceModel(root)); zbog ove linije nije radilo
				tree.show();
				root.addObserver(tree);*/
				
			
				Workspace w = (Workspace) model.getRoot();
				w.setName("orengvoiseur823ru");
				
				//tree.setModel((TreeModel) w);
				tree.setEditable(false);
				tree.show(false);
				
				
				
				
			} else {
				int a= WorkArea.getInstance().getTabCount();
				if(a>0){
							WorkArea.getInstance().removeAll();	
						
					
				}
				Workspace wsp = (Workspace)model.getRoot();
				for(int i=0; i<wsp.getProjects().size(); i++){
					wsp.getProjects().remove(i);
				}
				/*model = new WorkspaceModel();
				tree.setModel(model);
				tree.show(false);*/
				Workspace w = (Workspace) model.getRoot();
				w.setName("orengvoiseur823ru");
				//tree.setModel((TreeModel) w);
				tree.setEditable(false);
				tree.show(false);
				
				
			}
			
			
			
		}
		
		/*else if(selected.equals("Import")){
			//ucitava postojeci projekat
			JFileChooser chooser = new JFileChooser();
			FileFilter filter =new FileNameExtensionFilter("prj");
			
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(filter);
			int response=chooser.showOpenDialog(null);
			if( response ==JFileChooser.APPROVE_OPTION){
				String path=chooser.getSelectedFile().getAbsolutePath();
				
				int index = path.lastIndexOf("\\");
				String fileName = path.substring(index + 1);
				//ucitati novi projekat sa ovim nazivom i pathom
			}else return;
				
		}
		else if(selected.equals("Export")){
			//???
		}*/
		else if(selected.equals("Properties")){
			
		}
		else if(selected.equals("Exit")){
			if (JOptionPane.showConfirmDialog(null, "Do you want to save your work before exiting?", "WARNING",
			        JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			    // sve sacuva u JSON
				SaveAsListener n = new SaveAsListener();
				n.actionPerformed(null);
				System.exit(0);
			} else {
				System.exit(0);
			}
		}
		else if(selected.equals("Undo")){
			mi = (MenuItem) arg0.getSource();
			mi.addActionListener(new UndoListener(mi.getWModel(), tree));
		}
		else if(selected.equals("Redo")){
			new RedoListener((WorkspaceModel)tree.getModel(), tree);			
		}
		else if(selected.equals("Cut")){
			new CutListener((WorkspaceModel)tree.getModel(), tree);
		}
		else if(selected.equals("Copy")){
			new CopyListener((WorkspaceModel)tree.getModel(), tree);
		}
		else if(selected.equals("Paste")){
			new PasteListener((WorkspaceModel)tree.getModel(), tree);
		}
		else if(selected.equals("Hide")){
			MainWindow mw = MainWindow.getInstance();
			mw.getScrollPane().setVisible(false);
		}
		else if(selected.equals("Show")){
			MainWindow mw = MainWindow.getInstance();
			mw.getScrollPane().setVisible(true);
		}
		
		else if(selected.equals("Details")){
			ProfileDetails pd = ProfileDetails.getInstance();
			pd.setVisible(true);
		}
		
		else if(selected.equals("Sign out")){
			if (JOptionPane.showConfirmDialog(null, "Do you want to save your work before exiting?", "WARNING",
			        JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			    // sve sacuva u JSON
				SaveAsListener n = new SaveAsListener();
				n.actionPerformed(null);
			}
				
				
				
				GeRuDokModel.getInstance().setActiveWorkspace(null);
				GeRuDokModel.getInstance().setCurrentUser(null);
				LogInFrame lf = LogInFrame.getInstance();
				MainWindow.getInstance().deleteMainWidow();
				lf.textName.setText("");
				lf.passField.setText("");
				lf.show();
		}
		
		
	}

}
