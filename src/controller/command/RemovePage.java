package controller.command;

import model.GeRuDokument;
import model.Page;
import model.ShareTypes;

public class RemovePage extends AbstractCommand  {

	private GeRuDokument parent;
	private Page child;
	private ShareTypes type;

	public RemovePage(GeRuDokument parent, Page child) {
		this.parent=parent;
		this.child=child;
		selectedObject = child;
	}

	public boolean doCommand() {
		
		return parent.deleteChild(child.getUniqueID());
		
	}

	public boolean undoCommand() {
		
		
		return parent.addChild(child);
	}
	
}
