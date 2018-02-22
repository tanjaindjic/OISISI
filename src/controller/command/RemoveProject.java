package controller.command;

import java.io.File;

import model.GeRuDokument;
import model.Owner;
import model.Project;
import model.ShareTypes;
import model.Workspace;

public class RemoveProject extends AbstractCommand {

	private Workspace parent;

	private Project child;
	
	private ShareTypes type;
	
	public RemoveProject(Workspace parent, Project child) {
		
		this.parent = parent;
		this.child = child;
		selectedObject = child;
		type=child.getOwner(parent.getUniqueID());
		
		
	}

	public boolean doCommand() {
		
		for(GeRuDokument g : child.getGerudokuments()){
			
			try{
				File file = new File(g.getPath());
				file.delete();
    		
    		
			}catch(Exception e){
			}
		}
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
