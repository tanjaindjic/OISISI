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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.GeRuDokModel;
import model.GeRuDokument;
import model.Page;
import model.Project;
import model.Workspace;

@SuppressWarnings("serial")
public class ShowPropertiesNodeDialog extends JDialog{
	public ShowPropertiesNodeDialog(Object obj){
		
		setModal(true);
		setTitle("GeRuDok - Properties");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.40; 
		setBounds(150, 150, (int)width, (int)height);
		String myLoc = (System.getProperty("user.dir")+"/src/");;
		ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
		setIconImage(img.getImage());
		
		JPanel pan = new JPanel();
		
		GridBagLayout gbl_dial = new GridBagLayout();
		gbl_dial.columnWidths = new int[]{70, 200};
		gbl_dial.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30};
		gbl_dial.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dial.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		pan.setLayout(gbl_dial);
		pan.setPreferredSize(new Dimension((int)width, (int)height));		
		
		if(obj instanceof Workspace){
			Workspace objShow = (Workspace) obj; 
			
			JLabel lb1 = new JLabel("Type:");
			GridBagConstraints gbc_lb1 = new GridBagConstraints();
			gbc_lb1.insets = new Insets(5, 5, 5, 5);
			gbc_lb1.gridx =0;
			gbc_lb1.gridy =0;
			pan.add(lb1, gbc_lb1);
			
			JLabel lb11 = new JLabel("Workspace");
			GridBagConstraints gbc_lb11 = new GridBagConstraints();
			gbc_lb11.insets = new Insets(5, 5, 5, 5);
			gbc_lb11.gridx =1;
			gbc_lb11.gridy =0;
			pan.add(lb11, gbc_lb11);
			
			JLabel lb2 = new JLabel("Name:");
			GridBagConstraints gbc_lb2 = new GridBagConstraints();
			gbc_lb2.insets = new Insets(5, 5, 5, 5);
			gbc_lb2.gridx =0;
			gbc_lb2.gridy =1;
			pan.add(lb2, gbc_lb2);
			
			JLabel lb21 = new JLabel(objShow.getName());
			GridBagConstraints gbc_lb21 = new GridBagConstraints();
			gbc_lb21.insets = new Insets(5, 5, 5, 5);
			gbc_lb21.gridx =1;
			gbc_lb21.gridy =1;
			pan.add(lb21, gbc_lb21);
			
			JLabel lb3 = new JLabel("Path:");
			GridBagConstraints gbc_lb3 = new GridBagConstraints();
			gbc_lb3.insets = new Insets(5, 5, 5, 5);
			gbc_lb3.gridx =0;
			gbc_lb3.gridy =2;
			pan.add(lb3, gbc_lb3);
			
			JLabel lb31 = new JLabel(objShow.getPath());
			GridBagConstraints gbc_lb31 = new GridBagConstraints();
			gbc_lb31.insets = new Insets(5, 5, 5, 5);
			gbc_lb31.gridx =1;
			gbc_lb31.gridy =2;
			pan.add(lb31, gbc_lb31);
			
			JLabel lb4 = new JLabel("It's Projects:");
			GridBagConstraints gbc_lb4 = new GridBagConstraints();
			gbc_lb4.insets = new Insets(5, 5, 5, 5);
			gbc_lb4.gridx =0;
			gbc_lb4.gridy =3;
			pan.add(lb4, gbc_lb4);
			int  i=0;
			for(i=0; i<objShow.getProjects().size(); i++){
				JLabel lb41 = new JLabel(objShow.getProjects().get(i).toString());
				GridBagConstraints gbc_lb41 = new GridBagConstraints();
				gbc_lb41.insets = new Insets(5, 5, 5, 5);
				gbc_lb41.gridx =1;
				gbc_lb41.gridy =3+i;
				pan.add(lb41, gbc_lb41);
			}
			JButton ok = new JButton("OK");
			ok.addActionListener(new DialogCloseListener(this));
			ok.setPreferredSize(new Dimension(70, 30));
			GridBagConstraints gbc_ok = new GridBagConstraints();
			gbc_ok.insets = new Insets(5, 5, 5, 5);
			gbc_ok.gridx =1;
			gbc_ok.gridy =i+4;
			pan.add(ok, gbc_ok);
			JScrollPane sp = new JScrollPane(pan);
			add(sp);
			return;
		}
		if(obj instanceof Project){
			Project objShow = (Project) obj; 
			
			JLabel lb1 = new JLabel("Type:");
			GridBagConstraints gbc_lb1 = new GridBagConstraints();
			gbc_lb1.insets = new Insets(5, 5, 5, 5);
			gbc_lb1.gridx =0;
			gbc_lb1.gridy =0;
			pan.add(lb1, gbc_lb1);
			
			JLabel lb11 = new JLabel("Project");
			GridBagConstraints gbc_lb11 = new GridBagConstraints();
			gbc_lb11.insets = new Insets(5, 5, 5, 5);
			gbc_lb11.gridx =1;
			gbc_lb11.gridy =0;
			pan.add(lb11, gbc_lb11);
			
			JLabel lb2 = new JLabel("Name:");
			GridBagConstraints gbc_lb2 = new GridBagConstraints();
			gbc_lb2.insets = new Insets(5, 5, 5, 5);
			gbc_lb2.gridx =0;
			gbc_lb2.gridy =1;
			pan.add(lb2, gbc_lb2);
			
			JLabel lb21 = new JLabel(objShow.getName());
			GridBagConstraints gbc_lb21 = new GridBagConstraints();
			gbc_lb21.insets = new Insets(5, 5, 5, 5);
			gbc_lb21.gridx =1;
			gbc_lb21.gridy =1;
			pan.add(lb21, gbc_lb21);
			
			JLabel lb3 = new JLabel("Path:");
			GridBagConstraints gbc_lb3 = new GridBagConstraints();
			gbc_lb3.insets = new Insets(5, 5, 5, 5);
			gbc_lb3.gridx =0;
			gbc_lb3.gridy =2;
			pan.add(lb3, gbc_lb3);
			
			JLabel lb31 = new JLabel(objShow.getPath());
			GridBagConstraints gbc_lb31 = new GridBagConstraints();
			gbc_lb31.insets = new Insets(5, 5, 5, 5);
			gbc_lb31.gridx =1;
			gbc_lb31.gridy =2;
			pan.add(lb31, gbc_lb31);
			
			JLabel lb4 = new JLabel("It's GeRuDocuments:");
			GridBagConstraints gbc_lb4 = new GridBagConstraints();
			gbc_lb4.insets = new Insets(5, 5, 5, 5);
			gbc_lb4.gridx =0;
			gbc_lb4.gridy =3;
			pan.add(lb4, gbc_lb4);
			
			int i=0;
			for(i=0; i<objShow.getGerudokuments().size(); i++){
				JLabel lb41 = new JLabel(objShow.getGerudokuments().get(i).toString());
				GridBagConstraints gbc_lb41 = new GridBagConstraints();
				gbc_lb41.insets = new Insets(5, 5, 5, 5);
				gbc_lb41.gridx =1;
				gbc_lb41.gridy =3+i;
				pan.add(lb41, gbc_lb41);
			}
			JButton ok = new JButton("OK");
			ok.addActionListener(new DialogCloseListener(this));
			ok.setPreferredSize(new Dimension(70, 30));
			GridBagConstraints gbc_ok = new GridBagConstraints();
			gbc_ok.insets = new Insets(5, 5, 5, 5);
			gbc_ok.gridx =1;
			gbc_ok.gridy =i+4;
			pan.add(ok, gbc_ok);
			JScrollPane sp = new JScrollPane(pan);
			add(sp);
			return;
		}
		if(obj instanceof GeRuDokument){
			GeRuDokument objShow = (GeRuDokument) obj; 
			
			JLabel lb1 = new JLabel("Type:");
			GridBagConstraints gbc_lb1 = new GridBagConstraints();
			gbc_lb1.insets = new Insets(5, 5, 5, 5);
			gbc_lb1.gridx =0;
			gbc_lb1.gridy =0;
			pan.add(lb1, gbc_lb1);
			
			JLabel lb11 = new JLabel("GeRuDokument");
			GridBagConstraints gbc_lb11 = new GridBagConstraints();
			gbc_lb11.insets = new Insets(5, 5, 5, 5);
			gbc_lb11.gridx =1;
			gbc_lb11.gridy =0;
			pan.add(lb11, gbc_lb11);
			
			JLabel lb2 = new JLabel("Name:");
			GridBagConstraints gbc_lb2 = new GridBagConstraints();
			gbc_lb2.insets = new Insets(5, 5, 5, 5);
			gbc_lb2.gridx =0;
			gbc_lb2.gridy =1;
			pan.add(lb2, gbc_lb2);
			
			JLabel lb21 = new JLabel(objShow.getName());
			GridBagConstraints gbc_lb21 = new GridBagConstraints();
			gbc_lb21.insets = new Insets(5, 5, 5, 5);
			gbc_lb21.gridx =1;
			gbc_lb21.gridy =1;
			pan.add(lb21, gbc_lb21);
			
			JLabel lb3 = new JLabel("Path:");
			GridBagConstraints gbc_lb3 = new GridBagConstraints();
			gbc_lb3.insets = new Insets(5, 5, 5, 5);
			gbc_lb3.gridx =0;
			gbc_lb3.gridy =2;
			pan.add(lb3, gbc_lb3);
			
			JLabel lb31 = new JLabel(objShow.getPath());
			GridBagConstraints gbc_lb31 = new GridBagConstraints();
			gbc_lb31.insets = new Insets(5, 5, 5, 5);
			gbc_lb31.gridx =1;
			gbc_lb31.gridy =2;
			pan.add(lb31, gbc_lb31);
			
			JLabel lb4 = new JLabel("It's Pages:");
			GridBagConstraints gbc_lb4 = new GridBagConstraints();
			gbc_lb4.insets = new Insets(5, 5, 5, 5);
			gbc_lb4.gridx =0;
			gbc_lb4.gridy =3;
			pan.add(lb4, gbc_lb4);
			
			int i=0;
			for(i=0; i<objShow.getPages().size(); i++){
				JLabel lb41 = new JLabel(objShow.getPages().get(i).toString());
				GridBagConstraints gbc_lb41 = new GridBagConstraints();
				gbc_lb41.insets = new Insets(5, 5, 5, 5);
				gbc_lb41.gridx =1;
				gbc_lb41.gridy =3+i;
				pan.add(lb41, gbc_lb41);
			}
			
			
			
			JButton ok = new JButton("OK");
			ok.addActionListener(new DialogCloseListener(this));
			ok.setPreferredSize(new Dimension(70, 30));
			GridBagConstraints gbc_ok = new GridBagConstraints();
			gbc_ok.insets = new Insets(5, 5, 5, 5);
			gbc_ok.gridx =1;
			gbc_ok.gridy =i+5;
			pan.add(ok, gbc_ok);
			JScrollPane sp = new JScrollPane(pan);
			add(sp);
			return;
		}
		
