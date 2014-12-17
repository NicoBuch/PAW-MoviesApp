package ar.edu.itba.it.paw.web;

import java.util.Map;



import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class LoggedLink<T> extends Link<T>{
	private MoviesWicketSession wicketSession = MoviesWicketSession.get();
	boolean needUser, needAdmin, needVip;
	Class pageClazz;
	PageParameters parameters;
	public LoggedLink(String id,boolean needUser, boolean needAdmin, boolean needVip, Class<? extends Page> Pageclazz,
						PageParameters parameters) {
		super(id);
		this.parameters = parameters;
		this.needUser = needUser;
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.pageClazz = Pageclazz;
	}
	public LoggedLink(String id,boolean needUser, boolean needAdmin, boolean needVip,Class<? extends Page> Pageclazz,
			PageParameters parameters, IModel<T> model) {
		super(id,model);
		this.parameters = parameters;
		this.needUser = needUser;
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.pageClazz = Pageclazz;
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
			if(parameters == null){
				setResponsePage(pageClazz);
				return;
			}
			setResponsePage(pageClazz, parameters);
	}

}