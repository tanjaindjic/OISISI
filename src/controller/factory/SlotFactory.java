package controller.factory;

import java.util.ArrayList;
import java.util.Observer;

import model.Slot;

public class SlotFactory extends DocumentBuilding {

	
	private double width;
	private double height; 
	private double x;
	private double y;
	
	public SlotFactory(double width, double height, double x, double y) { 
		this.name="Slot";
		this.path="Slot";
		this.width=width;
		this.height = height;
		this.x=x;
		this.y=y;	
	}
	
	@Override
	public Object build(String path) {
		return new Slot(width, height, x, y);
	}
}
