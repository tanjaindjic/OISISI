package model;

import java.io.Serializable;
import java.util.UUID;

import javafx.beans.Observable;

public abstract class Document extends java.util.Observable {

		protected String name;
		protected String path;
		protected UUID uniqueID;

		protected String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
		public void setUniqueID(UUID uniqueID){
			this.uniqueID=uniqueID;
		}
		public UUID getUniqueID() {
			return uniqueID;
		}

		public Document()
		{
			name="";
			path="";
			uniqueID=UUID.randomUUID();
			
		}

		public abstract boolean deleteChild(UUID id);
		public abstract boolean containsChild(UUID id);
		public abstract Object getChild(UUID id);
		public abstract boolean addChild(Object obj);

		public UUID setNewUniqueID() {
			return UUID.randomUUID();
		}
		

		
		
}
