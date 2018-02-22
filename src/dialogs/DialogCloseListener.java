package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DialogCloseListener implements ActionListener {
	private AddPageDialog apd;
	private AddSlotDialog asd;
	private ShowPropertiesNodeDialog spd;
	private ShareProjectDialog shpd;
	private ShareGeRuDokumentDialog sgrdd;
	
	public DialogCloseListener(AddPageDialog apd) {
		this.apd = apd;
		this.asd = null;
		this.spd=null;
		this.shpd = null;
		this.sgrdd = null;
	}
	public DialogCloseListener(AddSlotDialog asd) {
		this.apd = null;
		this.asd = asd;
		this.spd=null;
		this.shpd = null;
		this.sgrdd = null;
	}
	public DialogCloseListener(ShowPropertiesNodeDialog spd) {
		this.apd = null;
		this.asd = null;
		this.spd=spd;
		this.shpd = null;
		this.sgrdd = null;
	}
	public DialogCloseListener(ShareProjectDialog shpd) {
		this.apd = null;
		this.asd = null;
		this.spd=null;
		this.shpd = shpd;
		this.sgrdd = null;
	}

	public DialogCloseListener(ShareGeRuDokumentDialog sgrdd) {
		this.sgrdd = sgrdd;
		this.apd = null;
		this.asd = null;
		this.spd=null;
		this.shpd = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(asd!=null){
			asd.dispose();
			return;
		}
		if(apd!=null){
			apd.dispose();
			return;
		}
		if(spd!=null){
			spd.dispose();
			return;
		}
		if(shpd!=null){
			shpd.dispose();
			return;
		}
		if(sgrdd!=null){
			sgrdd.dispose();
			return;
		}
	}

	
}

