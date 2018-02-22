package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.command.AddProjectCommand;
import controller.command.CommandManager;
import model.Clipboard;
import model.GeRuDokModel;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

public class OpenListener implements ActionListener{
	private WorkspaceModel model;
	private Tree tree;
	
	public OpenListener(WorkspaceModel model, Tree tree) {
		
		this.model=model;
		this.tree=tree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
		FileFilter filter =new FileNameExtensionFilter("proj", "PROJ");
		
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		int response=chooser.showOpenDialog(null);
		if( response ==JFileChooser.APPROVE_OPTION){
			String pp = chooser.getSelectedFile().getAbsolutePath();
			File f = new File(pp);
			Object root =(Object) model.getRoot();
			if(root instanceof Workspace){
				Workspace wsp = (Workspace) root;
				if(!tree.isShowing()){
					JOptionPane.showMessageDialog(null, "There is no Workspace. It should be opened first.");
					return;
				}
				CommandManager cmd = CommandManager.getCMD();
				
				cmd.addAbstractCommand(new AddProjectCommand( new Project(f.getName(),pp,tree), wsp ));
				if(wsp.getProjects().size()>1){
					if(wsp.getProjects().get(wsp.getProjects().size()-2).getName().equals("freeGeRuDocuments")){
						Project temp = wsp.getProjects().get(wsp.getProjects().size()-2);
						wsp.getProjects().set(wsp.getProjects().size()-2, wsp.getProjects().get(wsp.getProjects().size()-1));
						wsp.getProjects().set(wsp.getProjects().size()-1, temp);
					}
				}
			}	
			Clipboard.getInstance().delete();
			model.reload();
			
		}
	}

}
