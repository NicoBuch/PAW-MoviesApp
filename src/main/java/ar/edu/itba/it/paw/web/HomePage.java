package ar.edu.itba.it.paw.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class HomePage extends BasePage{
	@SpringBean MovieRepo movies;
	@SpringBean UserRepo users;
	private final int TOP_RANKED_CANT = 5;
	private final int MOST_RECENT_CANT = 5;
	private final int MOST_VISITED_CANT = 5;
	private transient Date now;
	private transient Date aWeekBefore;
	
	public HomePage(){
		now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, -6);
		aWeekBefore = new Date(c.getTimeInMillis());
		
		IModel<List<User>> usersOfInterest = new LoadableDetachableModel<List<User>>() {
			@Override
			protected List<User> load() {
				MoviesWicketSession session = MoviesWicketSession.get();
				if(!session.isSignedIn()){
					return new ArrayList<User>();
				}
				try {
					return users.get(session.getUserId()).getUsersOfInterest();
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
					return null;
				}
			}
		};
		
		IModel<List<Movie>> ranked = new LoadableDetachableModel<List<Movie>>() {
			@Override
			protected List<Movie> load() {
				return movies.getByRating(TOP_RANKED_CANT);
			}
		}; 
		
		IModel<List<Movie>> visited = new LoadableDetachableModel<List<Movie>>() {
			@Override
			protected List<Movie> load() {
				return movies.getByVisits(MOST_VISITED_CANT);
			}
		};
		IModel<List<Movie>> recents = new LoadableDetachableModel<List<Movie>>() {
			@Override
			protected List<Movie> load() {
				return movies.getByCreationDate(MOST_RECENT_CANT);
			}
		};

		IModel<List<Movie>> releases = new LoadableDetachableModel<List<Movie>>() {

			@Override
			protected List<Movie> load() {
				return movies.getByReleaseDate(aWeekBefore, now);				
			}
			
		};
		add(new PropertyListView<Movie>("releases", releases){
			@Override
			protected void populateItem(ListItem<Movie> item) {
				item.add(new BaseLink<Void>("movieDetailLink", HomePage.class).add(new Label("title", new PropertyModel<String>(item.getModel(), "title"))).add(new Label("shortDescription", new PropertyModel<String>(item.getModel(), "shortDescription"))));
			}
		}.setVisible(!releases.getObject().isEmpty()));
		add(new WebMarkupContainer("noReleases").setVisible(releases.getObject().isEmpty()));
		
		add(new PropertyListView<Movie>("rankedMovies", ranked){
			@Override
			protected void populateItem(ListItem<Movie> item) {
				item.add(new BaseLink<Void>("movieDetailLink", HomePage.class).add(new Label("title", new PropertyModel<String>(item.getModel(), "title"))));
			}
		}.setVisible(!ranked.getObject().isEmpty()));
		add(new WebMarkupContainer("noRankedMovies").setVisible(ranked.getObject().isEmpty()));
		
		add(new PropertyListView<Movie>("recentMovies", recents){
			@Override
			protected void populateItem(ListItem<Movie> item) {
				item.add(new BaseLink<Void>("movieDetailLink", HomePage.class).add(new Label("title", new PropertyModel<String>(item.getModel(), "title")))
				.add(new Label("uploadDate", String.format(getString("uploadDate"), item.getModelObject().getCreationDate())))
				.add(new Label("commentsCount", String.format(getString("commentsCount"), item.getModelObject().getCommentCount()))));
				
			}
		}.setVisible(!recents.getObject().isEmpty()));
		add(new WebMarkupContainer("noRecents").setVisible(recents.getObject().isEmpty()));
		
		add(new PropertyListView<Movie>("visitedMovies", visited){
			@Override
			protected void populateItem(ListItem<Movie> item) {
				item.add(new BaseLink<Void>("movieDetailLink", HomePage.class).add(new Label("title", new PropertyModel<String>(item.getModel(), "title"))));
			}
		}.setVisible(!visited.getObject().isEmpty()));
		add(new WebMarkupContainer("noVisitedMovies").setVisible(visited.getObject().isEmpty()));
		
		add(new WebMarkupContainer("usersOfInterestDiv").add(new PropertyListView<User>("usersOfInterest", usersOfInterest){
			@Override
			protected void populateItem(ListItem<User> item) {
				item.add(new BaseLink<Void>("userDetailLink", HomePage.class).add(new Label("email", new PropertyModel<String>(item.getModel(), "email"))));
				item.add(new PropertyListView<Comment>("comments", new PropertyModel<List<Comment>>(item.getModel(), "usersOfInterest")){

					@Override
					protected void populateItem(ListItem<Comment> item) {
						item.add(new Label("body", new PropertyModel<String>(item.getModel(), "body")));
						item.add(new BaseLink<Void>("movieDetailLink", HomePage.class).add(new Label("title", new PropertyModel<String>(new EntityModel<Movie>(Movie.class, item.getModelObject().getMovie()), "title"))));
						item.setVisible(item.getModelObject().getCreationDate().after(aWeekBefore));
					}
				});
				item.add(new WebMarkupContainer("noComments").setVisible(item.getModelObject().getUsersOfInterest().isEmpty()));				
			}
		}.setVisible(!usersOfInterest.getObject().isEmpty()))
			.add(new WebMarkupContainer("noUsers").setVisible(usersOfInterest.getObject().isEmpty()))
		.setVisible(MoviesWicketSession.get().isSignedIn()));
			
	}
}
