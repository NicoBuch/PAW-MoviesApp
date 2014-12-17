package ar.edu.itba.it.paw.web.movie;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValueConversionException;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.secured.AdminPage;

@SuppressWarnings("serial")
public class EditMoviePage extends AdminPage{
	@SpringBean
	static MovieRepo movies;
	
	private transient String title;
	private transient String director;
	private transient Integer releaseDay;
	private transient Integer releaseMonth;
	private transient Integer releaseYear;
	private transient Set<Genre> genres;
	private transient int minutes;
	private transient String description;
	private transient EditMoviePanel panel;
	private transient FileUpload picture;
	
	private Movie movie;
	
	public EditMoviePage(PageParameters params) throws StringValueConversionException, NoIdException{
		super();
		movie = movies.get(params.get("movieId").toInteger());
		fillFields(movie);
		add(new FeedbackPanel("feedback"));
		Form<EditMoviePage> form = new Form<EditMoviePage>("editMovieForm", new CompoundPropertyModel<EditMoviePage>(
				this));
		form.add(new EditMoviePanel("editMovie"));
		form.add(new Button("editMovieButton", new ResourceModel("editMovie")) {
			@Override
			public void onSubmit() {
				byte[] moviePicture = picture.getBytes();
				String releaseDateString = releaseYear + "-" + releaseMonth + "-" + releaseDay;
				Date releaseDate = Date.valueOf(releaseDateString);
				if(releaseDate.after(new Date(System.currentTimeMillis()))){
					panel.releaseDayField.error((IValidationError) new ValidationError().addMessageKey("invalidDate"));
				}
				movie.setDescription(description);
				movie.setDirector(director);
				movie.setGenres(genres);
				movie.setMinutes(minutes);
				movie.setPicture(moviePicture);
				movie.setReleaseDate(releaseDate);
				movie.setTitle(title);
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
	
	private void fillFields(Movie movie){
		title = movie.getTitle();
		director = movie.getDirector();
		releaseDay = movie.getReleaseDate().getDay();
		releaseMonth = movie.getReleaseDate().getMonth();
		releaseYear = movie.getReleaseDate().getYear();
		genres = movie.getGenres();
		minutes = movie.getMinutes();
		description = movie.getDescription();
	}
}
