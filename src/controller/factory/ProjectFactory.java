package controller.factory;

import java.util.ArrayList;
import java.util.Observer;

import model.Project;

public class ProjectFactory extends DocumentBuilding {
	
	
	private Observer observer;
	
	public ProjectFactory(String name, String path, Observer obs) {
		this.name= name;
		this.path= path;
		this.observer=obs;
	}
	@Override
	public Object build(String path) {
		return new Project(name, path, observer);
	}

}
