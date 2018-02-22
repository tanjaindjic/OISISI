package view;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.WorkspaceModel;

public class Menu extends JMenu{
	private ArrayList<MenuItem> menuItems;
	private String name;
	private WorkspaceModel model;
	private Tree tree;
	
	public Menu(ArrayList<MenuItem> menuItems, String name, WorkspaceModel m, Tree t){
		this.menuItems = menuItems;
		this.name = name;
		this.model = m;
		this.tree = t;
		setText(name);
		for(MenuItem mi : menuItems){
			JMenuItem jmi = new MenuItem(mi.getName(), model, tree);
			add(jmi);
		}
	}
}
