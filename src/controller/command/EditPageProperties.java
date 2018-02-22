package controller.command;

import model.Page;

public class EditPageProperties extends AbstractCommand {

	private String name;

	private Page page;

	private double width;

	private double height;
	
	private double x;
	 
	private double y;

	public boolean doCommand() {
	
		if (!name.trim().isEmpty()) {
			String temp = page.getName();
			page.setName(name);
			
			double tempH = page.getHeight();
			double tempW = page.getWidth();
			
			double tempX = page.getX();
			double tempY = page.getY();
			
			page.setDimensions(width, height, x, y);
			
			
			height= tempH;
			width = tempW;
			x=tempX;
			y= tempY;
			
			return true;
		}
		return false;
	}

	public boolean undoCommand() {
		
		if (provera()) {

			String temp = page.getName();
			page.setName(name);
			name=temp;
			
			double tempH = page.getHeight();
			double tempW = page.getWidth();
			double tempX = page.getX();
			double tempY = page.getY();
			
			page.setHeight(height);
			page.setWidth(width);
			
			height= tempH;
			width = tempW;
			x= tempX;
			y= tempY;
			return true;

		}
		return false;
	}
	
	public boolean provera(){
		
		return (!name.equals(page.getName()) || page.getHeight()!=height || page.getWidth()!=width || page.getX() != x || page.getY()!= y);
		
	}

	public EditPageProperties(String name, Page page, double width, double height, double x, double y) {
		this.height=height;
		this.width=width;
		this.page=page;
		this.name=name;
		this.x=x;
		this.y=y;
		this.selectedObject=page;
	}
}
