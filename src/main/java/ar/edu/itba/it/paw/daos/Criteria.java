package ar.edu.itba.it.paw.daos;

import org.eclipse.jdt.internal.core.util.ToStringSorter;

public class Criteria {
	
	private String sqlOperator;
	protected String field;
	protected Object value;
	
	/*Constructor basico, hay que ver el patron bien*/
	public Criteria(String sqlOperator, String field, Object value){
		this.sqlOperator = sqlOperator;
		this.field = field;
		this.value = value;
	}
	/*Metodo para hacer una consulta equal*/
	public static Criteria eq(String field, Object value){
		return new Criteria("=" , field, value);
	}
	public static Criteria all(){
		return new Criteria("" , "" , null);
	}
	@Override
	public String toString(){
		return field +  sqlOperator  + value; 
	}
}
