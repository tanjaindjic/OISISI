package controller.factory;

import java.util.ArrayList;
import java.util.Observer;

import model.Workspace;

public class WorkspaceFactory extends DocumentBuilding {
	
	private Observer observer;
	
	public WorkspaceFactory(String name, String path, Observer obs) {
		this.name=name;
		this.path=path;
		this.observer=obs;
	}
	@Override
	public Object build(String path) {
	
		return new Workspace(name, path, observer);
	}

}
