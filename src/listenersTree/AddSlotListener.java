package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dialogs.AddSlotDialog;
import model.Page;
import model.WorkspaceModel;
import view.Tree;

public class AddSlotListener  implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public AddSlotListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object selected = tree.getSelected();
		if(selected instanceof Page){
			String s1= tree.getExpansionState();
			
			AddSlotDialog asd = new AddSlotDialog((Page)selected, model);
			asd.setVisible(true);
			if( !s1.trim().equals("")){
			 tree.setExpansionState(s1);
			}
		}
	}

	
	
}
