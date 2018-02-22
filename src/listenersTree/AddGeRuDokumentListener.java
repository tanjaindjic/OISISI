package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.GeRuDokController;
import controller.command.AddDocumentCommand;
import controller.command.CommandManager;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.Project;
import model.WorkspaceModel;
import view.Tree;

public class AddGeRuDokumentListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public AddGeRuDokumentListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s1= tree.getExpansionState();
		
		Object selected = tree.getSelected();
		//provera da li je selektovani projekat read only ako je serovan u ovaj workspace
		if(tree.getSelected() instanceof Project){
			if(GeRuDokController.getInstance().isReadOnly((Project)tree.getSelected())){
				JOptionPane.showMessageDialog(null, "Shared project is read-only.");
				return;
			}
		}
		
		if(selected instanceof Project){
			
			CommandManager cmd = CommandManager.getCMD();
			String name = JOptionPane.showInputDialog(null, "Please enter a name for your GeRuDocument: ");
			//provera svih da li su istog imena
			if(name==null)
				return;
			if(name.trim().isEmpty()){
				return;
			}
		
			
			cmd.addAbstractCommand(new AddDocumentCommand((Project)selected, new GeRuDokument(name, GeRuDokModel.getInstance().getCurrentUser().getPathToUsersFolder(), tree) ));
		}
		model.reload();
		if( !s1.trim().equals("")){
		 tree.setExpansionState(s1);
		 }
	}

}
