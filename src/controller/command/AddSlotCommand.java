package controller.command;

import model.Page;
import model.Slot;

public class AddSlotCommand extends AbstractCommand {

	private Page parent;

	private Slot child;

	public AddSlotCommand(Page parent, Slot child) {
		
		this.child=child;
		this.parent=parent;
		this.selectedObject=child;
	}

	public boolean doCommand() {
		
		RemoveSlot r = new RemoveSlot(parent, child);
		return r.undoCommand();
	}

	public boolean undoCommand() {
		
		RemoveSlot r = new RemoveSlot(parent, child);
		return r.doCommand();
	}

}
