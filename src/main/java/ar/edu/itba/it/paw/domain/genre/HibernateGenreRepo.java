package ar.edu.itba.it.paw.domain.genre;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.NoIdException;

@Repository
public class HibernateGenreRepo extends AbstractHibernateRepo implements GenreRepo{
	
	@Autowired
	public HibernateGenreRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Genre> getAll() {
		return find("from Genre");
	}

	@Override
	public Genre get(int genreId) throws NoIdException {
		try {
			return get(Genre.class, genreId);
		} catch (HibernateException e) {
			throw new NoIdException();
		}
	}

	@Override
	public Genre getByName(String genre) {
		return (Genre) find("from Genre g where lower(g.name) like ?","%" + genre.toLowerCase() + "%").get(0);
	}

}
