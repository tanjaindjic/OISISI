package controller;

import java.util.UUID;

import model.Clipboard;
import model.ClipboardContentType;
import model.Document;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.Owner;
import model.Page;
import model.Project;
import model.ShareTypes;
import model.Slot;
import model.Workspace;

public class GeRuDokController {
	
	private static GeRuDokController controll = null; 
	
	private GeRuDokController() {
	}
	public static GeRuDokController getInstance(){ 
		if (controll == null){
			controll = new GeRuDokController();
		}
		
		return controll;
	}

	private static void deleteInstance() {
		controll = null;
	}
	
	
	
	public boolean isReadOnly(Document d){ 
		
		UUID u = GeRuDokModel.getInstance().getActiveWorkspace().getUniqueID();
		Workspace ws = GeRuDokModel.getInstance().getActiveWorkspace();
		
		String s1 = u.toString();
		
		if(d instanceof Project){
			Project p =(Project) d;
			for(Owner o : p.getOwners()){
				if(o.getType() == ShareTypes.READ){
					UUID uu = o.getId();
					String s2 = uu.toString();
					if(o.getId().toString().equals(GeRuDokModel.getInstance().getActiveWorkspace().getUniqueID().toString())){
						return true;
					}
				}
			}
		}else if(d instanceof GeRuDokument){
			GeRuDokument grd = (GeRuDokument)d;
			for(Owner o : grd.getOwners()){
				if(o.getType() == ShareTypes.READ){
					
					for( Project p1 : GeRuDokModel.getInstance().getActiveWorkspace().getProjects()){
						if(o.getId().toString().equals(p1.getUniqueID().toString())){
							return true;
						}
					}
					
				}
				
			}
			
		}else{
			System.out.println("Poslato je nesto sto nije Projekat ili Gerudokument");
		}
		
		return false;
	}
	
	
	
	public void copy(Object o){
		
		ClipboardContentType cct=null;
		
		if(o instanceof Workspace){ // WORKSPACE I PROJEKAT NIJE MOGUCE KOPRIATI 
			cct = ClipboardContentType.EMPTY;
			
		}else if( o instanceof Project){ 
			cct = ClipboardContentType.EMPTY;
			
		}else if(o instanceof GeRuDokument){
			cct = ClipboardContentType.GERUDOKUMENT;
			
		}else if(o instanceof Page){
			cct = ClipboardContentType.PAGE;
			
		}else if(o instanceof Slot){
			cct = ClipboardContentType.SLOT;
			
		} else{
			cct = ClipboardContentType.EMPTY;
			
		}
		Clipboard.getInstance().setType(cct);
		if(cct != ClipboardContentType.EMPTY){
			Clipboard.getInstance().setContent(o);
		}
		
	}
	

}
