package ar.edu.itba.it.paw.daos;

import java.util.List;

public interface GenericDao<T> {
	
		public T store(T obj);
		
		public T find(long id);
		
		public void delete(T obj);
		
		public List<T> findAll();
}