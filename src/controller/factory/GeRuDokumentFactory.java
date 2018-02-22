package controller.factory;

import java.util.ArrayList;
import java.util.Observer;

import model.GeRuDokument;

public class GeRuDokumentFactory extends DocumentBuilding {
	
	
	private Observer observer;
	
	public GeRuDokumentFactory(String name, String path, Observer obs) {
		this.name = name;
		this.path = path;
		this.observer=obs;
	}
	@Override
	public Object build(String path) {
		return new GeRuDokument(name, path, observer);
	}

}
