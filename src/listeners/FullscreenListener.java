package listeners;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;
import view.WorkArea;

public class FullscreenListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))			
			WorkArea.getInstance().removeTabAt(0);	
		
		MainWindow mw = MainWindow.getInstance();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		double width =  screenSize.getWidth(); 
		double height =  screenSize.getHeight();
		mw.setBounds(100, 100, (int)width, (int)height);
		GraphicsEnvironment env =
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		mw.setMaximizedBounds(env.getMaximumWindowBounds());
		mw.setExtendedState(mw.getExtendedState() | mw.MAXIMIZED_BOTH);
		mw.getScrollPane().setVisible(false);
		mw.getToolBar().setVisible(false);
		MainWindow.getInstance().tabbedPane.setVisible(true);
	}

}
