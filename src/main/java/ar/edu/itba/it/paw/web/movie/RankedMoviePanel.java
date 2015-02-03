package ar.edu.itba.it.paw.web.movie;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.w3c.dom.stylesheets.LinkStyle;

import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.web.BaseLink;

@SuppressWarnings("serial")
public class RankedMoviePanel extends Panel{

	public RankedMoviePanel(String id, IModel<Movie> movieModel) {
		super(id);
		PageParameters params = new PageParameters();
		params.add("movieId", movieModel.getObject().getId());
		WebMarkupContainer icon = new WebMarkupContainer("icon");
		icon.setVisible(movieModel.getObject().getAvgRating() > 4);
		add(new BaseLink<Integer>("movieDetailLink", ViewMoviePage.class,params).add(new Label("title", new PropertyModel<String>(movieModel, "title")))
		.add(icon));
		
	}
	

}
