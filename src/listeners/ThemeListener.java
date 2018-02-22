package listeners;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainWindow;

public class ThemeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//biranje teme u menijju
		String selected = e.getActionCommand();
		if(selected.equals("Metalic")){
			
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());
			
			MainWindow.getInstance().pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.8; //Procenat ekrana
			height=height*0.75; 
			MainWindow.getInstance().setBounds(100, 100, (int)width, (int)height);
			MainWindow.getInstance().repaint();
			MainWindow.getInstance().revalidate();
		}else{
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());
			
			MainWindow.getInstance().pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.8; //Procenat ekrana
			height=height*0.75; 
			MainWindow.getInstance().setBounds(100, 100, (int)width, (int)height);
			MainWindow.getInstance().repaint();
			MainWindow.getInstance().revalidate();
		}
	}

}
