package model;

import java.util.ArrayList;

import controller.command.AbstractCommand;
import controller.command.CommandManager;

public class Clipboard {

	
		private Object content;
		private ClipboardContentType type;
		
		private static Clipboard instance = null;
		
		public static Clipboard getInstance(){
			if (instance == null){
				instance = new Clipboard();
				
			}
			return instance;
		}
		
		private Clipboard()
		{
			type=ClipboardContentType.EMPTY;
			content=null;
		}
		
		public void delete(){
			
			instance=null;
			this.content=ClipboardContentType.EMPTY;
			this.type= null;
			
		}

		public ClipboardContentType getType() {
			return type;
		}

		public void setType(ClipboardContentType type) {
			this.type = type;
		}

		public void setContent(Object content) {
			this.content = content;
		}

		public Object getContent() {
			return content;
		}

	
}
