package ar.edu.itba.it.paw.domain.prize;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.NoIdException;

@Repository
public class HibernatePrizeRepo extends AbstractHibernateRepo implements PrizeRepo{

	@Autowired
	public HibernatePrizeRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Prize get(int id) throws NoIdException {
		try {
			return get(Prize.class, id);
		} catch (HibernateException e) {
			throw new NoIdException();
		}
	}
	
	@Override
	public void delete(Prize prize) {
		super.delete(prize);
	}

}
