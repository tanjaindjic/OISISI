package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.AddSlotDialog;
import dialogs.EditDeleteSlotDialog;
import model.WorkspaceModel;

public class EditChoosenSlot implements ActionListener {
	private EditDeleteSlotDialog dial; 
	private WorkspaceModel model;
	
	public EditChoosenSlot(EditDeleteSlotDialog edsd, WorkspaceModel model) {
		this.dial = edsd;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(dial.slot.getSelectedIndex()!=-1){
			//poziva novi dijalog
			AddSlotDialog asd = new AddSlotDialog(dial.slot.getSelectedIndex(), dial.getPage(), model);
			asd.show();
			dial.dispose();	
			return;
		}
		JOptionPane.showMessageDialog(null, "There is no Slot choosen.");
	}

	
	
}
