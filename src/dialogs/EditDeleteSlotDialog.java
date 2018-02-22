package dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import listenersTree.DeleteChoosenSlot;
import listenersTree.EditChoosenSlot;
import model.Page;
import model.WorkspaceModel;
import view.Tree;

public class EditDeleteSlotDialog extends JDialog {
	public static JTextField workspace;
	public static JTextField tfWidth;
	public static JTextField tfHeight;
	public static JComboBox slot;
	private Page page;
	private WorkspaceModel model;
	private Tree tree;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}


	public EditDeleteSlotDialog(Page p, WorkspaceModel m, Tree tree){
		this.page=p;
		this.model = m;
		this.tree = tree;
			setTitle("GeRuDok - Edit Slot");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.3; //Procenat ekrana
			height=height*0.2; 
			setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			setIconImage(img.getImage());
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{220, 90};
			gbl_dial.rowHeights = new int[]{40, 40};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			setLayout(gbl_dial);
			
			JLabel lbName = new JLabel("Choose slot to be edited:   ");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =0;
			gbc_lbName.gridy =0;
			add(lbName, gbc_lbName);
			
			String[] allSlots = new String[page.getSlots().size()];
			for(int i=0; i<page.getSlots().size(); i++){
				allSlots[i] = "Slot"+(i+1);
			}
			
			slot = new JComboBox(allSlots);
			GridBagConstraints gbc_slot = new GridBagConstraints();
			gbc_slot.insets = new Insets(5, 5, 5, 5);
			gbc_slot.gridx =0;
			gbc_slot.gridy =1;
			slot.setPreferredSize(new Dimension(190, 25));
			add(slot, gbc_slot);
			
			JButton continueBtn = new JButton("Continue");
			GridBagConstraints gbc_continueBtn = new GridBagConstraints();
			gbc_continueBtn.insets = new Insets(5, 5, 5, 5);
			gbc_continueBtn.gridx =1;
			gbc_continueBtn.gridy =1;
			continueBtn.addActionListener(new EditChoosenSlot(this, model));
			continueBtn.setPreferredSize(new Dimension(90, 25));
			add(continueBtn, gbc_continueBtn);
	}
	public EditDeleteSlotDialog(Page p, WorkspaceModel m, int i, Tree t){
		this.page=p;
		this.tree = t;
			setTitle("GeRuDok - Delete Slot");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.3; //Procenat ekrana
			height=height*0.2; 
			setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			setIconImage(img.getImage());
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{220, 90};
			gbl_dial.rowHeights = new int[]{40, 40};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			setLayout(gbl_dial);
			
			JLabel lbName = new JLabel("Choose slot to be deleted:   ");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =0;
			gbc_lbName.gridy =0;
			add(lbName, gbc_lbName);
			
			String[] allSlots = new String[page.getSlots().size()];
			for(int j=0; j<page.getSlots().size(); j++){
				allSlots[j] = "Slot"+(j+1);
			}
			
			slot = new JComboBox(allSlots);
			GridBagConstraints gbc_slot = new GridBagConstraints();
			gbc_slot.insets = new Insets(5, 5, 5, 5);
			gbc_slot.gridx =0;
			gbc_slot.gridy =1;
			slot.setPreferredSize(new Dimension(190, 25));
			add(slot, gbc_slot);
			
			JButton deleteBtn = new JButton("Delete");
			GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
			gbc_deleteBtn.insets = new Insets(5, 5, 5, 5);
			gbc_deleteBtn.gridx =1;
			gbc_deleteBtn.gridy =1;
			deleteBtn.addActionListener(new DeleteChoosenSlot(this, page, tree));
			deleteBtn.setPreferredSize(new Dimension(90, 25));
			add(deleteBtn, gbc_deleteBtn);
	}
}
