package controller.command;

import java.io.File;

import model.GeRuDokument;
import model.Project;
import model.ShareTypes;

public class RemoveDocument extends AbstractCommand {

	private GeRuDokument child;

	private Project parent;
	
	private ShareTypes type; 

	public RemoveDocument(Project parent, GeRuDokument child) {
		this.parent = parent;
		this.child = child;
		selectedObject = child;
		type=child.getOwner(parent.getUniqueID());
	}

	public boolean doCommand() {
		try{
    		File file = new File(child.getPath());
    		file.delete();
    	}catch(Exception e){
    	}
		
		return parent.deleteChild(child.getUniqueID());
	}

	public boolean undoCommand() {
		return parent.addChild(child);
	}
}
