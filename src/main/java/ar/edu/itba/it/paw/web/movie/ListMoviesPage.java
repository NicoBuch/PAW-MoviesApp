package ar.edu.itba.it.paw.web.movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.extensions.markup.html.form.select.IOptionRenderer;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOptions;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
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
	public ListMoviesPage(){
		this(null,null);
	}
	public ListMoviesPage(final String genre, final String director){
		try{
		Form<ListMoviesPage> form = new Form<ListMoviesPage>("filterByDirectorForm",
															new CompoundPropertyModel<ListMoviesPage>(this)) {
			@Override
			protected void onSubmit() {
				setResponsePage(new ListMoviesPage(null, ListMoviesPage.this.director));
				return;
			}
		};
//		List<Genre> genreList = genres.getAll();
//		add(Select("select", selectionModel);
//		 SelectOptions options = new SelectOptions("options", new EntityModel<Genre>(Genre.class), new  IOptionRenderer());
//		 
		form.add(new TextField<String>("director"));
		form.add(new Button("filterByDirector", new ResourceModel("filterByDirector")));
		add(new RefreshingView<Movie>("movie") {
			@Override
			protected Iterator<IModel<Movie>> getItemModels() {
				List<IModel<Movie>> result = new ArrayList<IModel<Movie>>();
				List<Movie> mvs;
				if(genre != null){
					mvs = movies.getByGenre(new Genre(genre));
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
				Map<String, Movie> params = new HashMap<String, Movie>();
				params.put("movieModel", item.getModelObject());
				item.add(new Label(("title"), new PropertyModel<String>(item.getModel(), "title")));
				item.add(new Label(("director"), new PropertyModel<String>(item.getModel(), "director")));
				item.add(new Label(("releaseDate"), new PropertyModel<Date>(item.getModel(), "releaseDate")));
				item.add(new LoggedLink<Movie>("edit", true, true, false , EditPage.class, params));
				item.add(new DeleteLink<Movie>("deleteMovieLink", true, true, false, item.getModel()) {
					@Override
					public void onClick() {
						movies.delete(getModelObject());
					}
				});
			}

		});
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
