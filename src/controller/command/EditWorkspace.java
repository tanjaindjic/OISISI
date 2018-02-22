package controller.command;

import model.Workspace;

public class EditWorkspace extends AbstractCommand {

	private String name;
	private Workspace workspace;

	public boolean doCommand() {

		if (!name.trim().isEmpty()) {
			String temp = workspace.getName();
			workspace.setName(name);
			return true;
		}
		return true;

	}

	public boolean undoCommand() {

		if (!name.equals(workspace.getName())) {

			String temp = workspace.getName();
			workspace.setName(name);
			return true;

		}
		return false;

	}

	
	public EditWorkspace(String name, Workspace workspace) {
		this.name= name;
		this.workspace= workspace;
		this.selectedObject=workspace;
	}

}
