package ar.edu.itba.it.paw.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class LoggedLink<T> extends Link<T>{
	public static final MoviesWicketSession wicketSession = MoviesWicketSession.get();
	Class<? extends Page>Pageclazz;
	boolean needUser, needAdmin, needVip;
	Map<String,T> parameters;
	public LoggedLink(String id,boolean needUser, boolean needAdmin, boolean needVip, Class<? extends Page> Pageclazz,
						Map<String,T> parameters) {
		super(id);
		this.parameters = parameters;
		this.needUser = needUser;
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.Pageclazz = Pageclazz;
	}
	public LoggedLink(String id,boolean needUser, boolean needAdmin, boolean needVip,Class<? extends Page> Pageclazz,
			Map<String,T> parameters, IModel<T> model) {
		super(id,model);
		this.parameters = parameters;
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
			PageParameters pageParameters = new PageParameters();
			if(parameters == null){
				setResponsePage(Pageclazz);
				return;
			}
			Set<Entry<String,T>> params = parameters.entrySet();
			for(Entry<String,T> entry : params){
				pageParameters.add(entry.getKey(), entry.getValue());
			}
			setResponsePage(Pageclazz, pageParameters);
	}

}