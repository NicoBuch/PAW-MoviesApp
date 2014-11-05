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

	@SuppressWarnings("unchecked")
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
