package ar.edu.itba.it.paw.models;

public abstract class Entity {
	long id = 0;
	
	public Entity(long id){
		this.id = id;
	}
	public Entity(){
		
	}
	
	public long getId(){
		return this.id;
	}
	public void setId(long id){
		this.id = id;
	}
}

