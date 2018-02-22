package listenersTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;

import controller.command.AddPageCommand;
import controller.command.AddSlotCommand;
import controller.command.CommandManager;
import dialogs.AddPageDialog;
import dialogs.AddSlotDialog;
import model.Page;
import model.Project;
import model.Slot;

public class AddSlotDialogListener implements ActionListener {
	private double width;
	private double height;	
	private AddSlotDialog asd;
	private Page page;
	private DefaultTreeModel model;
	
	public AddSlotDialogListener(AddSlotDialog asd, Page p, DefaultTreeModel model) {
		this.asd = asd;
		this.page =p;
		this.model= model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			height = Double.parseDouble(AddSlotDialog.tfHeight.getText());
			width = Double.parseDouble(AddSlotDialog.tfWidth.getText());
			} catch(Exception exception ){
				JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
				return;	
			}
			if( width<1 || height<1){
				JOptionPane.showMessageDialog(null,"Width or Height is not valid.");
				return;	
			}
			if( page.getWidth() < width || page.getHeight() < height){
				JOptionPane.showMessageDialog(null,"Width or Height is too big.");
				return;	
			}
			Slot slot = new Slot();
			slot.setHeight(height);
			slot.setWidth(width);
			slot.setUniqueID(UUID.randomUUID());
			
			CommandManager cmd = CommandManager.getCMD();
			cmd.addAbstractCommand(new AddSlotCommand( page, slot ));
			asd.dispose();
	}
	
}

