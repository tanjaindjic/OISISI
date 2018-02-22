package controller.factory;

public abstract class DocumentBuilding {
	
	protected String name;
	
	protected String path;
	
	public abstract Object build(String path);
}
