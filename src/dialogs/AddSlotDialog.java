package dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;

import listenersTree.AddSlotDialogListener;
import model.Page;
import model.WorkspaceModel;

@SuppressWarnings("serial")
public class AddSlotDialog extends JDialog {
	private Page page; 
	private DefaultTreeModel model;
	public static JTextField tfName;
	public static JTextField tfWidth;
	public static JTextField tfHeight;
	
	public AddSlotDialog(Page p, DefaultTreeModel m){
		this.page=p;
		this.model =m;
			setModal(true);
			setTitle("GeRuDok - Add Slot");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.3; //Procenat ekrana
			height=height*0.3; 
			setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			setIconImage(img.getImage());
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{120,170, 90};
			gbl_dial.rowHeights = new int[]{10, 30, 30, 30, 30, 30};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			setLayout(gbl_dial);

			JLabel lbWidth = new JLabel("Slot width (px):");
			GridBagConstraints gbc_lbWidth = new GridBagConstraints();
			gbc_lbWidth.insets = new Insets(5, 5, 5, 5);
			gbc_lbWidth.gridx =0;
			gbc_lbWidth.gridy =2;
			add(lbWidth, gbc_lbWidth);
			
			tfWidth = new JTextField("");
			GridBagConstraints gbc_tfWidth = new GridBagConstraints();
			gbc_tfWidth.insets = new Insets(5, 5, 5, 5);
			gbc_tfWidth.gridx =1;
			gbc_tfWidth.gridy =2;
			tfWidth.setPreferredSize(new Dimension(150, 25));
			add(tfWidth, gbc_tfWidth);
			
			JLabel lbHeight = new JLabel("Slot height (px):");
			GridBagConstraints gbc_lbHeight = new GridBagConstraints();
			gbc_lbHeight.insets = new Insets(5, 5, 5, 5);
			gbc_lbHeight.gridx =0;
			gbc_lbHeight.gridy =3;
			add(lbHeight, gbc_lbHeight);
			
			tfHeight = new JTextField("");
			GridBagConstraints gbc_tfHeight = new GridBagConstraints();
			gbc_tfHeight.insets = new Insets(5, 5, 5, 5);
			gbc_tfHeight.gridx =1;
			gbc_tfHeight.gridy =3;
			tfHeight.setPreferredSize(new Dimension(150, 25));
			add(tfHeight, gbc_tfHeight);
			
			JButton btnAdd = new JButton("Add slot");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
			gbc_btnAdd.gridx =2;
			gbc_btnAdd.gridy =4;
			btnAdd.setPreferredSize(new Dimension(90, 25));
			btnAdd.addActionListener(new AddSlotDialogListener(this, page, model));
			add(btnAdd, gbc_btnAdd);
			
			JButton btnCancel = new JButton("Cancel");
			GridBagConstraints gbc_btnCancel = new GridBagConstraints();
			gbc_btnCancel.insets = new Insets(5, 5, 5, 5);
			gbc_btnCancel.gridx =2;
			gbc_btnCancel.gridy =5;
			btnCancel.setPreferredSize(new Dimension(90, 25));
			btnCancel.addActionListener(new DialogCloseListener(this));
			add(btnCancel, gbc_btnCancel);
		
	}
	public AddSlotDialog(int index, Page p){
			setModal(true);
			setTitle("GeRuDok - Edit Slot");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.3; //Procenat ekrana
			height=height*0.3; 
			setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			setIconImage(img.getImage());
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{120,170, 90};
			gbl_dial.rowHeights = new int[]{10, 30, 30, 30, 30, 30};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			setLayout(gbl_dial);

			JLabel lbWidth = new JLabel("Slot width:");
			GridBagConstraints gbc_lbWidth = new GridBagConstraints();
			gbc_lbWidth.insets = new Insets(5, 5, 5, 5);
			gbc_lbWidth.gridx =0;
			gbc_lbWidth.gridy =2;
			add(lbWidth, gbc_lbWidth);
			
			tfWidth = new JTextField();
			tfWidth.setText(String.valueOf(p.getSlots().get(index).getWidth()));
			GridBagConstraints gbc_tfWidth = new GridBagConstraints();
			gbc_tfWidth.insets = new Insets(5, 5, 5, 5);
			gbc_tfWidth.gridx =1;
			gbc_tfWidth.gridy =2;
			tfWidth.setPreferredSize(new Dimension(150, 25));
			add(tfWidth, gbc_tfWidth);
			

			JLabel lbHeight = new JLabel("Slot height:");
			GridBagConstraints gbc_lbHeight = new GridBagConstraints();
			gbc_lbHeight.insets = new Insets(5, 5, 5, 5);
			gbc_lbHeight.gridx =0;
			gbc_lbHeight.gridy =3;
			add(lbHeight, gbc_lbHeight);
			
			tfHeight = new JTextField(String.valueOf(p.getSlots().get(index).getHeight()));
			GridBagConstraints gbc_tfHeight = new GridBagConstraints();
			gbc_tfHeight.insets = new Insets(5, 5, 5, 5);
			gbc_tfHeight.gridx =1;
			gbc_tfHeight.gridy =3;
			tfHeight.setPreferredSize(new Dimension(150, 25));
			add(tfHeight, gbc_tfHeight);
			
			JButton btnAdd = new JButton("Done");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
			gbc_btnAdd.gridx =2;
			gbc_btnAdd.gridy =4;
			btnAdd.setPreferredSize(new Dimension(90, 25));
			btnAdd.addActionListener(new EditSlotDialogListener(this, p, index));
			add(btnAdd, gbc_btnAdd);
			
			JButton btnCancel = new JButton("Cancel");
			GridBagConstraints gbc_btnCancel = new GridBagConstraints();
			gbc_btnCancel.insets = new Insets(5, 5, 5, 5);
			gbc_btnCancel.gridx =2;
			gbc_btnCancel.gridy =5;
			btnCancel.setPreferredSize(new Dimension(90, 25));
			btnCancel.addActionListener(new DialogCloseListener(this));
			add(btnCancel, gbc_btnCancel);
		
	}
	public AddSlotDialog(int index, Page p, WorkspaceModel model){
		setModal(true);
		setTitle("GeRuDok - Edit Slot");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.3; //Procenat ekrana
		height=height*0.3; 
		setBounds(150, 150, (int)width, (int)height);
		String myLoc = (System.getProperty("user.dir")+"/src/");;
		ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
		setIconImage(img.getImage());
		
		GridBagLayout gbl_dial = new GridBagLayout();
		gbl_dial.columnWidths = new int[]{120,170, 90};
		gbl_dial.rowHeights = new int[]{10, 30, 30, 30, 30, 30};
		gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_dial);

