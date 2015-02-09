package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

@SuppressWarnings("serial")
public class MoviesWicketSession extends WebSession {

  IModel<User> userModel = new EntityModel<User>(User.class); 

  public static MoviesWicketSession get() {
    return (MoviesWicketSession) Session.get();
  }

  public MoviesWicketSession(Request request) {
    super(request);
  }

  public String getEmail() {
	User user = userModel.getObject();
	if(user != null){
		return user.getEmail();
	}
	return null;
  }

  public boolean signIn(String email, String password, UserRepo users) throws EmailNotFound {
    User user = users.getByEmail(email);
    if (user != null && user.checkPassword(password)) {
    	userModel.setObject(user);
    	return true;
    }
    return false;
  }

  public boolean isSignedIn() {
    return userModel.getObject() != null;
  }

  public void signOut() {
	  userModel.detach();
	  invalidate();
	  clear();
  }
  
  public boolean isAdmin(){
	  return userModel.getObject().isAdmin();
  }
  
  public boolean isVip(){
	  return userModel.getObject().isVip();
  }
  
  public IModel<User> getUserModel(){
	  return userModel;
  }
}
