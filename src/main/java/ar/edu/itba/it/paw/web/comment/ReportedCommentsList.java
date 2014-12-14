package ar.edu.itba.it.paw.web.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ReportedCommentsList extends BasePage{
	@SpringBean CommentRepo comments;
	private transient boolean showTable = false;
	
	public ReportedCommentsList(){
		showTable = !comments.getByReports().isEmpty();
		WebMarkupContainer table = new WebMarkupContainer("reportsTable"){
			@Override
			public boolean isVisible(){
				return showTable;
			}
		};
		table.add(new RefreshingView<Report>("report"){
			@Override
			protected Iterator<IModel<Report>> getItemModels() {
				List<IModel<Report>> result = new ArrayList<IModel<Report>>();
				List<Report> reports = comments.getByReports();
				for (Report report : reports) {
					result.add(new EntityModel<Report>(Report.class, report));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<Report> item) {
				Map<String, Report> params = new HashMap<String, Report>();
				params.put("reportModel", item.getModelObject());
				item.add(new BaseLink<Void>("movieDetailLink", ReportedCommentsList.class).add(new Label("body", new PropertyModel<String>(item.getModelObject().getComment(), "body"))));
				item.add(new Form<ReportedCommentsList>("cleanReport", new CompoundPropertyModel<ReportedCommentsList>(ReportedCommentsList.this)) {			
					@Override
					protected void onSubmit() {
						item.getModelObject().getComment().cleanReports();
					}
				});
				item.add(new Form<ReportedCommentsList>("deleteComment", new CompoundPropertyModel<ReportedCommentsList>(ReportedCommentsList.this)) {			
					@Override
					protected void onSubmit() {
						Comment comment = item.getModelObject().getComment();
						comments.delete(comment);
					}
				});
			}
		});
		add(table);
		add(new WebMarkupContainer("noReports"){
			@Override
			public boolean isVisible(){
				return !showTable;
			}
		});
	}
}
