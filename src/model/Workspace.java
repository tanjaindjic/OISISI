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
import controller.observerArg.RadniProstor_Arg;

@SuppressWarnings("serial")
public class Workspace extends Document implements Serializable, TreeNode {

	private ArrayList<Project> projects;

	private ArrayList<String> projectPaths; 
	
	private Project defaultProject;
	private GeRuDokument defaultGeRuDokument;
	
	public GeRuDokument getDefaultGeRuDokument(){
		return defaultGeRuDokument;
	}
	public Project getDefaultProject(){
		return defaultProject;
	}
	
	
	public ArrayList<String> getProjectPaths(){
		
		return projectPaths;
	}
	
	
	public void setProjectPaths(ArrayList<String> p){
		projectPaths=p;
	}
	
	public void addProjectPath(String p){
		projectPaths.add(p);
	}

	@Override
	public String getName() {
		return super.getName();
	}
	@Override
	public void setName(String name) {
		super.setName(name);
		RadniProstor_Arg arg = new RadniProstor_Arg(null, this,Akcija.PREIMENUJ ); 
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
		RadniProstor_Arg arg = new RadniProstor_Arg(null, this,Akcija.PROMENI_PATH ); 
		setChanged();
		notifyObservers(arg);
	}
	public ArrayList<Project> getProjects() {
		return projects;
	}
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	
	public void setDefaultProject(Project defaultProject) {
		this.defaultProject = defaultProject;
		
		RadniProstor_Arg arg = new RadniProstor_Arg(defaultProject, this,Akcija.DODAJ_DEFAULT ); // nisam siguran za ovaj deo
		
		setChanged();
		notifyObservers(arg); 
		
	}
	
	@Override
	public UUID getUniqueID() {
		return super.getUniqueID();
		}
	public Workspace()
	{
		super.name="";
		super.path="";
		super.uniqueID=UUID.randomUUID();
		projects=new ArrayList<Project>();
		defaultProject= new Project();
		projectPaths= new ArrayList<String>();

	}
	public Workspace(String name, String path,ArrayList<Project> projects,Project documents, UUID id, Observer obs) {
		super.name = name;
		
		super.path = path;
		super.uniqueID = id;
		this.projects= projects;
		this.defaultProject = documents;
		projectPaths= new ArrayList<String>();
		addObserver(obs);
	}
	public Workspace (String name){
		this.name=name;
		this.projects= new ArrayList<Project>();
		this.path=GeRuDokModel.getInstance().getCurrentUser().getPathToUsersFolder();
		this.defaultProject=new Project();
		super.uniqueID=UUID.randomUUID();
		projectPaths= new ArrayList<String>();
		
	}
	public Workspace(String name, String path, Observer obs) {
		this.name = name;
		this.path = path;
		this.projects= new ArrayList<Project>();
		this.defaultProject = new Project();
		super.uniqueID = UUID.randomUUID();
		addObserver(obs);
		this.defaultProject= new Project();
		projectPaths= new ArrayList<String>();
	}
	
	
	@Override
	public boolean deleteChild(UUID id) {
		if (this.containsChild(id)) {
			for (Project proj : projects) {
				if (proj.getUniqueID().toString().equals(id.toString())) {
					projects.remove(proj);
					setChanged();
					RadniProstor_Arg arg = new RadniProstor_Arg(proj, this,Akcija.OBRISI );
					
					setChanged();
					notifyObservers(arg);
					return true;
				}
			}	
		} 
		return false;
	}

	@Override
	public boolean containsChild(UUID id) {
		for (Project proj : projects) {
			if (proj.getUniqueID().toString().equals(id.toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Document getChild(UUID id) {
		for (Project proj : projects) {
			if (proj.getUniqueID().toString().equals(id.toString())) {
				return proj;
			}
		}
		return null;
	}
	
	public boolean addChild(Object obj, ShareTypes stype) {
		Project temp = null;
		try {
			temp = (Project) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as Project in Workspace.addChild()");
		}
		if (!this.containsChild(temp.getUniqueID())) {
			projects.add(temp);
			temp.addOwner(this,stype);
			return true;
		}
		return false;
	}
	

	@Override
	public boolean addChild(Object obj) {
		boolean retVal = false;
		Project temp = null;
		try {
			temp = (Project) obj;
		} catch (Exception e) {
			System.out.println("Failed to cast Object as Project in Workspace.addChild()");
		}

		if (!this.containsChild(temp.getUniqueID())) {
			projects.add(temp);
			
			RadniProstor_Arg arg = new RadniProstor_Arg(temp, this,Akcija.DODAJ );
			
			setChanged();
			notifyObservers(arg);
			
			retVal=true;
		}

		return retVal;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Enumeration children() {
		return (Enumeration<Project>)projects;
	}
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		if(childIndex<projects.size()  && childIndex>-1){
			return (TreeNode) projects.get(childIndex);
		}
		return null;
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return projects.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		if(node instanceof Project){
			Project p = (Project)node;
			for(int i=0; i<projects.size(); i++){
				if(projects.get(i)==p){
					return i;
				}
			}
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
