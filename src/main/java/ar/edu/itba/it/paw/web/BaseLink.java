package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

@SuppressWarnings("serial")
public class BaseLink<T> extends Link<T>{
	Class<? extends Page> clazz;
	public BaseLink(String id, Class<? extends Page> clazz) {
		super(id);
		this.clazz = clazz;
	}

	@Override
	public void onClick() {
		setResponsePage(clazz);
	}

}
