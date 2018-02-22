package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observer;
import java.util.UUID;

import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import controller.observerArg.Akcija;
import controller.observerArg.GeRuDokument_Arg;
import controller.observerArg.Stranica_Arg;

@SuppressWarnings("serial")
public class GeRuDokument extends Document implements Serializable, TreeNode {

	private ArrayList<Page> pages;
	
	
	private ArrayList<Owner> owners;


	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
	
	public ArrayList<Owner> getOwners() {
		return owners;
	}
	
	public void setOwners(ArrayList<Owner> owners) {
		this.owners = owners;
	}
	@JsonIgnore
	public void addOwner(Project owner, ShareTypes stype) {
		if (owners.isEmpty()) {
			Owner ownerToAdd = new Owner(owner, ShareTypes.OWNER);
			owners.add(ownerToAdd);
		} else {
			
			if(stype == ShareTypes.OWNER){
				return;
			}
			
			
			Owner ownerToAdd = new Owner(owner, stype);
			owners.add(ownerToAdd);
		}
		GeRuDokument_Arg arg = new GeRuDokument_Arg(null, this, Akcija.DELI);
		setChanged();
		notifyObservers(arg);
	}
	@JsonIgnore
	public void addOwner(Project owner) {
		if (owners.isEmpty()) {
			Owner ownerToAdd = new Owner(owner, ShareTypes.OWNER);
			owners.add(ownerToAdd);
		}
		GeRuDokument_Arg arg = new GeRuDokument_Arg(null, this, Akcija.DELI);
		setChanged();
		notifyObservers(arg);
	}
	@JsonIgnore
	public void removeOwner(Project owner) {
		int index = -1;
		Owner temp;
		for (int i = 0; i < owners.size(); i++) {
			temp = owners.get(i);
			if(temp.getId().toString().equals(owner.getUniqueID().toString())){
				index=i;
				break;
			}
		}
		if(index!=-1){
			owners.remove(index);
			if(owners.isEmpty())
			{
			}else{
				owners.get(0).setType(ShareTypes.OWNER);
			}
		}
		GeRuDokument_Arg arg = new GeRuDokument_Arg(null, this, Akcija.DELI); 
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
		GeRuDokument_Arg arg = new GeRuDokument_Arg(null, this, Akcija.PREIMENUJ);
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
		GeRuDokument_Arg arg = new GeRuDokument_Arg(null, this, Akcija.PROMENI_PATH);
		setChanged();
		notifyObservers(arg);
		
	}
	@Override
	public UUID getUniqueID() {
		return super.getUniqueID();
	}
	public GeRuDokument(String name, String path, Observer obs) {
		super.name = name;
		super.path = path;
		super.uniqueID = UUID.randomUUID();
		this.pages = new ArrayList<Page>();
		this.owners = new ArrayList<Owner>();
		addObserver(obs);
	}
	public GeRuDokument(String name, String path, ArrayList<Page> pages, ArrayList<Owner> owners, UUID id, Observer obs) {
		super.name = name;
		super.path = path;
		super.uniqueID = id;
		this.pages = pages;
		this.owners = owners;
		addObserver(obs);
	}
	public GeRuDokument(){ 
	
		this.pages = new ArrayList<Page>();
		this.owners = new ArrayList<Owner>();
	}
	public GeRuDokument(String name, Observer obs) { 
		this.name=name;
		this.path="";
		super.uniqueID = UUID.randomUUID();
		this.pages = new ArrayList<Page>();
		this.owners = new ArrayList<Owner>();
		addObserver(obs);
	}
	@Override
	@JsonIgnore
	public boolean deleteChild(UUID id) {
		if (this.containsChild(id)) {
			for (Page page : pages) {
				if (page.getUniqueID().toString().equals(id.toString())) {
					pages.remove(page);
					GeRuDokument_Arg arg = new GeRuDokument_Arg(page, this, Akcija.OBRISI);
					setChanged();
					notifyObservers(arg);
					return true;
				}
			}
		}
		return false;
	}
	@JsonIgnore
	@Override
	public boolean containsChild(UUID id) {
		for (Page page : pages) {
			if (page.getUniqueID().toString().equals(id.toString())) {
				return true;
			}
		}
		return false;
	}
	@JsonIgnore
	@Override
	public Document getChild(UUID id) {
		for (Page page : pages) {
			if (page.getUniqueID().toString().equals(id.toString())) {
				return page;
			}
		}
		return null;
	}
	@JsonIgnore
	@Override
	public boolean addChild(Object obj) {
		Page temp = null;
		try {
			temp = (Page) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as Page in GeRuDokument.addChild()");
		}

		if (!this.containsChild(temp.getUniqueID())) {
			pages.add(temp);
			GeRuDokument_Arg arg = new GeRuDokument_Arg(temp, this, Akcija.DODAJ);
			setChanged();
			notifyObservers(arg);
			return true;
		}
		return false;
	}
	@JsonIgnore
	public boolean addChild(Object obj, ShareTypes stype) {
		Page temp = null;
		try {
			temp = (Page) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as Page in GeRuDokument.addChild()");
		}
		if (!this.containsChild(temp.getUniqueID())) {
			pages.add(temp);
			GeRuDokument_Arg arg = new GeRuDokument_Arg(temp, this, Akcija.DODAJ);
			setChanged();
			notifyObservers(arg);
			return true;
		}
		return false;
	}
	@JsonIgnore
	public ShareTypes getOwner(UUID uniqueID){
		for(Owner own : owners){
			if(own.getId()==uniqueID){
				return own.getType();
			}
		}
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@JsonIgnore
	public Enumeration children() {
		return (Enumeration<Page>)pages;
	}
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		if(childIndex<pages.size() && childIndex>-1){
			return (TreeNode)pages.get(childIndex);
		}
		return null;
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return pages.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		if(node instanceof Page){
			Page p = (Page)node;
			for(int i=0; i<pages.size();i++)
				if(p==pages.get(i))
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
		return false;
	}
	public String toString(){
		return name;
	}
	
	@JsonIgnore
	public GeRuDokument getClone(GeRuDokument grd){
		
		GeRuDokument g = new GeRuDokument();
		g.setUniqueID(UUID.randomUUID());
		g.setName(grd.getName());
		g.setPath("");
		g.setOwners(grd.getOwners());
		g.setPages(grd.getPages());
		return g;
	}
		

}
