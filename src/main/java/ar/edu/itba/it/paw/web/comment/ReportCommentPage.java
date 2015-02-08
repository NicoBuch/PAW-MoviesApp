package ar.edu.itba.it.paw.web.comment;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValueConversionException;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.movie.ViewMoviePage;

@SuppressWarnings("serial")
public class ReportCommentPage extends BasePage{

	@SpringBean
	private UserRepo users;
	@SpringBean
	private CommentRepo comments;
	
	private Comment comment; 

	private transient String explanation;
	
	public ReportCommentPage( PageParameters params) throws StringValueConversionException, NoIdException {
		this.comment = comments.get(params.get("commentId").toInt());
		Form<ReportCommentPage> form = new Form<ReportCommentPage>("reportForm", new CompoundPropertyModel<ReportCommentPage>(this)) {
			@Override
			protected void onSubmit() {
				try {
					user = users.getByEmail(MoviesWicketSession.get().getEmail());
				} catch (EmailNotFound e) {
					throw new RuntimeException();
				}
				user.report(new Report(user, comment, explanation));
				PageParameters movieParams = new PageParameters();
				movieParams.add("movieId", comment.getMovie().getId());
				setResponsePage(ViewMoviePage.class, movieParams);
			}
		};

		form.add(new TextArea<String>("explanation").setRequired(true));
		form.add(new Button("report", new ResourceModel("report")));
		add(form);
	}
}
