package model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Owner {

	@JsonIgnore
	private Document holder;
	
	
	private ShareTypes type;
	
	private UUID id;

	public void setId(UUID id){
		this.id=id;
	}
	
	public UUID getId(){
		return this.id;
	}
	
	public void setType(ShareTypes type) {
		this.type = type;
	}
	public void setHolder(Document holder){
		this.holder=holder;
	}
	public Document getHolder() {
		return holder;
	}

	public ShareTypes getType() {
		return type;
	}

	public Owner(Document holder, ShareTypes type) {
		this.holder = holder;
		this.type = type;
		this.id =holder.getUniqueID();
	}
	public Owner(){
	}
	public String toString(){
		return holder.name;
	}
	
}

