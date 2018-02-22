package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.ShareGeRuDokumentDialog;

public class ShareGeRuDokumentListener implements ActionListener {

	ShareGeRuDokumentDialog sh;
	
	public ShareGeRuDokumentListener(ShareGeRuDokumentDialog sh) {
	
		this.sh=sh;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		sh.share();
		
	}

}
