package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GeRuDokModel;

public class OpenFileBrowserListener implements ActionListener {

	private static String path;
	private JTextField tfName;
	private boolean work = false;
	private boolean proj = false;
	public OpenFileBrowserListener(JTextField tfName){
		path="";
		this.tfName=tfName;
	}
	public OpenFileBrowserListener(JTextField tfName, int i){
		path="";
		this.tfName=tfName;
		if(i==0)
			work=true;
		if (i==1)
			proj=true;
	}
	
	
	public static String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(work){
			JFileChooser chooser = new JFileChooser();
			FileFilter filter =new FileNameExtensionFilter("work", "WORK");
			
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(filter);
			
			chooser.setAcceptAllFileFilterUsed(false);
			int response=chooser.showOpenDialog(null);
			if( response ==JFileChooser.APPROVE_OPTION){
				String pp = chooser.getSelectedFile().getAbsolutePath();
				String pp0 = GeRuDokModel.getInstance().getActiveWorkspace().getUniqueID().toString();
				if(pp.contains(pp0)){
					JOptionPane.showMessageDialog(null, "Choosen Workspace contains the project. Choose another.");
					return;
				}
				setPath(chooser.getSelectedFile().getAbsolutePath());
				tfName.setText(path);
			}
			return;
		}
		if(proj){
			JFileChooser chooser = new JFileChooser();
			FileFilter filter =new FileNameExtensionFilter("proj", "PROJ");
			
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			chooser.setAcceptAllFileFilterUsed(false);
			int response=chooser.showOpenDialog(null);
			if( response ==JFileChooser.APPROVE_OPTION){
				setPath(chooser.getSelectedFile().getAbsolutePath());
				tfName.setText(path);
			}
			return;
		}
		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		int response=chooser.showOpenDialog(null);
		if( response ==JFileChooser.APPROVE_OPTION){
			setPath(chooser.getSelectedFile().getAbsolutePath());
			tfName.setText(path);
		}
	}
	
	
}
