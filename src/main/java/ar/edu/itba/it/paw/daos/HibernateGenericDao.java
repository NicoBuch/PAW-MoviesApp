package ar.edu.itba.it.paw.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateGenericDao<T> implements GenericDao<T>{

	private Class<T> domainClass = getDomainClass();
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getDomainClass() {
	    if (domainClass == null) {
	    	ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
	        domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
	    }
	    return domainClass;
	}
	
	public void delete(T obj) {
		Session session = getSession();
		session.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public T find(long id) {
		Session session = getSession();
		T result = (T) session.get(domainClass, id);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = getSession();
		List<T> result = (List<T>) session.createQuery("from " + domainClass.getSimpleName()).list();
		return result;
	}
	
	public T store(T obj) {
		Session session = getSession();
		session.save(obj);
		return obj;
	}
}
