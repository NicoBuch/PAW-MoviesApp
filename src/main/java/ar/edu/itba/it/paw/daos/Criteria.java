package ar.edu.itba.it.paw.daos;

import ar.edu.itba.it.paw.daos.Session;
public class Criteria {
	
	protected String sqlOperator;
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
	public static Criteria biggerOrEq(String field, Object value){
		return new Criteria(">=", field, value);
	}
	public static Criteria lowerOrEq(String field, Object value){
		return new Criteria("<=", field, value);
	}
	
	@Override
	public String toString(){
		return field +  sqlOperator  + Session.convertToSql(value); 
	}
}
