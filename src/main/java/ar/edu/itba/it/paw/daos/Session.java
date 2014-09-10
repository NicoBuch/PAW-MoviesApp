package ar.edu.itba.it.paw.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class Session<T> {
	
	String userName = "paw";
	String password = "paw";
	String db = "jdbc:postgresql://localhost/paw7";
	String dbname = "paw7";
	Connection conn;

	private List<Criteria> criteria = new ArrayList<Criteria>();
	
	
	public Session(){
		Properties connectionProps = new Properties();
	    connectionProps.put("user",userName);
	    connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection(db, connectionProps);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		System.out.println("Connected to database");
	}
	
	public  void add(Criteria criteria){
		this.criteria.add(criteria);
	}
	
	public ResultSet list(String table){
		String query = "SELECT " + "* " + "FROM " + table;
		if(criteria.size() > 0){
			query = query + " WHERE ";
			Iterator<Criteria> critIterator = criteria.iterator();
			while(critIterator.hasNext()){
				query = query + critIterator.next();
				if(critIterator.hasNext())
					query = query + " AND ";
			}
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