		if(obj instanceof Page){
			Page objShow = (Page) obj; 
			
			JLabel lb1 = new JLabel("Type:");
			GridBagConstraints gbc_lb1 = new GridBagConstraints();
			gbc_lb1.insets = new Insets(5, 5, 5, 5);
			gbc_lb1.gridx =0;
			gbc_lb1.gridy =0;
			pan.add(lb1, gbc_lb1);
			
			JLabel lb11 = new JLabel("Page");
			GridBagConstraints gbc_lb11 = new GridBagConstraints();
			gbc_lb11.insets = new Insets(5, 5, 5, 5);
			gbc_lb11.gridx =1;
			gbc_lb11.gridy =0;
			pan.add(lb11, gbc_lb11);
			
			JLabel lb2 = new JLabel("Name:");
			GridBagConstraints gbc_lb2 = new GridBagConstraints();
			gbc_lb2.insets = new Insets(5, 5, 5, 5);
			gbc_lb2.gridx =0;
			gbc_lb2.gridy =1;
			pan.add(lb2, gbc_lb2);
			
			JLabel lb21 = new JLabel(objShow.getName());
			GridBagConstraints gbc_lb21 = new GridBagConstraints();
			gbc_lb21.insets = new Insets(5, 5, 5, 5);
			gbc_lb21.gridx =1;
			gbc_lb21.gridy =1;
			pan.add(lb21, gbc_lb21);
			
			JLabel lb3 = new JLabel("Path:");
			GridBagConstraints gbc_lb3 = new GridBagConstraints();
			gbc_lb3.insets = new Insets(5, 5, 5, 5);
			gbc_lb3.gridx =0;
			gbc_lb3.gridy =2;
			pan.add(lb3, gbc_lb3);
			
			JLabel lb31 = new JLabel(objShow.getPath());
			GridBagConstraints gbc_lb31 = new GridBagConstraints();
			gbc_lb31.insets = new Insets(5, 5, 5, 5);
			gbc_lb31.gridx =1;
			gbc_lb31.gridy =2;
			pan.add(lb31, gbc_lb31);
			
			JLabel lb4 = new JLabel("Width:");
			GridBagConstraints gbc_lb4 = new GridBagConstraints();
			gbc_lb4.insets = new Insets(5, 5, 5, 5);
			gbc_lb4.gridx =0;
			gbc_lb4.gridy =3;
			pan.add(lb4, gbc_lb4);
			
			JLabel lb41 = new JLabel(String.valueOf(objShow.getWidth()));
			GridBagConstraints gbc_lb41 = new GridBagConstraints();
			gbc_lb41.insets = new Insets(5, 5, 5, 5);
			gbc_lb41.gridx =1;
			gbc_lb41.gridy =3;
			pan.add(lb41, gbc_lb41);
			
			
			JLabel lb5 = new JLabel("Height:");
			GridBagConstraints gbc_lb5 = new GridBagConstraints();
			gbc_lb5.insets = new Insets(5, 5, 5, 5);
			gbc_lb5.gridx =0;
			gbc_lb5.gridy =4;
			pan.add(lb5, gbc_lb5);
			
			
			JLabel lb51 = new JLabel(String.valueOf(objShow.getHeight()));
			GridBagConstraints gbc_lb51 = new GridBagConstraints();
			gbc_lb51.insets = new Insets(5, 5, 5, 5);
			gbc_lb51.gridx =1;
			gbc_lb51.gridy =4;
			pan.add(lb51, gbc_lb51);
			
			
			JButton ok = new JButton("OK");
			ok.addActionListener(new DialogCloseListener(this));
			ok.setPreferredSize(new Dimension(70, 30));
			GridBagConstraints gbc_ok = new GridBagConstraints();
			gbc_ok.insets = new Insets(5, 5, 5, 5);
			gbc_ok.gridx =1;
			gbc_ok.gridy =5;
			pan.add(ok, gbc_ok);
			JScrollPane sp = new JScrollPane(pan);
			add(sp);
			return;
		}
		
	}
}
