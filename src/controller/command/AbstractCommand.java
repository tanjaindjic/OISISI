package controller.command;

public abstract class AbstractCommand {

	protected Object selectedObject;

	public abstract boolean doCommand();
	
	public abstract boolean undoCommand();
	
	
}
