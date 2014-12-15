package ar.edu.itba.it.paw.web.comment;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.user.SignInPage;

@SuppressWarnings("serial")
public class ReportedCommentsList extends BasePage{
	@SpringBean CommentRepo comments;

	public ReportedCommentsList(){
		MoviesWicketSession session = MoviesWicketSession.get();
		if (!(session.isSignedIn() && session.isAdmin())) {
			redirectToInterceptPage(new SignInPage());
		}
		WebMarkupContainer table = new WebMarkupContainer("reportsTable"){
			@Override
			public boolean isVisible(){
				return !comments.getByReports().isEmpty();
			}
		};
		table.add(new RefreshingView<Comment>("report"){
			@Override
			protected Iterator<IModel<Comment>> getItemModels() {
				List<IModel<Comment>> result = new ArrayList<IModel<Comment>>();
				List<Comment> reportedComments = comments.getByReports();
				for (Comment comment : reportedComments) {
					result.add(new EntityModel<Comment>(Comment.class, comment));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<Comment> item) {
				Map<String, Comment> params = new HashMap<String, Comment>();
				params.put("reportModel", item.getModelObject());
				BaseLink<Void> link = new BaseLink<Void>("movieDetailLink", ReportedCommentsList.class);
				link.add(new Label("body", new PropertyModel<String>(item.getModel(), "body")));
				item.add(link);
				item.add(new Label("creationDate", new PropertyModel<Date>(item.getModel(), "creationDate")));
				Form<ReportedCommentsList> cleanForm = new Form<ReportedCommentsList>("cleanReport", new CompoundPropertyModel<ReportedCommentsList>(ReportedCommentsList.this)) {
					@Override
					protected void onSubmit() {
						comments.cleanReports(item.getModelObject());
					}
				};
				cleanForm.add(new Button("clean", new ResourceModel("clean")));
				item.add(cleanForm);
				Form<ReportedCommentsList> deleteForm = new Form<ReportedCommentsList>("deleteComment", new CompoundPropertyModel<ReportedCommentsList>(ReportedCommentsList.this)) {
					@Override
					protected void onSubmit() {
						comments.delete(item.getModelObject());
					}
				};
				deleteForm.add(new Button("delete", new ResourceModel("delete")));
				item.add(deleteForm);
			}
		});
		add(table);
		add(new WebMarkupContainer("noReports"){
			@Override
			public boolean isVisible(){
				return comments.getByReports().isEmpty();
			}
		});
	}
}
