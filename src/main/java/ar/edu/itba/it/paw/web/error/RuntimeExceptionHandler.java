package ar.edu.itba.it.paw.web.error;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.request.IRequestHandler;

@SuppressWarnings("serial")
public abstract class RuntimeExceptionHandler extends Behavior{
	public abstract IRequestHandler handleRuntimeException(Component component, Exception e);
}
