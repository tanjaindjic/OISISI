package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observer;
import java.util.UUID;

import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import controller.observerArg.Akcija;
import controller.observerArg.Projekat_Arg;
import controller.observerArg.Stranica_Arg;

public class Page extends Document implements TreeNode {

	private ArrayList<Slot> slots;
	private double x;
	private double y;
	private double height;
	private double width;
	
	public ArrayList<Slot> getSlots() {
		return slots;
	}
	public void setSlots(ArrayList<Slot> slots) {
		this.slots = slots;
		for (Slot item : this.slots) {
			Stranica_Arg arg = new Stranica_Arg(item, this, Akcija.DODAJ);
			setChanged();
			notifyObservers(arg);
		}
	}

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
	
	
	public void setDimensions(double width, double height, double x, double y){// DA NE BI IMALI 100 case-ova u update
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		Stranica_Arg arg = new Stranica_Arg(null, this, Akcija.PROMENI_DIMENZIJE);
		setChanged();
		notifyObservers(arg);
		
		
	}

	@Override
	public String getName() {
		return super.getName();
	}
	@Override
	public void setName(String name) {
		super.setName(name);
		Stranica_Arg arg = new Stranica_Arg(null, this, Akcija.PREIMENUJ);
		setChanged();
		notifyObservers(arg);
	}
	@Override
	public String getPath() {
		return super.getPath();
	}
	@Override
	public void setPath(String path) {
		super.setPath(path);
		Stranica_Arg arg = new Stranica_Arg(null, this, Akcija.PROMENI_PATH);
		setChanged();
		notifyObservers(arg);
	}
	@Override
	public UUID getUniqueID() {
		return super.getUniqueID();
	}

	public Page(String name, String path, double width, double height, double x, double y, Observer obs) {
		super.name = name;
		super.path = path;
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		addObserver(obs);
		super.uniqueID = UUID.randomUUID();
		this.slots = new ArrayList<Slot>();
	}
	public Page(String name, String path, double width, double height, double x, double y,ArrayList<Slot> slots, UUID id, Observer obs) {
		super.name = name;
		super.path = path;
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		addObserver(obs);
		super.uniqueID = id;
		this.slots = slots;
	}
	public Page(){
	}
	@Override
	@JsonIgnore
	public boolean deleteChild(UUID id) {
		if (this.containsChild(id)) {
			for (Slot slot : slots) {
				if (slot.getUniqueID().toString().equals( id.toString())) {
					slots.remove(slot);
					Stranica_Arg arg = new Stranica_Arg(slot, this, Akcija.OBRISI);
					setChanged();
					notifyObservers(arg);
					return true;
				}
			}
		} 
		return false;
	}
	@Override
	@JsonIgnore
	public boolean containsChild(UUID id) {
		for (Slot slot : slots) {
			if (slot.getUniqueID().toString().equals(id.toString())) {
				return true;
			}
		}
		return false;
	}
	@Override
	@JsonIgnore
	public Slot getChild(UUID id) {
		for (Slot slot : slots) {
			if (slot.getUniqueID().toString().equals(id.toString())) {
				return slot;
			}
		}
		return null;
	}
	@Override
	@JsonIgnore
	public boolean addChild(Object obj) {
		boolean retVal = false;
		Slot temp = null;
		try {
			temp = (Slot) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as Slot in Page.addChild()");
		}
		if (!this.containsChild(temp.getUniqueID())) {
			slots.add(temp);
			
			Stranica_Arg arg = new Stranica_Arg(temp, this, Akcija.DODAJ);
			setChanged();
			notifyObservers(arg);
			
			retVal=true;
		}
		return retVal;
	}
	
	@JsonIgnore
	public Page getClone(){
		
		Page  p= new Page();
		p.setSlots(this.getSlots());
		p.setName(this.getName());
		p.setDimensions(this.getWidth(), this.getHeight(),this.getX(), this.getY());
		p.setUniqueID(UUID.randomUUID());
		p.setPath("");
		return p;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@JsonIgnore
	public Enumeration children() {
		return (Enumeration<Slot>)slots;
	}
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		if(childIndex<slots.size() && childIndex>-1){
			return (TreeNode)slots.get(childIndex);
		}
		return null;
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return slots.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		if(node instanceof Slot){
			Slot s = (Slot) node;
			for(int i=0; i<slots.size();i++)
				if(s==slots.get(i))
					return i;
		}
		return -1;
	}
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		return null;
	}
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return true;
	}
	public String toString(){
		return name;
	}

}
