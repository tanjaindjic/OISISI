package controller.command;

import model.GeRuDokument;
import model.Page;

public class AddPageCommand extends AbstractCommand {

	private GeRuDokument parent;

	private Page child;

	public AddPageCommand(GeRuDokument parent, Page child) {
		this.parent=parent;
		this.child=child;
		selectedObject = child;
	}

	

	public boolean doCommand() {
		RemovePage r = new RemovePage(parent, child);
		return r.undoCommand();
	}

	public boolean undoCommand() {
		RemovePage r = new RemovePage(parent, child);
		return r.doCommand();
	}

}
