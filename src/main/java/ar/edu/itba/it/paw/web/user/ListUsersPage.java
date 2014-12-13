package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ListUsersPage extends BasePage{
	@SpringBean UserRepo users;
	private transient MoviesWicketSession session = MoviesWicketSession.get();
	
	public ListUsersPage(){
		if (!session.isSignedIn()) {
			redirectToInterceptPage(new SignInPage());
			
		}
		add(new RefreshingView<User>("users") {
			@Override
			protected Iterator<IModel<User>> getItemModels() {
				List<IModel<User>> result = new ArrayList<IModel<User>>();
				List<User> usrs = users.getAll();
				for (User usr : usrs) {
					result.add(new EntityModel<User>(User.class, usr));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<User> item) {
				Map<String, User> params = new HashMap<String, User>();
				params.put("userModel", item.getModelObject());
				item.add(new Label(("firstName"), new PropertyModel<String>(item.getModel(), "firstName")));
				item.add(new Label(("lastName"), new PropertyModel<String>(item.getModel(), "lastName")));
				IModel<User> model= new EntityModel<User>(User.class, item.getModelObject());
				item.add(new Link<User>("emailLink", model) {
					
					@Override
					public void onClick() {
						
						try {
							setResponsePage(new UserDetailPage(this.getModelObject().getId()));
						} catch (NoIdException e) {
							// TODO Auto-generated catch block manejar excepcion
							e.printStackTrace();
						} 
					}
				}.add(new Label(("email"), new PropertyModel<String>(item.getModel(), "email"))));
						
			}

		});
	}
}
