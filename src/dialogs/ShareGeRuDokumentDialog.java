package dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import listenersTree.ShareGeRuDokumentListener;
import model.GeRuDokModel;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.ShareTypes;
import view.Tree;

public class ShareGeRuDokumentDialog extends JDialog {
	public static JComboBox project;
	public static JTextField tfWidth;
	public static JTextField tfHeight;
	public static JComboBox typeList;
	private GeRuDokument grd;
	private Tree t;
	
	public ShareGeRuDokumentDialog(GeRuDokument grdok, Tree t){
			this.grd=grdok;
			this.t=t;
			
			setTitle("GeRuDok - Share GeRuDocument");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.4; //Procenat ekrana
			height=height*0.3; 
			setBounds(150, 150, (int)width, (int)height);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			setIconImage(img.getImage());
			
			GridBagLayout gbl_dial = new GridBagLayout();
			gbl_dial.columnWidths = new int[]{120,250, 90};
			gbl_dial.rowHeights = new int[]{10, 30, 30, 10, 30, 30};
			gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			setLayout(gbl_dial);
			
			JLabel lbName = new JLabel("Shared with:");
			GridBagConstraints gbc_lbName = new GridBagConstraints();
			gbc_lbName.insets = new Insets(5, 5, 5, 5);
			gbc_lbName.gridx =0;
			gbc_lbName.gridy =1;
			add(lbName, gbc_lbName);
			
			int sizeP = GeRuDokModel.getInstance().getActiveWorkspace().getProjects().size();
			if(sizeP>1){
				String[] shareProject = new String[sizeP-1];
				int j=0;
				for(int i=0; i<sizeP; i++){
					Project p = GeRuDokModel.getInstance().getActiveWorkspace().getProjects().get(i);
					if(!p.getName().equals("freeGeRuDocuments"))
						if(!p.containsChild(grd.getUniqueID()))
							shareProject[j++] = p.getName().toString()+"                                                                                             ("+p.getUniqueID().toString()+")";
				}
				project = new JComboBox(shareProject); // ja odavde mogu vaditi samo neki string name, ali mi to nista ne znaci jer mogu biti dva gerudoka sa istim imenom u razlictim dokumentima...
				GridBagConstraints gbc_project = new GridBagConstraints();// Trebao bi mi uiid ili najbolje konkretan projekat nekako
				gbc_project.insets = new Insets(5, 5, 5, 5);
				gbc_project.gridx =1;
				gbc_project.gridy =1;
				project.setPreferredSize(new Dimension(250, 25));
				add(project, gbc_project);
			}
			else {
				String[] shareProject = new String[0];
				project = new JComboBox(shareProject); // ja odavde mogu vaditi samo neki string name, ali mi to nista ne znaci jer mogu biti dva gerudoka sa istim imenom u razlictim dokumentima...
				GridBagConstraints gbc_project = new GridBagConstraints();// Trebao bi mi uiid ili najbolje konkretan projekat nekako
				gbc_project.insets = new Insets(5, 5, 5, 5);
				gbc_project.gridx =1;
				gbc_project.gridy =1;
				project.setPreferredSize(new Dimension(250, 25));
				add(project, gbc_project);
			}
			

			JLabel lbType = new JLabel("Sharing type:");
			GridBagConstraints gbc_lbType = new GridBagConstraints();
			gbc_lbType.insets = new Insets(5, 5, 5, 5);
			gbc_lbType.gridx =0;
			gbc_lbType.gridy =2;
			add(lbType, gbc_lbType);
			
			String[] shareTypes = {"read", "read-write", "copy"};
			typeList = new JComboBox(shareTypes);
			typeList.setSelectedIndex(-1);
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(5, 5, 5, 5);
			gbc_list.gridx =1;
			gbc_list.gridy =2;
			typeList.setPreferredSize(new Dimension(250, 25));
			add(typeList, gbc_list);
			
			JButton btnAdd = new JButton("Share");
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
			gbc_btnAdd.gridx =2;
			gbc_btnAdd.gridy =4;
			btnAdd.setPreferredSize(new Dimension(90, 25));
			add(btnAdd, gbc_btnAdd);
			btnAdd.addActionListener(new ShareGeRuDokumentListener(this));
			
			JButton btnCancel = new JButton("Cancel");
			GridBagConstraints gbc_btnCancel = new GridBagConstraints();
			gbc_btnCancel.insets = new Insets(5, 5, 5, 5);
			gbc_btnCancel.gridx =2;
			gbc_btnCancel.gridy =5;
			btnCancel.setPreferredSize(new Dimension(90, 25));
			btnCancel.addActionListener(new DialogCloseListener(this));
			add(btnCancel, gbc_btnCancel);
		
	}

	private ShareTypes stringToEnum(){
		
		switch ((String)typeList.getSelectedItem()) {
		case "read":
			return ShareTypes.READ;
		case "read-write":
			return ShareTypes.READANDWRITE;
		case "copy":
			return ShareTypes.COPY;
		default:
			return ShareTypes.OWNER;
		}
		
	}
	
	public void share(){
		
		
		String projName = (String)project.getSelectedItem(); // NE MOZE PREKO INDEKSA JER SE UVEK IZBACUJE PROJEKAT U KOJEM SE TRENUTNO NALAZI P
		
		Project proj=null;
		
		for(Project p : GeRuDokModel.getInstance().getActiveWorkspace().getProjects()){
			
			if(projName.contains(p.getUniqueID().toString())){ // Veoma skrnavo jer dobijam samo prvi sa ovim imenom a ne tacno onaj koji je zeleo
				
				proj=p;
			}
		}
		
		if(proj != null && grd != null){
			ShareTypes s = stringToEnum();
			grd.addOwner(proj, s);
			
			if(s == ShareTypes.COPY){
				GeRuDokument g = new GeRuDokument();
				g.setUniqueID(UUID.randomUUID());
				g.setName(grd.getName());
				g.setPath(grd.getPath()+"copy");
				g.setOwners(grd.getOwners());
				g.setPages(new ArrayList<Page>());
				for(Page p : grd.getPages()){
					g.getPages().add(p);
				}
				
				g.addObserver(t);
				proj.getGerudokuments().add(g);
				JOptionPane.showMessageDialog(null, " Shared !");
				this.dispose();
				return;
			
				
			}else{
			
			proj.getGerudokuments().add(grd);
			
			JOptionPane.showMessageDialog(null, " Shared !");
			this.dispose();
			return;
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Invalid project, GeRuDocument was not shared !");
		}
		
		this.dispose();
		
		
	}
}
