package view;

import javax.swing.JMenuItem;

import listeners.CopyListener;
import listeners.CutListener;
import listeners.ExitFullscreenListener;
import listeners.FullscreenListener;
import listeners.MenuItemsListener;
import listeners.PasteListener;
import listeners.RedoListener;
import listeners.SaveAsListener;
import listeners.SaveListener;
import listeners.ThemeListener;
import listeners.UndoListener;
import listenersTree.AddProjectListener;
import listenersTree.NewWorkspaceListener;
import listenersTree.OpenWorkspaceListener;
import model.WorkspaceModel;

@SuppressWarnings("serial")
public class MenuItem extends JMenuItem {
	private String name;
	private WorkspaceModel model;
	private Tree tree;

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public WorkspaceModel getWModel() {
		return model;
	}

	public void setWModel(WorkspaceModel model) {
		this.model = model;
	}

	public MenuItem(String name, WorkspaceModel model, Tree tree){
		this.name=name;
		this.model = model;
		this.tree = tree;
		
		setText(name);
		if(name.equals("New Workspace"))
			addActionListener(new NewWorkspaceListener(model, tree));
		else if(name.equals("New Project"))
			addActionListener(new AddProjectListener(model, tree));
		else if(name.equals("Open Workspace"))
			addActionListener(new OpenWorkspaceListener(model, tree));
		else if(name.equals("Undo"))
			addActionListener(new UndoListener(model, tree));
		else if(name.equals("Redo"))
			addActionListener(new RedoListener(model, tree));
		else if(name.equals("Cut"))
			addActionListener(new CutListener(model, tree));
		else if(name.equals("Copy"))
			addActionListener(new CopyListener(model, tree));
		else if(name.equals("Paste"))
			addActionListener(new PasteListener(model, tree));
		else if(name.equals("Metalic"))
			addActionListener(new ThemeListener());
		else if(name.equals("Motif"))
			addActionListener(new ThemeListener());
		else if(name.equals("Save"))
			addActionListener(new SaveListener());
		else if(name.equals("Save As"))
			addActionListener(new SaveAsListener());
		else if(name.equals("Show document fullscreen"))
			addActionListener(new FullscreenListener());
		else if(name.equals("Exit fullscreen"))
			addActionListener(new ExitFullscreenListener());
		else
			addActionListener(new MenuItemsListener(this, tree));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private void onClick(){
		
	}
}
