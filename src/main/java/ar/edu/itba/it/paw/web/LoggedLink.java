package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

@SuppressWarnings("serial")
public class LoggedLink extends Link<Object>{
	public static final MoviesWicketSession wicketSession = MoviesWicketSession.get();
	Class<? extends Page>Pageclazz;
	boolean needUser, needAdmin, needVip;
	public LoggedLink(String id,boolean needUser, boolean needAdmin, boolean needVip,Class<? extends Page> Pageclazz) {
		super(id);
		this.needUser = needUser;
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.Pageclazz = Pageclazz;
	}
	
	@Override
	public boolean isVisible(){
		if( needUser && !wicketSession.isSignedIn() || !needUser && wicketSession.isSignedIn()){
			return false;
		}
		if( needAdmin && !wicketSession.isAdmin()){
			return false;
		}
		if( needVip && !wicketSession.isVip()){
			return false;
		}
		
		
		return true;
		
	}
	@Override
	public void onClick() {
			setResponsePage(Pageclazz);
	}

}