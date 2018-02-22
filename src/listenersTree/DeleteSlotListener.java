package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import dialogs.EditDeleteSlotDialog;
import model.Page;
import model.WorkspaceModel;
import view.Tree;

public class DeleteSlotListener implements ActionListener {
	
	private WorkspaceModel model;
	private Tree tree;
	
	public DeleteSlotListener(WorkspaceModel m, Tree t) {
		this.model = m;
		this.tree=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tree.getSelected()==null)
			return;
		if(tree.getSelected() instanceof Page){
			//poziva novi dijalog
			EditDeleteSlotDialog edsd = new EditDeleteSlotDialog((Page)tree.getSelected(),model, 0, tree);
			edsd.show();
			model.reload();
			
			return;
		}
	}

	
	
}
