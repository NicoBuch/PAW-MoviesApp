package ar.edu.itba.it.paw.domain.report;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.comment.Comment;

@Repository
public class HibernateReportRepo extends AbstractHibernateRepo implements ReportRepo{
	
	@Autowired
	public HibernateReportRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
		
	}

	public void deleteByComment(Comment comment) {
		List<Report> reports = comment.getReports();
		while(reports.size() > 0){
			Report report = reports.get(0);
			reports.remove(0);
			super.delete(report);
		}
	}


}
