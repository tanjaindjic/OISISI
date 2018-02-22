package listeners;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;
import view.WorkArea;

public class ExitFullscreenListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
			WorkArea.getInstance().removeTabAt(0);	
		
		MainWindow mw = MainWindow.getInstance();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		double width =  screenSize.getWidth(); 
		double height =  screenSize.getHeight();
		width=width*0.8; //Procenat ekrana
		height=height*0.75; 
		mw.setBounds(100, 100, (int)width, (int)height);
		mw.getScrollPane().setVisible(true);
		mw.getToolBar().setVisible(true);
		MainWindow.getInstance().tabbedPane.setVisible(true);
	}

}
