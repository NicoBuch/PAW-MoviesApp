package ar.edu.itba.it.paw.web;

import org.apache.wicket.model.IModel;


@SuppressWarnings("serial")
public abstract class DeleteLink<T> extends LoggedLink<T>{
	public DeleteLink(String id, boolean needUser, boolean needAdmin,
			boolean needVip, IModel<T> model) {
		super(id, needUser, needAdmin, needVip, null,null, model);

	}
	
	public abstract void onClick(); 
	
}
