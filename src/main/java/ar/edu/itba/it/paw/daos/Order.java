package ar.edu.itba.it.paw.daos;

public class Order {
	private String expression;
	private boolean asc = true; //Default ascendent  
	
	public Order(String expression, boolean asc) {
		super();
		this.expression = expression;
		this.asc = asc;
	}
	/*By default ASC*/
	public Order(String expression){
		this.expression = expression;
	}
	public String toString(){
		String string = expression;
		if(asc==true){
			string+= " ASC";
		}
		else{
			string+= " DESC";
		}
		return string;
	}
	
}