		JLabel lbWidth = new JLabel("Slot width (px):");
		GridBagConstraints gbc_lbWidth = new GridBagConstraints();
		gbc_lbWidth.insets = new Insets(5, 5, 5, 5);
		gbc_lbWidth.gridx =0;
		gbc_lbWidth.gridy =2;
		add(lbWidth, gbc_lbWidth);
		
		tfWidth = new JTextField();
		tfWidth.setText(String.valueOf(p.getSlots().get(index).getWidth()));
		GridBagConstraints gbc_tfWidth = new GridBagConstraints();
		gbc_tfWidth.insets = new Insets(5, 5, 5, 5);
		gbc_tfWidth.gridx =1;
		gbc_tfWidth.gridy =2;
		tfWidth.setPreferredSize(new Dimension(150, 25));
		add(tfWidth, gbc_tfWidth);
		

		JLabel lbHeight = new JLabel("Slot height (px):");
		GridBagConstraints gbc_lbHeight = new GridBagConstraints();
		gbc_lbHeight.insets = new Insets(5, 5, 5, 5);
		gbc_lbHeight.gridx =0;
		gbc_lbHeight.gridy =3;
		add(lbHeight, gbc_lbHeight);
		
		tfHeight = new JTextField(String.valueOf(p.getSlots().get(index).getHeight()));
		GridBagConstraints gbc_tfHeight = new GridBagConstraints();
		gbc_tfHeight.insets = new Insets(5, 5, 5, 5);
		gbc_tfHeight.gridx =1;
		gbc_tfHeight.gridy =3;
		tfHeight.setPreferredSize(new Dimension(150, 25));
		add(tfHeight, gbc_tfHeight);
		
		JButton btnAdd = new JButton("Done");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
		gbc_btnAdd.gridx =2;
		gbc_btnAdd.gridy =4;
		btnAdd.setPreferredSize(new Dimension(90, 25));
		btnAdd.addActionListener(new EditSlotDialogListener(this, p, index, model));
		add(btnAdd, gbc_btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(5, 5, 5, 5);
		gbc_btnCancel.gridx =2;
		gbc_btnCancel.gridy =5;
		btnCancel.setPreferredSize(new Dimension(90, 25));
		btnCancel.addActionListener(new DialogCloseListener(this));
		add(btnCancel, gbc_btnCancel);
	
}
}
