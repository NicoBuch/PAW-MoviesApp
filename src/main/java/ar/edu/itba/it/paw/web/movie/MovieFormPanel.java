package ar.edu.itba.it.paw.web.movie;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;


@SuppressWarnings("serial")
public class MovieFormPanel extends Panel {

	private static int MAX_DIRECTOR_LENGHT = 255;
	private static int MAX_TITLE_LENGHT = 255;
	private static int MAX_DURATION_LENGHT = 500;
	@SpringBean MovieRepo movies;
	@SpringBean GenreRepo genres;
	
	public MovieFormPanel(String id, IModel<?> model) {
		super(id, model);

		TextField<String> titleText = new TextField<String>("title");
		titleText.setRequired(true);
		titleText.add(StringValidator.maximumLength(MAX_TITLE_LENGHT));
		add(titleText);
//		add(new Label("movieTitleName"));
		
		TextField<String> directorText = new TextField<String>("director");
		titleText.setRequired(true);
		titleText.add(StringValidator.maximumLength(MAX_DIRECTOR_LENGHT));
		add(directorText);
//		add(new Label("movieDirectorName"));
		
		add(new DateTextField("birthdate"));
//		add(new Label("movieReleaseDateName"));
		
		TextField<Integer> durationText = new TextField<Integer>("minutes");
		durationText.add(new MinimumValidator<Integer>(0));
		durationText.setRequired(true);
//		add(new Label("movieDurationName"));
		
		add(new TextField<String>("description"));
//		add(new Label(""))
		
		DropDownChoice<Genre> dpc = new DropDownChoice<Genre>("genres", genres.getAll());
		add(dpc);
		
	}
	
	public MovieFormPanel(String id){
		super(id);
		
		
	}



}
