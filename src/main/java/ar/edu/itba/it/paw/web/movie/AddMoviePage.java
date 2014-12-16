package ar.edu.itba.it.paw.web.movie;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
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
	private transient List<Genre> genres;
	private transient int minutes;
	private transient String description;
	private transient EditMoviePanel panel;
	
	public AddMoviePage(){
		add(new FeedbackPanel("feedback"));
		Form<AddMoviePage> form = new Form<AddMoviePage>("editMovieForm", new CompoundPropertyModel<AddMoviePage>(this));
		form.setMultiPart(true);
		form.add(panel = new EditMoviePanel("editMovie"));
		form.add(new Button("editMovieButton", new ResourceModel("editMovieButton")) {
			@Override
			public void onSubmit(){
//				final FileUpload picture = panel.fileUpload.getFileUpload();
				List<FileUpload> file = panel.picture.getFileUploads();
				byte[] moviePicture = file.get(0).getBytes();
				String releaseDateString = releaseYear + "-" + releaseMonth + "-" + releaseDay;
				Date releaseDate = Date.valueOf(releaseDateString);
				if(releaseDate.after(new Date(System.currentTimeMillis()))){
					panel.releaseDayField.error((IValidationError) new ValidationError().addMessageKey("invalidDate"));
				}
				Set<Genre> genr = new HashSet<Genre>(genres);
				Movie movie = new Movie(title, releaseDate, director, genr, minutes, description);
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
		add(form);
	}
	
}
