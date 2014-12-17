package ar.edu.itba.it.paw.web.movie;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class AddMoviePage extends BasePage{
	@SpringBean private MovieRepo movies;

	private transient String title;
	private transient String director;
	private transient Date releaseDate;
	private transient Set<Genre> genres;
	private transient int minutes;
	private transient String description;
	private transient byte[] picture;
	private transient SortedSet<Comment> comments = new TreeSet<Comment>();
	
	public AddMoviePage() {
		
		add(new FeedbackPanel("feedback"));

//		Form<AddMoviePage> form = new Form<AddMoviePage>("form", new CompoundPropertyModel<AddSubjectPage>(this));
//		form.add(new SubjectInfoPanel("subjectsInfo"));
//		form.add(new Button("save", new ResourceModel("save")) {
//			@Override
//			public void onSubmit() {
//				try {
//					Subject subject = new Subject(code, name, credits, department, annual, contents);
//					for (Professor p : professors) {
//						subject.addProfessor(p);
//					}
//					subjects.add(subject);
//					setResponsePage(ListSubjectsPage.class);
//				} catch (ApplicationException e) {
//					error(getString(e.getClass().getSimpleName(), new Model<ApplicationException>(e)));
//				}
//			}
//		});
//		add(form);
	}
}
