package ar.edu.itba.it.paw.daos;

//import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

//import ar.edu.itba.it.paw.models.Movie;

public class Session<T> {
	
	String userName = "paw";
	String password = "paw";
	String db = "jdbc:postgresql://localhost/paw7";
	String dbname = "paw7";
	Connection conn;

	private Class<T> clazz;
	//private List<String> returnFields;
	private List<Criteria> criteria = new ArrayList<Criteria>();
	
	
	public Session(Class<T> clazz){
		this.clazz = clazz;
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
			Iterator critIterator = criteria.iterator();
			while(critIterator.hasNext()){
				query = query + critIterator.next();
				if(critIterator.hasNext())
					query = query + " AND ";
			}
		
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			/*Intento de hacer con Reflection para poder armar una clase directamente
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
			
			while(rs.next()){
				clazz.getConstructor(parameterTypes)
				rs.getMetaData().
				String name = rs.getString("nombre");
				String description = rs.getString("descripcion");
				Long code = rs.getLong("codigo");
				T object = new T(code, name, description);
				
				movies.add(movie);
			}
			 */
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
