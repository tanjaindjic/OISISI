package controller.factory;

import java.util.ArrayList;
import java.util.Observer;

import model.Page;

public class PageFactory extends DocumentBuilding {
	
	private Observer observer;
	private double width;
	private double height;
	private double x; 
	private double y; 
	
	
	public PageFactory(String name, String path, Observer obs, double width, double height, double x, double y) {
		this.name=name;
		this.path=path;
		this.width=width;
		this.height = height;
		this.x=x;
		this.y=y;
		this.observer=obs;
	}
	@Override
	public Object build(String path) {
		return new Page(name, path, width, height, x, y, observer );
	}

}
