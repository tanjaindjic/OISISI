package controller.command;

import model.Clipboard;
import model.ClipboardContentType;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;

public class CutCommand extends AbstractCommand {

	private ClipboardContentType type;
	private Object parent;
	
	public CutCommand() {
	
		this.selectedObject=Clipboard.getInstance().getContent();
		type=Clipboard.getInstance().getType();
		parent= getParent(this.selectedObject);
	}
	
	@Override
	public boolean doCommand() {
		
		switch (type) {
			case PROJECT:
				//RemoveProject rp = new RemoveProject((Workspace)parent, (Project) this.selectedObject);
			//	return rp.doCommand();
				return false;
			
			case GERUDOKUMENT:
				
				if(parent instanceof Project){
					RemoveDocument rd = new RemoveDocument( (Project)parent, (GeRuDokument) this.selectedObject );
					return rd.doCommand();
				}
				return false;
				
			case PAGE:
				
				if(parent instanceof GeRuDokument){
					RemovePage rpa = new RemovePage((GeRuDokument)parent, (Page)this.selectedObject );
					return rpa.doCommand();
				}
				
				return false;
				
			default:
			
				return false;
		}
		
	}

	@Override
	public boolean undoCommand() {
		
		switch (type) {
		case PROJECT:
			//RemoveProject rp = new RemoveProject((Workspace)parent, (Project) this.selectedObject);
			//return rp.undoCommand();
		
		case GERUDOKUMENT:
			
			if(parent instanceof Project){
				RemoveDocument rd = new RemoveDocument( (Project)parent, (GeRuDokument) this.selectedObject );
				return rd.undoCommand();
			}
			return false;
			
		case PAGE:
			
			if(parent instanceof GeRuDokument){
				RemovePage rpa = new RemovePage((GeRuDokument)parent, (Page)this.selectedObject );
				return rpa.undoCommand();
			}
			
			return false;
			
		default:
		
			return false;
	}
				
}
	
	
	private Object getParent(Object node){
		Workspace wsp = (Workspace) GeRuDokModel.getInstance().getActiveWorkspace();
		if(node instanceof Project)
			return (Object)wsp;
		for(int i=0;i<wsp.getChildCount(); i++){
			if(wsp.getChildAt(i)==node)
				return wsp;
			if(node instanceof Page || node instanceof GeRuDokument){
				Project project = (Project)wsp.getChildAt(i);
				for(int j=0; j<project.getChildCount(); j++){
					if(project.getChildAt(j)==node)
						return project;				
					if(node instanceof Page){
						GeRuDokument grd =  (GeRuDokument) project.getChildAt(j);
						for(int k=0; k<grd.getChildCount(); k++){
							if(grd.getChildAt(k)==node)
								return grd;
						}
					}
				}
			}
		}
		return null;
	}
	
	

}
