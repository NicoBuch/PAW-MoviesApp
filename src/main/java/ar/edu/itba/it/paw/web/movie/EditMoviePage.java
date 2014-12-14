package ar.edu.itba.it.paw.web.movie;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValueConversionException;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.secured.AdminPage;

@SuppressWarnings("serial")
public class EditMoviePage extends AdminPage{
	@SpringBean
	static MovieRepo movies;
	public EditMoviePage(PageParameters params) throws StringValueConversionException, NoIdException{
		super();
		Movie movie = movies.get(params.get("movieId").toInteger());
		add(new FeedbackPanel("feedback"));
		Form<Movie> form = new Form<Movie>("editMovieForm", new CompoundPropertyModel<Movie>(
				new EntityModel<Movie>(Movie.class, movie.getId())));
		form.add(new EditMoviePanel("editMovie"));
		form.add(new Button("editMovieButton", new ResourceModel("editMovie")) {
			@Override
			public void onSubmit() {
				setResponsePage(ListMoviesPage.class);
			}
		});
		form.add(new Button("movieCancelButton", new ResourceModel("editMovie")) {
			@Override
			public void onSubmit() {
				setResponsePage(ListMoviesPage.class);
			}
		});
		form.setMultiPart(true);
		add(form);
	}
}
