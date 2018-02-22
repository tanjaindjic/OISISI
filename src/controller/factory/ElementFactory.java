package controller.factory;

public class ElementFactory extends DocumentBuilding {
	
	private double width; 
	private double height; 
	private double x;
	private double y; 

	public ElementFactory(double x, double y, double height, double width) {
		this.name="Element";
		this.path="Element";
		this.width=width;
		this.height = height;
		this.x=x;
		this.y=y;
	}
	@Override
	public Object build(String path) {
		return null; 
	}
	
}
