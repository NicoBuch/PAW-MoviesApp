package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class BaseLink<T> extends Link<T>{
	Class<? extends Page> clazz;
	PageParameters params;
	public BaseLink(String id, Class<? extends Page> clazz) {
		super(id);
		this.clazz = clazz;
	}
	public BaseLink(String id, Class<? extends Page> clazz, PageParameters params) {
		super(id);
		this.clazz = clazz;
		this.params = params;
	}

	@Override
	public void onClick() {
		setResponsePage(clazz, params);
	}

}
