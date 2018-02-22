package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialogs.ShareProjectDialog;

public class ShareProjectListener implements ActionListener {

	private ShareProjectDialog shd;
	
	public ShareProjectListener( ShareProjectDialog s) {
		this.shd=s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		shd.share();
	}

}
