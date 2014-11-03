package ar.edu.itba.it.paw.domain.report;

import ar.edu.itba.it.paw.domain.comment.Comment;



public interface ReportRepo {
	public void deleteByComment(Comment comment);
}
