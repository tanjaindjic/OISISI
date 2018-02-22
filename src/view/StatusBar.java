package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatusBar extends JPanel{
	private static StatusBar statusBar = null;
	private static String status;
	static JLabel statusLabel;
	private StatusBar(){
		
	}
	public static StatusBar getInstance(){
		if(statusBar==null){
			statusBar = new StatusBar();
			statusLabel = new JLabel(status);
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			statusBar.add(statusLabel);
		}
		return statusBar;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
		statusLabel.setText(status);
	}
}
