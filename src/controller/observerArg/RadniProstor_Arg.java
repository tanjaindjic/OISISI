package controller.observerArg;

import model.Project;
import model.Workspace;

public class RadniProstor_Arg { 
	
		   private Project projekat; 
		  
		   private Workspace radniProstor;
		   
		   public Akcija akcijeRP; 
		   
		   public Workspace getRProstor() {
		      return radniProstor;
		   }
		   
		   
		   public Project getProjekat() {
		      return projekat;
		   }
		 
		
		   public RadniProstor_Arg(Project projekat, Workspace radniProstor, Akcija akcijaNadRP) { 
		      
			  this.projekat= projekat;
			  this.radniProstor=radniProstor;
			  this.akcijeRP= akcijaNadRP;
		   }
	
}
