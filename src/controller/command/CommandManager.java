package controller.command;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandManager { 

	private static int activePosition; 

	private static ArrayList<AbstractCommand> abstractCommands;


	private static CommandManager cmd = null;


	private CommandManager() {} 
	
	public static CommandManager getCMD(){ 
		if (cmd == null){
			cmd = new CommandManager();
			activePosition = -1;
			abstractCommands = new ArrayList<AbstractCommand>();
		}
		
		return cmd;
	}

	public static void deleteCMD() { 
		cmd = null;
	}

	public void addCommand(AbstractCommand command) {
		
		if(activePosition != abstractCommands.size()){
			while(activePosition != abstractCommands.size()){
				abstractCommands.remove(abstractCommands.size()-1);
			}
		}
		activePosition++;
		this.addAbstractCommand(command);
		this.doCommandAt(activePosition);
	}


	public void deleteAll() {
		activePosition = -1;
		this.removeAllAbstractCommands();
		deleteCMD();
	}

	public int getActivePosition() {
		// TODO: implement
		return activePosition;
	}
	
	public boolean doCommand(){
		
		if (activePosition < abstractCommands.size()-1) {
			
			activePosition++;
		
			return doCommandAt(activePosition);
		}
		return false;
		
	}

	public boolean doCommandAt(int position) {
		// TODO: implement

		return abstractCommands.get(position).doCommand();
	}

	public boolean undoCommand() {

		if (activePosition > -1) {

			Boolean returnVal = this.undoCommandAt(activePosition);
			activePosition--;
			return returnVal;
		}
		return false;
	}

	private boolean undoCommandAt(int position) {
		// TODO: implement
		return abstractCommands.get(position).undoCommand();
	}

	public ArrayList<AbstractCommand> getAbstractCommands() {
		if (abstractCommands == null)
			abstractCommands = new ArrayList<AbstractCommand>();
		return abstractCommands;
	}

	public Iterator getIteratorAbstractCommand() {
		if (abstractCommands == null)
			abstractCommands = new ArrayList<AbstractCommand>();
		return abstractCommands.iterator();
	}

	public void setAbstractCommands(ArrayList<AbstractCommand> newAbstractCommand) {
		removeAllAbstractCommands();
		for (Iterator<AbstractCommand> iter = newAbstractCommand.iterator(); iter.hasNext();)
			addAbstractCommand((AbstractCommand) iter.next());
	}

	public void addAbstractCommand(AbstractCommand newAbstractCommand) {
		if (newAbstractCommand == null)
			return;
		if (this.abstractCommands == null)
			this.abstractCommands = new ArrayList<AbstractCommand>();
		if (!this.abstractCommands.contains(newAbstractCommand)){
			this.abstractCommands.add(newAbstractCommand);
			newAbstractCommand.doCommand();
			activePosition++;
			
		}
			
	
	}

	public void removeAbstractCommand(AbstractCommand oldAbstractCommand) {
		if (oldAbstractCommand == null)
			return;
		if (this.abstractCommands != null)
			if (this.abstractCommands.contains(oldAbstractCommand))
				this.abstractCommands.remove(oldAbstractCommand);
	}

	public void removeAllAbstractCommands() {
		if (abstractCommands != null)
			abstractCommands.clear();
	}

}
