package ar.edu.itba.it.paw.web.movie;

import java.sql.Date;
import java.util.Set;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;

import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.secured.AdminPage;

@SuppressWarnings("serial")
public class AddMoviePage extends AdminPage{
	@SpringBean private MovieRepo movies;

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
	
	public AddMoviePage(){
		add(new FeedbackPanel("feedback"));
		Form<AddMoviePage> form = new Form<AddMoviePage>("editMovieForm", new CompoundPropertyModel<AddMoviePage>(this));
		form.add(panel = new EditMoviePanel("editMovie"));
		form.add(new Button("editMovieButton", new ResourceModel("editMovieButton")) {
			@Override
			public void onSubmit(){
//				final FileUpload picture = panel.fileUpload.getFileUpload();
				byte[] moviePicture = picture.getBytes();
				String releaseDateString = releaseYear + "-" + releaseMonth + "-" + releaseDay;
				Date releaseDate = Date.valueOf(releaseDateString);
				if(releaseDate.after(new Date(System.currentTimeMillis()))){
					panel.releaseDayField.error((IValidationError) new ValidationError().addMessageKey("invalidDate"));
				}
				Movie movie = new Movie(title, releaseDate, director, genres, minutes, description);
				movie.setPicture(moviePicture);
				movies.save(movie);
				setResponsePage(ListMoviesPage.class);
			}
		});
		form.add(new Button("movieCancelButton", new ResourceModel("movieCancelButton")) {
			@Override
			public void onSubmit() {
				setResponsePage(ListMoviesPage.class);
			}
		});
		form.setMultiPart(true);
		add(form);
	}
	
}
