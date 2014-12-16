package ar.edu.itba.it.paw.web.movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.DeleteLink;
import ar.edu.itba.it.paw.web.LoggedLink;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ListMoviesPage extends BasePage{
	@SpringBean MovieRepo movies;
	@SpringBean GenreRepo genres;
	private transient String director;
	private transient String genre;
	public ListMoviesPage(){
		this(null,null);
	}
	public ListMoviesPage(final String genre, final String director){
		Form<ListMoviesPage> form = new Form<ListMoviesPage>("filterByDirectorForm",
															new CompoundPropertyModel<ListMoviesPage>(this)) {
			@Override
			protected void onSubmit() {
				setResponsePage(new ListMoviesPage(null, ListMoviesPage.this.director));
				return;
			}
		};
		Form<ListMoviesPage> formGenre = new Form<ListMoviesPage>("filterByGenreForm",
															new CompoundPropertyModel<ListMoviesPage>(this)){
			@Override
			protected void onSubmit() {
				setResponsePage(new ListMoviesPage(ListMoviesPage.this.genre, null));
				return;
			}
		};

		DropDownChoice<Genre> dpc = new DropDownChoice<Genre>("genre", genres.getAll());
		formGenre.add(dpc);
		formGenre.add(new Button("filterByGenre", new ResourceModel("filterByGenre")));
		form.add(new TextField<String>("director"));
		form.add(new Button("filterByDirector", new ResourceModel("filterByDirector")));
		add(form);
		add(formGenre);
		add(new LoggedLink<Void>("addMovieLink", true, true, false, AddMoviePage.class, null));
		add(new RefreshingView<Movie>("movie") {
			@Override
			protected Iterator<IModel<Movie>> getItemModels() {
				List<IModel<Movie>> result = new ArrayList<IModel<Movie>>();
				List<Movie> mvs;
				if(genre != null){
					mvs = movies.getByGenre(genres.getByName(genre));
				}
				else if(director != null){
					mvs = movies.getByDirector(director);
				}
				else{
					mvs = movies.getAll();
				}
				for (Movie d : mvs) {
					result.add(new EntityModel<Movie>(Movie.class, d));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<Movie> item) {
				Map<String, Integer> params = new HashMap<String, Integer>();
				params.put("movieId", item.getModelObject().getId());
				RankedMoviePanel moviePanel = new RankedMoviePanel("title", item.getModel());
				item.add(moviePanel);
				item.add(new Label(("director"), new PropertyModel<String>(item.getModel(), "director")));
				item.add(new Label(("releaseDate"), new PropertyModel<Date>(item.getModel(), "releaseDate")));
				item.add(new LoggedLink<Integer>("editMovieLink", true, true, false , EditMoviePage.class, params));
				item.add(new DeleteLink<Movie>("deleteMovieLink", true, true, false, item.getModel()) {
					@Override
					public void onClick() {
						movies.delete(getModelObject());
					}
				});
			}

		});
	}

}
