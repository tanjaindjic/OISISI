package dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;

import model.GeRuDokument;

public class ChooseDefaultPath extends JDialog {
	private GeRuDokument grd; 
	private DefaultTreeModel model;
	public static JTextField tfName;
	private String pathP;
	public static JCheckBox cb;
	private static ChooseDefaultPath cdf = null;
	
	public String pathP() {
		return pathP;
	}

	public void setPathP(String name) {
		this.pathP = name;
	}
	private ChooseDefaultPath(){
	}
	
	public static ChooseDefaultPath getInstance(){
		if(cdf == null){
			cdf = new ChooseDefaultPath();
			cdf.setModal(true);
			cdf.setTitle("GeRuDok - Choose default location");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.4; //Procenat ekrana
			height=height*0.3; 
			cdf.setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			cdf.setIconImage(img.getImage());
			cdf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{10,150,80};
			gbl_dial.rowHeights = new int[]{10, 30, 30, 10, 30};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			cdf.setLayout(gbl_dial);
			
			JLabel lbName = new JLabel("Please choose the default location for GeRuDok.						                                       ");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =1;
			gbc_lbName.gridy =1;
			cdf.add(lbName, gbc_lbName);
						
			
			tfName = new JTextField();
			GridBagConstraints gbc_tfName = new GridBagConstraints();
			gbc_tfName.insets = new Insets(5, 5, 5, 5);
			gbc_tfName.gridx =1;
			gbc_tfName.gridy =2;
			tfName.setPreferredSize(new Dimension(400, 25));
			cdf.add(tfName, gbc_tfName);
//samo sam izmenila malo b na dugmetu nista vise
			JButton browseBtn = new JButton("Browse");
			GridBagConstraints gbc_browseBtn = new GridBagConstraints();
			gbc_browseBtn.insets = new Insets(5, 5, 5, 5);
			gbc_browseBtn.gridx =2;
			gbc_browseBtn.gridy =2;
			browseBtn.addActionListener(new OpenFileBrowserListener(tfName));
			cdf.add(browseBtn, gbc_browseBtn);
			
			JButton doneBtn = new JButton(" Done ");
			GridBagConstraints gbc_doneBtn = new GridBagConstraints();
			gbc_doneBtn.insets = new Insets(5, 5, 5, 5);
			gbc_doneBtn.gridx =2;
			gbc_doneBtn.gridy =3;
			doneBtn.addActionListener(new DoneDefaultLocationListener());
			cdf.add(doneBtn, gbc_doneBtn);
			
			cb = new JCheckBox();
			GridBagConstraints gbc_cb = new GridBagConstraints();
			gbc_cb.insets = new Insets(5, 5, 5, 5);
			gbc_cb.gridx =0;
			gbc_cb.gridy =4;
			cdf.add(cb, gbc_cb);

			JLabel lbHeight = new JLabel("Remember and do not show this again.                                                 ");
			GridBagConstraints gbc_lbHeight = new GridBagConstraints();
			gbc_lbHeight.insets = new Insets(5, 5, 5, 5);
			gbc_lbHeight.gridx =1;
			gbc_lbHeight.gridy =4;
			cdf.add(lbHeight, gbc_lbHeight);
			
			
		}
		return cdf;
		
	}
}
