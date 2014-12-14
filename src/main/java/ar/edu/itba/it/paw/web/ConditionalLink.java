package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

@SuppressWarnings("serial")
public class ConditionalLink extends Link<Object>{
	public static final MoviesWicketSession wicketSession = MoviesWicketSession.get();
	Class<? extends Page>Pageclazz;
	boolean condition;
	public ConditionalLink(String id, boolean condition, Class<? extends Page> Pageclazz) {
		super(id);
		this.Pageclazz = Pageclazz;
		this.condition = condition;
	}
	
	@Override
	public boolean isVisible(){
		if(condition){
			return true;
		}
		return false;
	}
	@Override
	public void onClick() {
			setResponsePage(Pageclazz);
	}

}
