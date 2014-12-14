package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class UserDetailPage extends BasePage{
	@SpringBean UserRepo users;
	private transient User user;
	private transient MoviesWicketSession session = MoviesWicketSession.get();
	private transient boolean isInterest = false;
	
	public UserDetailPage(int id) throws NoIdException {
			
		user = users.get(id);
		add(new Label("fullName", user.getFullName()));
		add(new Label("firstName", user.getFirstName()));
		if(session.isSignedIn() && session.getUser().getUsersOfInterest().contains(user)){
			isInterest = true;
		}
		Form<UserDetailPage> unfollowform = new Form<UserDetailPage>("removeUserOfInterest", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				session.getUser().removeUserOfInterest(user);
			}
			@Override
			public boolean isVisible() {
				return isInterest;
			};
		};
		add(unfollowform);
		Form<UserDetailPage> followform = new Form<UserDetailPage>("addUserOfInterest", new CompoundPropertyModel<UserDetailPage>(this)) {
			@Override
			protected void onSubmit() {
				session.getUser().addUserOfInterest(user);
			}
			@Override
			public boolean isVisible() {
				return isInterest;
			};
		};
		add(followform);
		
		add(new RefreshingView<Comment>("comment") {
			@Override
			protected Iterator<IModel<Comment>> getItemModels() {
				List<IModel<Comment>> result = new ArrayList<IModel<Comment>>();
				SortedSet<Comment> comments = user.getComments();
				for (Comment comment : comments) {
					result.add(new EntityModel<Comment>(Comment.class, comment));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<Comment> item) {
				Map<String, Comment> params = new HashMap<String, Comment>();
				params.put("commentModel", item.getModelObject());
				item.add(new Label(("creationDate"), new PropertyModel<String>(item.getModel(), "creationDate")));
				item.add(new BaseLink<Void>("movieInfoLink", UserDetailPage.class).add(new Label("movieName", new PropertyModel<String>(item.getModelObject().getMovie(), "title"))));
			}

		});
	}
	
}
