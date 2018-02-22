package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JToolBar;

import listeners.RedoListener;
import listeners.UndoListener;
import model.WorkspaceModel;

public class ToolBar extends JToolBar{
	private static ToolBar toolBar = null;
	private  ArrayList<Tool> toolList;
	private Tool selectedTool;
	private static WorkspaceModel model;
	private static Tree tree;
	
	private ToolBar(){
		
	}
	
	public static ToolBar getInstance(WorkspaceModel m, Tree t) {
		model=m;
		tree=t;
		
		if(toolBar == null){
			toolBar = new ToolBar();
			String myLoc = (System.getProperty("user.dir")+"/src/view/");
			
			Tool tb1 = new Tool("New Project",myLoc + "tool1.png",tree,model);
			toolBar.add(tb1, BorderLayout.LINE_START);
			
			Tool tb2 = new Tool("Open", myLoc+"tool3.jpg",tree,model);
			toolBar.add(tb2);		
			
			Tool tb3 = new Tool("Save", myLoc+"tool4.jpg",tree,model);
			toolBar.add(tb3);
			
			Tool tb4 = new Tool("Save As",myLoc+"tool5.png",tree,model);
			toolBar.add(tb4);
			
			toolBar.addSeparator();
			
			Tool tb5 = new Tool("Undo", myLoc+"tool6.jpg",tree,model);
			
			toolBar.add(tb5);
			
			Tool tb6 = new Tool("Redo", myLoc+"tool7.jpg",tree,model);
			
			toolBar.add(tb6);
			
			toolBar.addSeparator();
			
			Tool tb7 = new Tool("Cut", myLoc+"tool8.png",tree,model);
			toolBar.add(tb7);
			
			Tool tb8 = new Tool("Copy",myLoc+"tool9.png",tree,model);
			toolBar.add(tb8);
			
			Tool tb9 = new Tool("Paste", myLoc+"tool10.png",tree,model);
			toolBar.add(tb9);
			
			toolBar.addSeparator();
			
			Tool tb10 = new Tool("Delete", myLoc+"tool11.png",tree,model);
			toolBar.add(tb10);
		}
		return toolBar;
	}
	public void addTool(Tool tool){
		toolList.add(tool);
	}
	public void removeTool(Tool tool){
		toolList.remove(tool);
	}
	public Tool getSelectedTool(){
		return selectedTool;
		
	}
	
}
