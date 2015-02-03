package ar.edu.itba.it.paw.web;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public class ConditionalForm<T> extends Form<T> {

	private boolean needAdmin, needVip, needUser;
	private MoviesWicketSession wicketSession = MoviesWicketSession.get();
	public ConditionalForm(String id,boolean needUser, boolean needAdmin, boolean needVip){
		super(id);
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.needUser = needUser;
	}
	public ConditionalForm(String id,IModel<T> model,boolean needUser, boolean needAdmin, boolean needVip){
		super(id, model);
		this.needAdmin = needAdmin;
		this.needVip = needVip;
		this.needUser = needUser;
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

}
