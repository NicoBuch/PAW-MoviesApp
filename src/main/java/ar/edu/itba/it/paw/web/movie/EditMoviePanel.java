package ar.edu.itba.it.paw.web.movie;

import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MaximumValidator;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;


@SuppressWarnings("serial")
public class EditMoviePanel extends Panel {

	private static int MAX_DIRECTOR_LENGHT = 255;
	private static int MAX_TITLE_LENGHT = 255;
	private static int MAX_DURATION_LENGHT = 500;
	@SpringBean MovieRepo movies;
	@SpringBean GenreRepo genres;
	
	FileUploadField fileUpload;
	
	public EditMoviePanel(String id, IModel<?> model) {
		super(id, model);

		
	}
	
	public EditMoviePanel(String id){
		super(id);
		TextField<String> titleText = new TextField<String>("title");
		titleText.setRequired(true);
		titleText.add(StringValidator.maximumLength(MAX_TITLE_LENGHT));
		add(titleText);
		
		TextField<String> directorText = new TextField<String>("director");
		directorText.setRequired(true);
		directorText.add(StringValidator.maximumLength(MAX_DIRECTOR_LENGHT));
		add(directorText);
		
		DateTextField dtf = new DateTextField("releaseDate");
		dtf.setRequired(true);
		add(dtf);
//		add(new Label("movieReleaseDateName"));
		
		TextField<Integer> durationText = new NumberTextField<Integer>("minutes");
		durationText.add(new MinimumValidator<Integer>(0));
		durationText.add(new MaximumValidator<Integer>(MAX_DURATION_LENGHT));
		durationText.setRequired(true);
		add(durationText);
		
		add(new TextField<String>("description"));
		
		ListMultipleChoice<Genre> lmc = new ListMultipleChoice<Genre>("genres",new LoadableDetachableModel<List<Genre>>(){
			@Override
			protected List<Genre> load() {
				return genres.getAll();
			}
		}, genres.getAll());
		add(lmc);
		add(fileUpload = new FileUploadField("picture"));
		
	}



}
