package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.command.AddProjectCommand;
import controller.command.CommandManager;
import model.Project;
import model.Workspace;
import model.WorkspaceModel;
import view.Tree;

@SuppressWarnings("serial")
public class AddProjectListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public AddProjectListener(WorkspaceModel model, Tree t) {
		this.model = model;
		this.tree=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object root =(Object) model.getRoot();
		String s1= tree.getExpansionState();
		if(root instanceof Workspace){
			//provera da li postoji workspce za daodavanje
			Workspace wsp = (Workspace) root;
			if(!tree.isShowing()){
				JOptionPane.showMessageDialog(null, "There is no Workspace. It should be opened first.");
				return;
			}
			String name = JOptionPane.showInputDialog(null, "Please enter a name for your Project: ");
			if(name==null)
				return;
			if(name.trim().isEmpty()){
				return;
			}
			if(name.equals("freeGeRuDocuments")){
				JOptionPane.showMessageDialog(null, "This name is reserved. Please, type another one.");
				return;
			}
			
			
			
			CommandManager cmd = CommandManager.getCMD();
			
			cmd.addAbstractCommand(new AddProjectCommand( new Project(name,"",tree), wsp ));
			if(wsp.getProjects().size()>1){
				if(wsp.getProjects().get(wsp.getProjects().size()-2).getName().equals("freeGeRuDocuments")){
					Project temp = wsp.getProjects().get(wsp.getProjects().size()-2);
					wsp.getProjects().set(wsp.getProjects().size()-2, wsp.getProjects().get(wsp.getProjects().size()-1));
					wsp.getProjects().set(wsp.getProjects().size()-1, temp);
				}
			}
			
		}		
		model.reload();
		if( !s1.trim().equals("")){
		 tree.setExpansionState(s1);
		}
	}

	
	
}
