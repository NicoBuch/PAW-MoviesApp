package ar.edu.itba.it.paw.web.movie;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
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
	protected FormComponent<Integer> releaseDayField;
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
		
		List<Integer> days = new ArrayList<Integer>();
		for(int i = 1; i<= 31; i++){
			days.add(i);
		}
		releaseDayField = new DropDownChoice<Integer>("releaseDay", days).setRequired(true);
		add(releaseDayField);
		List<Integer> months = new ArrayList<Integer>();
		for(int i = 1; i<= 12; i++){
			months.add(i);
		}
		add(new DropDownChoice<Integer>("releaseMonth", months).setRequired(true));
		List<Integer> years = new ArrayList<Integer>();
		for(int i = 1950; i<= 2014; i++){
			years.add(i);
		}
		add(new DropDownChoice<Integer>("releaseYear", years).setRequired(true));
		
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
