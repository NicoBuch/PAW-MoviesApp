package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.movie.RankedMoviePanel;

@SuppressWarnings("serial")
public class UserDetailPage extends BasePage{
	@SpringBean UserRepo users;
	private transient MoviesWicketSession session = MoviesWicketSession.get();

	public UserDetailPage(final int id) throws NoIdException {
		final IModel<User> user = new LoadableDetachableModel<User>() {
			@Override
			protected User load() {
				try {
					return users.get(id);
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar exepcion
					e.printStackTrace();
					return null;
				}
			}
			@Override
			public void detach() {
				setObject(null);
			}
		};
		add(new Label("fullName", user.getObject().getFullName()));
		add(new Label("firstName", String.format(getString("pageDesc"), user.getObject().getFirstName())));
		Form<UserDetailPage> unfollowform = new Form<UserDetailPage>("removeUserOfInterest", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				try {
					users.get(session.getUserId()).removeUserOfInterest(user.getObject());
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
				}
			}
			@Override
			public boolean isVisible() {
				try {
					return session.isSignedIn() && users.get(session.getUserId()).getUsersOfInterest().contains(user.getObject());
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
					return false;
				}
			};
		};
		unfollowform.add(new Button("unfollow", new ResourceModel("unfollow")));
		add(unfollowform);
		Form<UserDetailPage> followform = new Form<UserDetailPage>("addUserOfInterest", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				try {
					users.get(session.getUserId()).addUserOfInterest(user.getObject());
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
				}
			}
			@Override
			public boolean isVisible() {
				try {
					return session.isSignedIn() && !users.get(session.getUserId()).getUsersOfInterest().contains(user.getObject());
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
					return false;
				}
			};
		};
		followform.add(new Button("follow", new ResourceModel("follow")));
		add(followform);

		Form<UserDetailPage> blockForm = new Form<UserDetailPage>("blockUser", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				try {
					users.get(id).setBlocked(true);
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
				}
			}
			@Override
			public boolean isVisible() {
				try {
					return session.isAdmin() && !users.get(id).isBlocked();
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
					return false;
				}
			}
		};
		blockForm.add(new Button("block"));
		add(blockForm);

		Form<UserDetailPage> unblockForm = new Form<UserDetailPage>("unblockUser", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				try {
					users.get(id).setBlocked(false);
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
				}
			}
			@Override
			public boolean isVisible() {
				try {
					return session.isAdmin() && users.get(id).isBlocked();
				} catch (NoIdException e) {
					// TODO Auto-generated catch block manejar excepcion
					e.printStackTrace();
					return false;
				}
			}
		};
		unblockForm.add(new Button("unblock"));
		add(unblockForm);

		add(new RefreshingView<Comment>("comment") {
			@Override
			protected Iterator<IModel<Comment>> getItemModels() {
				List<IModel<Comment>> result = new ArrayList<IModel<Comment>>();
				SortedSet<Comment> comments = user.getObject().getComments();
				for (Comment comment : comments) {
					result.add(new EntityModel<Comment>(Comment.class, comment));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<Comment> item) {
				RankedMoviePanel moviePanel = new RankedMoviePanel("movieName", new EntityModel<Movie>(Movie.class, item.getModelObject().getMovie()));
				item.add(new Label(("creationDate"), new PropertyModel<String>(item.getModel(), "creationDate")));
				item.add(new BaseLink<Void>("movieInfoLink", UserDetailPage.class).add(moviePanel));
			}

		});
	}

}
