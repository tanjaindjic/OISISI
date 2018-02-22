
package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.command.CommandManager;
import model.Clipboard;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.JsonModel;
import model.Page;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class OpenWorkspaceListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public OpenWorkspaceListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}

	private Workspace updateShares(Workspace ws){
		int index=-1;
		
		JsonModel jm= new JsonModel();
		for(Project p : ws.getProjects()){
			index++;
			Project temp=jm.loadProjectFromJSon(p.getPath());
			
			if(temp != null){
				
				p=ws.getProjects().set(index, temp);
			}			
		}
		return ws;
		}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(tree.isShowing()){
			
			int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save workspace before closing?","alert", JOptionPane.YES_NO_CANCEL_OPTION);
			if(confirm==0){
				JsonModel jm = new JsonModel();
				jm.saveModel((Workspace)model.getRoot());
			}else if(confirm == 2){
				return;
			}
		}
		JFileChooser chooser = new JFileChooser();
		//TODO treba dodati ekstenziju, puca program trenutno sa ovim filterom!
		FileFilter filter =new FileNameExtensionFilter("work", "work");
		chooser.setFileFilter(filter);
		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		

		int response=chooser.showOpenDialog(null);
		if( response ==JFileChooser.APPROVE_OPTION){
			String path=chooser.getSelectedFile().getAbsolutePath();
			JsonModel jm = new JsonModel();
			Workspace ws = jm.loadWorkspaceFromJSon(path);
			if(ws==null){
				
				return;
			}
			
			ws= updateShares(ws);
			
			GeRuDokModel.getInstance().setActiveWorkspace(ws);
			//tree.setModel(new WorkspaceModel(ws));
			model.setRoot(ws);
			
			ws.addObserver(tree);
			
			for(Project p : ws.getProjects()){
				
				p.addObserver(tree);
				for(GeRuDokument g : p.getGerudokuments()){
					g.addObserver(tree);
					for(Page pa : g.getPages()){
						pa.addObserver(tree);
					}
				}
			}
			CommandManager.getCMD().deleteAll();
			Clipboard.getInstance().delete();
			tree.show();
		}
		
		
	
	}

}
