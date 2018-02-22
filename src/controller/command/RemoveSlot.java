package controller.command;

import model.Page;
import model.Slot;

public class RemoveSlot extends AbstractCommand {

	private Page parent;

	private Slot child;

	public RemoveSlot(Page parent, Slot child) {
		
		this.parent = parent;
		this.child = child;
		selectedObject = child;
	}

	public boolean doCommand() {
		
		return parent.deleteChild(child.getUniqueID());
	}

	public boolean undoCommand() {
		
		return parent.addChild(child);
	}

}
