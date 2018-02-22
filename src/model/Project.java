package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observer;
import java.util.UUID;

import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import controller.observerArg.Akcija;
import controller.observerArg.Projekat_Arg;

@SuppressWarnings("serial")
public class Project extends Document implements Serializable, TreeNode{
	private ArrayList<GeRuDokument> gerudokuments;

	private ArrayList<String> gerudokumentPaths;

	private ArrayList<Owner> owners;
	
	
	public ArrayList<Owner> getOwners() {
		return owners;
	}
	
	public ArrayList<String> getGeRudokumentPaths(){
		
		return gerudokumentPaths;
	}
	
	
	public void setGeRuDokumentPaths(ArrayList<String> p){
		gerudokumentPaths=p;
	}
	
	public void addPath(String p){
		gerudokumentPaths.add(p);
	}

	public void setOwners(ArrayList<Owner> owners) {
		this.owners = owners;

	}
	@JsonIgnore
	public ShareTypes getOwner(UUID uniqueID){
		for(Owner own : owners){
			if(own.getId().toString().equals(uniqueID.toString())){
				return own.getType();
			}
		}
		return null;
	}
	
	
	
	@JsonIgnore
	public void addOwner(Workspace owner, ShareTypes stype) { 
		if (owners.isEmpty()) {
			Owner ownerToAdd = new Owner(owner, ShareTypes.OWNER);
			owners.add(ownerToAdd);
		}else{
			
			if(stype == ShareTypes.OWNER){
				return;
			}
			
			Owner ownerToAdd = new Owner(owner, stype);
			owners.add(ownerToAdd);
		}
	}
	@JsonIgnore
	public void removeOwner(Workspace owner) { 
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
				owners.add(new Owner(GeRuDokModel.getInstance().getActiveWorkspace().getDefaultGeRuDokument(), ShareTypes.OWNER));
			}else{
				owners.get(0).setType(ShareTypes.OWNER);
			}
		}
	}
	
	@JsonIgnore
	public Project getClone(Project proj){
		
		Project p = new Project();
		p.setGeRuDokumentPaths(proj.getGeRudokumentPaths());
		p.setGerudokuments(proj.getGerudokuments());
		p.setName(proj.getName());
		p.setOwners(proj.getOwners());
		p.setUniqueID(UUID.randomUUID());
		p.setPath("");
		
		return p;
	}
	
	
	@Override
	public String getName() {
		return super.getName();
	}

	public ArrayList<GeRuDokument> getGerudokuments() {
		return gerudokuments;
	}

	public void setGerudokuments(ArrayList<GeRuDokument> gerudokuments) {
		this.gerudokuments = gerudokuments;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		
		Projekat_Arg arg = new Projekat_Arg(null, this, Akcija.PREIMENUJ);
		
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
		
		Projekat_Arg arg = new Projekat_Arg(null, this, Akcija.PROMENI_PATH);
		
		setChanged();
		notifyObservers(arg);
		
		
	}

	@Override
	public UUID getUniqueID() {
		return super.getUniqueID();
	}

	public Project()
	{
		super();
		gerudokuments=new ArrayList<GeRuDokument>();
		gerudokumentPaths= new ArrayList<String>();
		owners= new ArrayList<Owner>();
		
		
	}
	
	public Project(String name, String path, Observer obs, ArrayList<GeRuDokument> gerudokument, UUID id) {
		super.name = name;
		super.path = path;
		super.uniqueID = id;
		gerudokuments = gerudokument;
		gerudokumentPaths= new ArrayList<String>();
		owners= new ArrayList<Owner>();
		addObserver(obs);
	}
	public Project(String name, ArrayList<GeRuDokument> gerudokument ){
		super.name = name;
		gerudokuments = gerudokument;
		super.path = "";
		super.uniqueID = UUID.randomUUID();
		gerudokumentPaths= new ArrayList<String>();
		for(GeRuDokument g : gerudokuments){
			gerudokumentPaths.add(g.getPath());
		}
		owners=null;

	}
	public Project(String name, String path, Observer obs) {
		super.name = name;
		super.path = path;
		super.uniqueID = UUID.randomUUID();
		gerudokuments = new ArrayList<GeRuDokument>();
		
		gerudokumentPaths= new ArrayList<String>();
		owners= new ArrayList<Owner>();
		addObserver(obs);
	}
	
	public Project(String name, Observer o){
		this.name=name;
		this.path = GeRuDokModel.getInstance().getCurrentUser().getPathToUsersFolder();
		this.uniqueID = super.setNewUniqueID();
		gerudokuments = new ArrayList<GeRuDokument>();
		gerudokumentPaths= new ArrayList<String>();
		owners= new ArrayList<Owner>();
		addObserver(o);
	}
	@Override
	@JsonIgnore
	public boolean deleteChild(UUID id) {
		if (this.containsChild(id)) {
			for (GeRuDokument grdok : gerudokuments) {
				if (grdok.getUniqueID() == id) {					
					grdok.removeOwner(this);
					setChanged();
					gerudokuments.remove(grdok);
					
					Projekat_Arg arg = new Projekat_Arg(grdok, this, Akcija.OBRISI);
					
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
		for (GeRuDokument grdok : gerudokuments) {
			if (grdok.getUniqueID() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	@JsonIgnore
	public Document getChild(UUID id) {
		for (GeRuDokument grdok : gerudokuments) {
			if (grdok.getUniqueID() == id) {
				return grdok;
			}
		}
		return null;
	}

	@Override
	@JsonIgnore
	public boolean addChild(Object obj) {
		GeRuDokument temp = null;
		try {
			temp = (GeRuDokument) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as GeRuDokument in Project.addChild()");
		}
		if (!this.containsChild(temp.getUniqueID())) {
			gerudokuments.add(temp);
			temp.addOwner(this);
			Projekat_Arg arg = new Projekat_Arg(temp, this, Akcija.DODAJ);
			
			setChanged();
			notifyObservers(arg);
			return true;
		}
		return false;
	}

	public boolean addChild(Object obj, ShareTypes stype) {
		GeRuDokument temp = null;
		try {
			temp = (GeRuDokument) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as GeRuDokument in Project.addChild()");
		}
		if (!this.containsChild(temp.getUniqueID())) {
			gerudokuments.add(temp);
			temp.addOwner(this,stype);
			Projekat_Arg arg = new Projekat_Arg(temp, this, Akcija.DODAJ);
			
			setChanged();
			notifyObservers(arg);
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@JsonIgnore
	public Enumeration children() {
		return (Enumeration<GeRuDokument>)gerudokuments;
	}
	@JsonIgnore
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		if(childIndex<gerudokuments.size() && childIndex>-1){
			return (TreeNode)gerudokuments.get(childIndex);
		}
		return null;
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return gerudokuments.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		if(node instanceof GeRuDokument){
			GeRuDokument grd = (GeRuDokument)node;
			for(int i=0; i<gerudokuments.size(); i++)
				if(gerudokuments.get(i)==grd)
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
}
