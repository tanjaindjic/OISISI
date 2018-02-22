package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import dialogs.ShareGeRuDokumentDialog;
import dialogs.ShareProjectDialog;
import model.GeRuDokument;
import model.Project;
import model.WorkspaceModel;
import view.Tree;

public class ShareListener implements ActionListener {
	private String name;
	private double width;
	private double height;	
	private Tree tree;
	private WorkspaceModel model;
	
	public ShareListener(WorkspaceModel model, Tree t) {
		this.tree = t;
		this.model= model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(tree.getSelected() == null){
			return;
		}
		if(tree.getSelected() instanceof Project){
			ShareProjectDialog swd = new ShareProjectDialog( tree);
			swd.show();
			return;
		}
		if(tree.getSelected() instanceof GeRuDokument){
			//novi dijalog
			ShareGeRuDokumentDialog swd = new ShareGeRuDokumentDialog((GeRuDokument)tree.getSelected(), tree);
			swd.show();
			return;
		}
		return;
	}

	
	
	
}

