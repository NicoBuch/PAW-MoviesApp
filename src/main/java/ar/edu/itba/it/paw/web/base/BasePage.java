package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import ar.edu.itba.it.paw.web.MoviesWicketSession;

public class BasePage extends WebPage {

  public BasePage() {
	add(new Link<Void>("homeLink"){
		@Override
		public void onClick(){
			setResponsePage(new BasePage()); //cambiar por la pagina de perfil
		}
	});
	add(new Link<Void>("resetPasswordLink"){
		@Override
		public void onClick(){
			setResponsePage(new BasePage()); //cambiar por la pagina de perfil
		}
	});
	add(new Link<Void>("signOutLink"){
		@Override
		public void onClick(){
			setResponsePage(new BasePage()); //cambiar por la pagina de perfil
		}
	});
	add(new Link<Void>("myProfileLink"){
		@Override
		public void onClick(){
			setResponsePage(new BasePage()); //cambiar por la pagina de perfil
		}
	});
	
    add(new Label("username", new PropertyModel<String>(MoviesWicketSession.get(), "username")));
  }
}
