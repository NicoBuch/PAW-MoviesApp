package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

public class MoviesWicketSession extends WebSession {

  private String email;

  public static MoviesWicketSession get() {
    return (MoviesWicketSession) Session.get();
  }

  public MoviesWicketSession(Request request) {
    super(request);
  }

  public String getEmail() {
    return email;
  }

  public boolean signIn(String email, String password, UserRepo users) throws EmailNotFound {
    User user = users.getByEmail(email);
    if (user != null && user.checkPassword(password)) {
      this.email = email;
      return true;
    }
    return false;
  }

  public boolean isSignedIn() {
    return email != null;
  }

  public void signOut() {
        invalidate();
        clear();
  }
}
