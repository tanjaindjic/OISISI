package controller.observerArg;

import model.GeRuDokument;
import model.Project;

public class Projekat_Arg {
	
	
	 private GeRuDokument dokument; 
	 
	   private Project projekat; 

	   public Akcija akcijeProjekat;
	   

	   public Project getProjekat() { 
	      return projekat;
	   }
	   
	
	   public GeRuDokument getDokument() {
	      return dokument;
	   }
	   

	   public Projekat_Arg(GeRuDokument dokument, Project projekat, Akcija akcijaNadProjektom) { // I KONSTRUKTOR
		   
		   this.dokument = dokument;
		   this.projekat= projekat;
		   this.akcijeProjekat=akcijaNadProjektom;
	   }

}
