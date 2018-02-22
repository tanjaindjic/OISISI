package controller.command;

import model.GeRuDokument;
import model.Project;
import model.ShareTypes;

public class AddDocumentCommand extends AbstractCommand {

	private Project parent;

	private GeRuDokument child;

	public AddDocumentCommand(Project parent, GeRuDokument child) {
		this.child=child;
		this.parent=parent;
		child.addOwner(parent,ShareTypes.OWNER);
		this.selectedObject=child;
	   }
	
	public AddDocumentCommand(Project parent, GeRuDokument child, ShareTypes s){
		this.child=child;
		this.parent=parent;
		child.addOwner(parent, s);
		this.selectedObject=child;
		
	}

	public boolean doCommand() {
		
		RemoveDocument r = new RemoveDocument(parent, child);
		return r.undoCommand();
		
	}

	public boolean undoCommand() {
	
		RemoveDocument r = new RemoveDocument(parent, child);
		return r.doCommand();
	}

}
