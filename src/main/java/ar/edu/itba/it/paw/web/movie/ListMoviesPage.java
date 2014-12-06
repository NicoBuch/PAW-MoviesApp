package ar.edu.itba.it.paw.web.movie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ListMoviesPage extends BasePage{
	
	@SpringBean MovieRepo movies;
	
	public ListMoviesPage(){
		
		add(new RefreshingView<Movie>("movie") {
			@Override
			protected Iterator<IModel<Movie>> getItemModels() {
				List<IModel<Movie>> result = new ArrayList<IModel<Movie>>();
				for (Movie d : movies.getAll()) {
					result.add(new EntityModel<Movie>(Movie.class, d));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<Movie> item) {
//				item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
//				item.add(new Link<Movie>("delete", item.getModel()) {
//					@Override
//					public void onClick() {
//						try {
//							departments.remove(getModelObject());
//						} catch (ApplicationException e) {
//							error(getString(e.getClass().getSimpleName(), new Model<ApplicationException>(e)));
//						}
//					}
//				}.add(new Image("deleteImage", DemoWicketApp.DELETE_ICON)));
				
			}

		});
	}

}
