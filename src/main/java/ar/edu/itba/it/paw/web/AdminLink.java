package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;


@SuppressWarnings("serial")
public class AdminLink extends Link<Object>{
	public static final MoviesWicketSession wicketSession = MoviesWicketSession.get();
	Class<? extends Page>Pageclazz;
	public AdminLink(String id, Class<? extends Page> Pageclazz) {
		super(id);
		this.Pageclazz = Pageclazz;
	}
	
	@Override
	public boolean isVisible(){
		if(wicketSession.isAdmin()){
			return true;
		}
		return false;
	}
	@Override
	public void onClick() {
			setResponsePage(Pageclazz);
	}

}
