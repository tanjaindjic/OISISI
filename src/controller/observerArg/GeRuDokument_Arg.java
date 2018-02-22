package controller.observerArg;

import java.util.ArrayList;

import model.GeRuDokument;
import model.Page;

public class GeRuDokument_Arg { 
	
	private Page stranica;

	   private GeRuDokument grdokument;
	 

	   public Akcija akcijeGRD;
	   
	
	   public Page getStranica() {
	      return stranica;
	   }
	   
	
	   public GeRuDokument getGRDokument() {
	      return grdokument;
	   }
	   

	   public GeRuDokument_Arg(Page stranica, GeRuDokument grdokument, Akcija akcijaNadGRD) {
		   this.stranica=stranica;
		   this.grdokument=grdokument;
		   this.akcijeGRD=akcijaNadGRD;
	   }

}
