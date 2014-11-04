package ar.edu.itba.it.paw.domain.comment;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.commentRating.CommentRating;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.report.Report;

@Repository
public class HibernateCommentRepo extends AbstractHibernateRepo implements
		CommentRepo {

	@Autowired
	public HibernateCommentRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public void delete(Comment comment) {
		super.delete(comment);
	}

	public Comment get(int id) throws NoIdException {
		try {
			return get(Comment.class, id);
		} catch (HibernateException e) {
			throw new NoIdException();
		}

	}

	public void save(Comment comment) {
		super.save(comment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByMovie(Movie movie) {
		Session session = getSession();

		Query query = session
				.createQuery(
						"select c from Comment c left outer join c.commentRatings ratings "
					  + "where c.movie = ? "
					  + "group by c "
					  + "order by avg(ratings.rating) desc")
				.setParameter(0, movie);
		List<Comment> list = query.list();
		return list;
	}

	@Override
	public void rate(CommentRating commentRating) {
		super.save(commentRating);
	}

	public void report(Report report){
		super.save(report);
	}

	@Override
	public List<Report> getByReports() {
		Session session = getSession();

		Query query = session
				.createQuery(
						"select c from Comment c join c.reports reports "
					  + "group by c "
					  + "order by count(reports) desc");

		List<Report> list = query.list();
		return list;
	}

}
