package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.command.CommandManager;
import model.Clipboard;
import model.GeRuDokModel;
import model.JsonModel;
import view.MainWindow;

public class SaveAsListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file location to save");   
		FileNameExtensionFilter Filter = new FileNameExtensionFilter("work", "WORK");
		if(GeRuDokModel.getInstance().getActiveWorkspace()==null){
			JOptionPane.showMessageDialog(null, "There is no Workspace to save!");
		}else{
			String workspace = GeRuDokModel.getInstance().getActiveWorkspace().getName();
			fileChooser.setSelectedFile(new File(workspace + "(1)"));
			fileChooser.addChoosableFileFilter(Filter);
			fileChooser.setFileFilter(Filter);
			 
			int userSelection = fileChooser.showSaveDialog(MainWindow.getInstance());
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				JsonModel jm = new JsonModel();
				String path = fileChooser.getSelectedFile().getAbsolutePath();

				jm.saveModel(GeRuDokModel.getInstance().getActiveWorkspace(),path);
				CommandManager.getCMD().deleteAll();
				Clipboard.getInstance().delete();
			}
		}
	}

}
