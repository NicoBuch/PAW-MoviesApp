package ar.edu.itba.it.paw.web;

import org.apache.wicket.markup.html.basic.Label;

@SuppressWarnings("serial")
public class ConditionalLabel extends Label{
	boolean conditional;
	public ConditionalLabel(String id, String label, boolean conditional) {
		super(id, label);
		this.conditional = conditional;
	}
	@Override
	public boolean isVisible(){
		if(conditional){
			return true;
		}
		return false;
	}

}
