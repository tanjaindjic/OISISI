package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import listeners.CopyListener;
import listeners.CutListener;
import listeners.OpenListener;
import listeners.PasteListener;
import listeners.RedoListener;
import listeners.SaveAsListener;
import listeners.SaveListener;
import listeners.ToolsListener;
import listeners.UndoListener;
import listenersTree.AddProjectListener;
import listenersTree.DeleteMouseListener;
import listenersTree.OpenWorkspaceListener;
import model.WorkspaceModel;

public class Tool extends JButton{
	private String name;
	private String pathToIcon;
	private Tree tree;
	private WorkspaceModel model;
	
	public Tool(String name, String pathToIcon, Tree t, WorkspaceModel m){
		this.name = name;
		this.pathToIcon = pathToIcon;
		this.model = m;
		this.tree = t;
		
		setToolTipText(name);
		setIcon(new ImageIcon(pathToIcon));
		if(name.equals("New Project"))
			addActionListener(new AddProjectListener(model, tree));
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
		else if(name.equals("Delete"))
			addActionListener(new DeleteMouseListener(model, tree));
		else if(name.equals("Save"))
			addActionListener(new SaveListener());
		else if(name.equals("Save As"))
			addActionListener(new SaveAsListener());
		else if(name.equals("Open"))
			addActionListener(new OpenWorkspaceListener(model, tree));
		else
			addActionListener(new ToolsListener(tree, model));
	}
}
