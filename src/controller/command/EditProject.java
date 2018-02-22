package controller.command;

import model.Project;

public class EditProject extends AbstractCommand {

	private String name;
	private Project project;
	
	public boolean doCommand() {


		if (!name.trim().isEmpty()) {
			String temp = project.getName();
			project.setName(name);
			return true;
		}
		return true;

	}

	public boolean undoCommand() {

		if (!name.equals(project.getName())) {

			String temp = project.getName();
			project.setName(name);
			return true;

		}
		return false;

	}

	public EditProject(Project project, String name) {
		
		this.name=name;
		this.project=project;
		this.selectedObject=project;
		

	}

}
