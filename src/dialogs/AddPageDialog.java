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

import listenersTree.AddPageDialogListener;
import listenersTree.SaveEditedPageListener;
import model.GeRuDokument;
import model.Page;
import view.Tree;

@SuppressWarnings("serial")
public class AddPageDialog extends JDialog {
	private GeRuDokument grd; 
	private DefaultTreeModel model;
	public static JTextField tfName;
	public static JTextField tfWidth;
	public static JTextField tfHeight;
	private String nameP;
	private double widthP;
	private Tree t;
	private double heightP;
		
	public String getNameP() {
		return nameP;
	}

	public void setNameP(String name) {
		this.nameP = name;
	}

	public double getWidthP() {
		return widthP;
	}

	public void setWidthP(double width) {
		this.widthP = width;
	}

	public double getHeightP() {
		return heightP;
	}

	public void setHeightP(double height) {
		this.heightP = height;
	}
	
	public AddPageDialog(GeRuDokument grd, DefaultTreeModel model, Tree t){
		this.grd=grd;
		this.t=t;
		this.model =model;
			setModal(true);
			setTitle("GeRuDok - Add Page");
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
			
			JLabel lbName = new JLabel("Page name:");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =0;
			gbc_lbName.gridy =1;
			add(lbName, gbc_lbName);
			
			tfName = new JTextField("");
			GridBagConstraints gbc_tfName = new GridBagConstraints();
			gbc_tfName.insets = new Insets(5, 5, 5, 5);
			gbc_tfName.gridx =1;
			gbc_tfName.gridy =1;
			tfName.setPreferredSize(new Dimension(150, 25));
			add(tfName, gbc_tfName);

			JLabel lbWidth = new JLabel("Page width (px):");
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
			

			JLabel lbHeight = new JLabel("Page height (px):");
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
			
			JButton btnAdd = new JButton("Add page");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
			gbc_btnAdd.gridx =2;
			gbc_btnAdd.gridy =4;
			btnAdd.setPreferredSize(new Dimension(90, 25));
			btnAdd.addActionListener(new AddPageDialogListener(this, grd, model,t));
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
	public AddPageDialog(DefaultTreeModel model, Page p){
		this.grd=grd;
		this.model =model;
			setModal(true);
			setTitle("GeRuDok - Edit Page");
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
			
			JLabel lbName = new JLabel("Page name:");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =0;
			gbc_lbName.gridy =1;
			add(lbName, gbc_lbName);
			
			tfName = new JTextField(p.getName());
			GridBagConstraints gbc_tfName = new GridBagConstraints();
			gbc_tfName.insets = new Insets(5, 5, 5, 5);
			gbc_tfName.gridx =1;
			gbc_tfName.gridy =1;
			tfName.setPreferredSize(new Dimension(150, 25));
			add(tfName, gbc_tfName);

			JLabel lbWidth = new JLabel("Page width:");
			GridBagConstraints gbc_lbWidth = new GridBagConstraints();
			gbc_lbWidth.insets = new Insets(5, 5, 5, 5);
			gbc_lbWidth.gridx =0;
			gbc_lbWidth.gridy =2;
			add(lbWidth, gbc_lbWidth);
			
			tfWidth = new JTextField(String.valueOf(p.getWidth()));
			GridBagConstraints gbc_tfWidth = new GridBagConstraints();
			gbc_tfWidth.insets = new Insets(5, 5, 5, 5);
			gbc_tfWidth.gridx =1;
			gbc_tfWidth.gridy =2;
			tfWidth.setPreferredSize(new Dimension(150, 25));
			add(tfWidth, gbc_tfWidth);
			

			JLabel lbHeight = new JLabel("Page height:");
			GridBagConstraints gbc_lbHeight = new GridBagConstraints();
			gbc_lbHeight.insets = new Insets(5, 5, 5, 5);
			gbc_lbHeight.gridx =0;
			gbc_lbHeight.gridy =3;
			add(lbHeight, gbc_lbHeight);
			
			tfHeight = new JTextField(String.valueOf(p.getHeight()));
			GridBagConstraints gbc_tfHeight = new GridBagConstraints();
			gbc_tfHeight.insets = new Insets(5, 5, 5, 5);
			gbc_tfHeight.gridx =1;
			gbc_tfHeight.gridy =3;
			tfHeight.setPreferredSize(new Dimension(150, 25));
			add(tfHeight, gbc_tfHeight);
			
			JButton btnAdd = new JButton("Save");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
			gbc_btnAdd.gridx =2;
			gbc_btnAdd.gridy =4;
			btnAdd.setPreferredSize(new Dimension(90, 25));
			btnAdd.addActionListener(new SaveEditedPageListener(this, model, p));
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
