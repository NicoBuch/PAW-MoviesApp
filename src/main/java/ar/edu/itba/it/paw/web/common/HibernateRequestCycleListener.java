package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.request.handler.RenderPageRequestHandler;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;
import org.springframework.util.Assert;

import ar.edu.itba.it.paw.web.email.EmailSender;
import ar.edu.itba.it.paw.web.error.ErrorPage;

public class HibernateRequestCycleListener extends AbstractRequestCycleListener {

  private final SessionFactory sessionFactory;
  private static ThreadLocal<Boolean> error = new ThreadLocal<Boolean>();

  public HibernateRequestCycleListener(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void onBeginRequest(RequestCycle cycle) {
    error.set(false);
    Assert.state(!ManagedSessionContext.hasBind(sessionFactory), "Session already bound to this thread");
    Session session = sessionFactory.openSession();
    ManagedSessionContext.bind(session);
    session.beginTransaction();
  }

  @Override
  public void onEndRequest(RequestCycle cycle) {
    if (!error.get()) {
      commit();
    } else {
      rollback();
    }
  }

  @Override
  public IRequestHandler onException(RequestCycle cycle, Exception ex) {
    rollback();
    error.set(true);
    EmailSender emailSender = new EmailSender();
   	emailSender.sendEmail("Error" + ex.toString(), ex.getMessage());
   	return new RenderPageRequestHandler(new PageProvider(ErrorPage.class));
  }

  private void commit() {
    Session session = sessionFactory.getCurrentSession();
    Assert.state(session.isOpen(), "Can't commit a closed session!");
    try {
      Transaction tx = session.getTransaction();
      if (tx.isActive()) {
        session.flush();
        tx.commit();
      }
    } finally {
      close(session);
    }
  }

  private void rollback() {
    Session session = sessionFactory.getCurrentSession();
    Assert.state(session.isOpen(), "Can't rollback a closed session!");
    try {
      Transaction tx = session.getTransaction();
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      close(session);
    }
  }

  private void close(Session session) {
    ManagedSessionContext.unbind(sessionFactory);
    session.close();
  }
}
