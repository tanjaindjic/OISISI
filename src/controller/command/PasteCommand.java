package controller.command;

import java.util.ArrayList;
import java.util.UUID;

import controller.GeRuDokController;
import model.Clipboard;
import model.ClipboardContentType;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.ShareTypes;
import model.Workspace;
import view.Tree;

public class PasteCommand extends AbstractCommand  {

	
	private Object pastedObject;
	private ClipboardContentType type;
	private Tree t;
	
	public PasteCommand(Object o, Tree t) {
		this.selectedObject=o;
		this.t=t;
		pastedObject=null;
		type=null;
	}
	
	@Override
	public boolean doCommand() {
		
		ClipboardContentType cct = Clipboard.getInstance().getType();
		type=cct;
		
		Object obj = Clipboard.getInstance().getContent();
		
		switch (cct) {
		case PROJECT: //Kopiranje projekta trenutno nema smisla ali ce mozda nekada imati pa ostaje ovo zbog prisrivosti koda
			if(this.selectedObject instanceof Workspace){
				
				Project p = (Project) obj;
				Project proj = p.getClone(p);
				
				proj.addObserver(t);
				pastedObject=proj;
				AddProjectCommand apc = new AddProjectCommand(proj, (Workspace) this.selectedObject );
				return apc.doCommand();
				
			}
			
			break;
		case GERUDOKUMENT:
			if(this.selectedObject instanceof Project){
				
				GeRuDokument grd = (GeRuDokument) obj;
				
				GeRuDokument g = new GeRuDokument();
				g.setUniqueID(UUID.randomUUID());
				g.setName(grd.getName());
				g.setPath(grd.getPath()+"copy");
				g.setOwners(grd.getOwners());
				g.setPages(new ArrayList<Page>());
				
				for(Page p : grd.getPages()){
					g.getPages().add(p);
				}
				g.addObserver(t);
				pastedObject=g;
				AddDocumentCommand adc = new AddDocumentCommand((Project)this.selectedObject, g, ShareTypes.COPY);
				return adc.doCommand();
			}
			
			break;
		case PAGE:
			if(this.selectedObject instanceof GeRuDokument){
				
				Page pag = (Page) obj;
				Page p = new Page();
				p.setUniqueID(UUID.randomUUID());
				p.setName(pag.getName());
				p.setPath("");
				p.setDimensions(pag.getWidth(), pag.getHeight(), pag.getX(), pag.getY());
				p.setSlots(pag.getSlots());
				p.addObserver(t);
				pastedObject=p;
				AddPageCommand apc = new AddPageCommand((GeRuDokument)this.selectedObject, p);
				return apc.doCommand();
				
			}
			
			break;
		case SLOT: // Trenutno je nemoguce kopiranje slota
			
			
			break;
		case EMPTY:
			
			break;
			
		default:
			break;
		}
		
		
		return false;
	}

	@Override
	public boolean undoCommand() {
		if(pastedObject != null){
			
			switch (type) {
			case PROJECT:
				if(this.selectedObject instanceof Workspace){ // Nikada tu ne moze doci, ostaje zbog prosirivosti koda
					
					Project p = (Project) pastedObject;
					GeRuDokController.getInstance().copy(p);
					
					
					AddProjectCommand apc = new AddProjectCommand(p, (Workspace) this.selectedObject );
					return apc.undoCommand();
					
				}
				
				break;
			case GERUDOKUMENT:
				if(this.selectedObject instanceof Project){
					
					GeRuDokument grd = (GeRuDokument) pastedObject;
					GeRuDokController.getInstance().copy(grd);
					AddDocumentCommand adc = new AddDocumentCommand((Project)this.selectedObject, grd);
					return adc.undoCommand();
				}
				
				break;
			case PAGE:
				if(this.selectedObject instanceof GeRuDokument){
					
					Page pag = (Page) pastedObject;
					GeRuDokController.getInstance().copy(pag);
					AddPageCommand apc = new AddPageCommand((GeRuDokument)this.selectedObject, pag);
					return apc.undoCommand();
					
				}
				
				break;
			case SLOT: // Nije moguce kopirati slot
				
				
				break;
			case EMPTY:
				
				break;
				
			default:
				break;
			}
			
			
			return false;
			
			
			
			
		}
			
			
		return false;
	}

}
