package controller.command;

import model.Owner;
import model.Project;
import model.ShareTypes;
import model.Workspace;

public class AddProjectCommand extends AbstractCommand {

	private Workspace parent;

	private Project child;

	public boolean doCommand() {
		return parent.addChild(child);
	}

	public boolean undoCommand() {
		return parent.deleteChild(child.getUniqueID());
	}

	public AddProjectCommand(Project child, Workspace parent) {
		this.child=child;
		child.addOwner(parent,ShareTypes.OWNER);
		this.parent=parent;
		this.selectedObject=child;
	
	}

}
