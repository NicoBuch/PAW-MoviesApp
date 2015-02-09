package ar.edu.itba.it.paw.web;

import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public class ConditionalFormData<T,S> extends ConditionalForm<T> {

	public IModel<S> data;

	public ConditionalFormData(String id,IModel<T> model,boolean needUser, boolean needAdmin, boolean needVip, IModel<S> data){
		super(id, model,needUser,needAdmin,needVip);
		this.data = data;
	}
	

}