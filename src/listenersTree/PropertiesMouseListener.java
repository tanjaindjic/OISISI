package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import dialogs.ShowPropertiesNodeDialog;
import view.Tree;

public class PropertiesMouseListener implements ActionListener {
	
	private Tree tree;
	
	public PropertiesMouseListener(Tree t) {
		this.tree = t;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//novi dijalog
		JDialog s = new ShowPropertiesNodeDialog(tree.getSelected());
		s.setVisible(true);		
	}
		
}
