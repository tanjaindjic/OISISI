package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import dialogs.AddPageDialog;
import model.GeRuDokument;
import model.WorkspaceModel;
import view.Tree;

public class AddPageListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public AddPageListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object selected = tree.getSelected();
		if(selected instanceof GeRuDokument){
			String s1= tree.getExpansionState();
			 
		AddPageDialog apd = new AddPageDialog((GeRuDokument)selected, model, tree);
			apd.setVisible(true);
			if( !s1.trim().equals("")){
			 tree.setExpansionState(s1);
			}
		}
	}

	
	
}
