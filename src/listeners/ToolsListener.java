package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import listenersTree.AddProjectListener;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tool;
import view.Tree;

public class ToolsListener implements ActionListener{
	private Tree tree;
	private WorkspaceModel model;
	private Tool t;
	
	public ToolsListener(Tree t, WorkspaceModel m){
		this.tree = t;
		this.model = m;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String selected = arg0.getActionCommand();
		if(selected.equals("Open")){
			JFileChooser chooser = new JFileChooser();
			FileFilter filter =new FileNameExtensionFilter("proj");
			
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(filter);
			int response=chooser.showOpenDialog(null);
			if( response ==JFileChooser.APPROVE_OPTION){
				String path=chooser.getSelectedFile().getAbsolutePath();
				
				int index = path.lastIndexOf("\\");
				String fileName = path.substring(index + 1);
				
				Object root =(Object) model.getRoot();
				Workspace wsp = (Workspace) root;
				wsp.getProjects().add(new Project(fileName, path,tree));
			}else return;
				
		}
		
		else if(selected.equals("Delete")){
			
		}
		
	}

}
