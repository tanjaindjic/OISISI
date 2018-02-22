package model;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.UUID;

import javax.swing.tree.TreeNode;

@SuppressWarnings("serial")
public class Slot implements Serializable {
	private double x;
	private double y;
	private double height;
	private double width;
	private UUID uniqueID;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public UUID getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(UUID uniqueID){
		this.uniqueID = uniqueID;
	}
	public Slot(double x, double y, double height, double width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	public Slot(double x, double y, double height, double width, UUID id) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		uniqueID = id;
	}
	public Slot(){
		
	}
	
}

