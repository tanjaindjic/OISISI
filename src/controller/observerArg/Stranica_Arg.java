package controller.observerArg;

import model.Page;
import model.Slot;

public class Stranica_Arg {
	
	
	 private Slot slot;
	
	   private Page stranica; 

	   public Akcija akcijeStranica;
	   

	   public Slot getSlot() {
	      return slot;
	   }

	   public Page getStranica() {
	      return stranica;
	   }

	   public Stranica_Arg(Slot slot, Page stranica, Akcija akcijaNadSTR) {
		   
		   this.slot=slot;
		   this.stranica=stranica;
		   this.akcijeStranica=akcijaNadSTR;
	   }

}
