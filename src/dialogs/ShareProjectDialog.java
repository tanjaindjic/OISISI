package dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import listenersTree.ShareProjectListener;
import model.GeRuDokModel;
import model.JsonModel;
import model.Project;
import model.ShareTypes;
import model.Workspace;
import view.Tree;

public class ShareProjectDialog extends JDialog {
	public static JTextField workspace;
	public static JComboBox typeList;
	private Project p;
	private Tree t;

	
	
	
	public ShareProjectDialog(Tree t){
			this.p= (Project) t.getSelected();			
			setTitle("GeRuDok - Share Project");
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
			
			workspace = new JTextField("");
			GridBagConstraints gbc_workspace = new GridBagConstraints();
			gbc_workspace.insets = new Insets(5, 5, 5, 5);
			gbc_workspace.gridx =1;
			gbc_workspace.gridy =1;
			workspace.setPreferredSize(new Dimension(250, 25));
			add(workspace, gbc_workspace);
			
			JButton browseBtn = new JButton("Browse");
			GridBagConstraints gbc_browseBtn = new GridBagConstraints();
			gbc_browseBtn.insets = new Insets(5, 5, 5, 5);
			gbc_browseBtn.gridx =2;
			gbc_browseBtn.gridy =1;
			browseBtn.addActionListener(new OpenFileBrowserListener(workspace, 0));
			add(browseBtn, gbc_browseBtn);

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
			btnAdd.addActionListener(new ShareProjectListener(this));
			
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
		
		if(workspace != null && !workspace.getText().trim().isEmpty()){
			
			if(typeList.getSelectedIndex() !=-1){
				
				ShareTypes s = stringToEnum();
				if(s == ShareTypes.COPY){
					Project proj = new Project();
					proj.setUniqueID(UUID.randomUUID());
					proj.setName(p.getName());
					proj.setGerudokuments(p.getGerudokuments());
					proj.setOwners(p.getOwners());
					
					JsonModel jm = new JsonModel();
					
					Workspace ws=jm.loadWorkspaceFromJSon(workspace.getText());
					proj.addOwner(ws, s);
					jm.shareProject(workspace.getText(),proj, s);
					JOptionPane.showMessageDialog(null, " Shared !");
					this.dispose();
					return;
					
				}
				
				JsonModel jm = new JsonModel();
				Workspace ws=jm.loadWorkspaceFromJSon(workspace.getText());
				
				Project test=null;
				
				for( Project pr : GeRuDokModel.getInstance().getActiveWorkspace().getProjects()){
					if(pr.getUniqueID().toString().equals(p.getUniqueID().toString())){
						 pr.addOwner(ws, s);
						 test=pr;
					}
					
				}
				
				//Project pr = (Project) t.getSelected();
				//pr.addOwner(ws, s);
				p.addOwner(ws, s);
				if( test!= null){
					jm.shareProject(workspace.getText(),test, s);
				}else{
					jm.shareProject(workspace.getText(),p, s);}
					JOptionPane.showMessageDialog(null, " Shared !");
				this.dispose();
				return;
				
			}else{
				JOptionPane.showMessageDialog(null, "Invalid type, project was not shared !");
			}
			
		}
		JOptionPane.showMessageDialog(null, "Invalid workspace, project was not shared !");
		this.dispose();
		
		
	}
	
	public void addOwn(Workspace ws){
		
		
	}
}
