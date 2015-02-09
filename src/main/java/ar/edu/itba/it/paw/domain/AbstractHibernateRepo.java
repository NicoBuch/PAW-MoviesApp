package ar.edu.itba.it.paw.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public abstract class AbstractHibernateRepo {
	private final SessionFactory sessionFactory;

	public AbstractHibernateRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type, Serializable id) {
		return (T) getSession().get(type, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object... params) {
		Session session = getSession();

		Query query = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> list = query.list();
		return list;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(Object o) {
		Session session = getSession();
		Serializable id = session.save(o);
		session.flush();
		return id;
	}
	
	public void delete(Object o){
		Session session = getSession();
		session.delete(o);
		session.flush();
	}

}