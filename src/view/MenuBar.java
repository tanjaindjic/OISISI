package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import controller.HelpAction;
import listeners.HelpListener;
import model.WorkspaceModel;
public class MenuBar extends JMenuBar {
	
	private static MenuBar menuBar= null;
	private Tree tree;
	private  MenuBar(Tree t){
		this.tree = t;
	}
	public static MenuBar getInstance(WorkspaceModel model, Tree tree){
		if(menuBar==null){
			menuBar = new MenuBar(tree);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.8; //Procenat ekrana
			height=height*0.75; 
			
			menuBar.setSize((int)width, (int)(height*0.8));
			
			ArrayList<MenuItem> FileMenuItems = new ArrayList<MenuItem>();
			MenuItem m0i = new MenuItem("New Workspace", model,tree);
			MenuItem m0i0 = new MenuItem("New Project", model,tree);
			m0i0.setMnemonic('n');
	        
			MenuItem m0i1 = new MenuItem("Open Workspace", model,tree);
			m0i1.setMnemonic('o');
			MenuItem m0i2 = new MenuItem("Close Project", model,tree);
			MenuItem m0i3 = new MenuItem("Close Workspace", model,tree);
			MenuItem m0i4 = new MenuItem("Save", model,tree);
			m0i4.setMnemonic('s');
			MenuItem m0i5 = new MenuItem("Save As", model,tree);
			
			MenuItem m0i8 = new MenuItem("Properties", model,tree);
			m0i8.setMnemonic('p');
			MenuItem m0i9 = new MenuItem("Exit", model,tree);
			m0i9.setMnemonic('q');
			FileMenuItems.add(m0i); 
			FileMenuItems.add(m0i0); 
			FileMenuItems.add(m0i1);
			FileMenuItems.add(m0i2);
			FileMenuItems.add(m0i3);
			FileMenuItems.add(m0i4);
			FileMenuItems.add(m0i5);		
			FileMenuItems.add(m0i8);
			FileMenuItems.add(m0i9);
			
			//fajl meni
			JMenu FileMenu = new Menu(FileMenuItems, "File", model,tree);
			FileMenu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(FileMenu);
			
			ArrayList<MenuItem> EditMenuItems = new ArrayList<MenuItem>();		
			MenuItem m1i0 = new MenuItem("Undo", model,tree);	
			m1i0.setMnemonic('z');
			MenuItem m1i1 = new MenuItem("Redo", model,tree);
			m1i1.setMnemonic('y');
			MenuItem m1i2 = new MenuItem("Cut", model,tree);
			m1i2.setMnemonic('x');
			MenuItem m1i3 = new MenuItem("Copy", model,tree);
			m1i3.setMnemonic('c');
			MenuItem m1i4 = new MenuItem("Paste", model,tree);
			m1i4.setMnemonic('v');
			EditMenuItems.add(m1i0);
			EditMenuItems.add(m1i1);
			EditMenuItems.add(m1i2);
			EditMenuItems.add(m1i3);
			EditMenuItems.add(m1i4);
			JMenu EditMenu = new Menu(EditMenuItems,"Edit", model,tree);
			EditMenu.setMnemonic(KeyEvent.VK_E);
			menuBar.add(EditMenu);
			
			ArrayList<MenuItem> ViewMenuItems = new ArrayList<MenuItem>();	
			MenuItem m2i0 = new MenuItem("Show document fullscreen", model,tree);
			ViewMenuItems.add(m2i0);
			MenuItem m2i02 = new MenuItem("Exit fullscreen", model,tree);
			ViewMenuItems.add(m2i02);
			JMenu ViewMenu = new Menu(ViewMenuItems,"View", model,tree);
			menuBar.add(ViewMenu);
			
			ArrayList<MenuItem> TreeMenuItems = new ArrayList<MenuItem>();	
			MenuItem m2i2 = new MenuItem("Hide", model,tree);
			MenuItem m2i3 = new MenuItem("Show", model,tree);
			TreeMenuItems.add(m2i2);TreeMenuItems.add(m2i3);
			JMenu TreeMenu = new Menu(TreeMenuItems,"Tree", model,tree);
			menuBar.add(TreeMenu);
			
			ArrayList<MenuItem> ThemeMenuItems = new ArrayList<MenuItem>();	
			MenuItem m2i8 = new MenuItem("Metalic", model,tree);
			MenuItem m2i9 = new MenuItem("Motif", model,tree);
			ThemeMenuItems.add(m2i8);ThemeMenuItems.add(m2i9);
			Menu ThemeMenu = new Menu(ThemeMenuItems,"Theme", model,tree);
			menuBar.add(ThemeMenu);
			
			
			ArrayList<MenuItem> HelpMenuItems = new ArrayList<MenuItem>();	
			JMenu HelpMenu = new Menu(HelpMenuItems,"Help", model,tree);
			HelpMenu.addMenuListener(new HelpListener());
			HelpMenu.getInputMap().put(KeyStroke.getKeyStroke("F1"),"doSomething");
			HelpMenu.getActionMap().put("doSomething",new HelpAction());
			menuBar.add(HelpMenu);
			menuBar.add(Box.createGlue());
			
			
			ArrayList<MenuItem> ProfileMenuItems = new ArrayList<MenuItem>();	
			MenuItem m3i0 = new MenuItem("Details", model,tree);
			MenuItem m3i3 = new MenuItem("Sign out", model,tree);
			ProfileMenuItems.add(m3i0);
			ProfileMenuItems.add(m3i3);
			JMenu ProfileMenu = new Menu(ProfileMenuItems,"Profile", model,tree);
			menuBar.add(ProfileMenu);
			
			
			
			
		
			
			
			
		}
		return menuBar;
	}
}
