package ar.edu.itba.it.paw.web.movie;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValueConversionException;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.prize.Prize;
import ar.edu.itba.it.paw.domain.prize.PrizeRepo;
import ar.edu.itba.it.paw.web.ConditionalForm;
import ar.edu.itba.it.paw.web.DeleteLink;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ViewMoviePage  extends BasePage {
	
	@SpringBean static MovieRepo movies;
	@SpringBean static CommentRepo comments;
	@SpringBean static GenreRepo genres;
	@SpringBean static PrizeRepo prizes;
	private transient boolean prize;
	private transient String name;
	EntityModel<Movie> movieModel;
	PageParameters params;
	
	
	public ViewMoviePage(PageParameters params)  throws StringValueConversionException, NoIdException{
		this.params = params;
		final EntityModel<Movie> movie = new EntityModel<Movie>(Movie.class,movies.get(params.get("movieId").toInteger()));
		add(new Label(("title"), new PropertyModel<String>(movie, "title")));
		add(new Label(("director"), new PropertyModel<String>(movie, "director")));
		add(new Label(("releaseDate"), new PropertyModel<String>(movie, "releaseDate")));
		add(new Label(("minutes"), new PropertyModel<String>(movie, "minutes")));
		add(new Label(("description"), new PropertyModel<String>(movie, "description")));
		
		RepeatingView genreList = new RepeatingView("genreList");
		 for (Genre g : movie.getObject().getGenres()) {
			genreList.add(new Label(genreList.newChildId(),g.getName()));
		}
		add(genreList);
		
		IModel<List<Prize>> prizeModel = new LoadableDetachableModel<List<Prize>>(){
			@Override
			protected List<Prize> load() {
				return new ArrayList<Prize>(movie.getObject().getPrices()) ;
			}
		};
		add(new ListView<Prize>("prizeList", prizeModel)
		{	
			@Override
			protected void populateItem(ListItem<Prize> item) {
			   item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
			   item.add(new DeleteLink<Prize>("deletePrizeLink",true, true, false, item.getModel()) {
					@Override
					public void onClick() {
						movie.getObject().removePrize(getModelObject());
					}
				});
			}
			
		});
		IModel<List<Prize>> nominationsModel = new LoadableDetachableModel<List<Prize>>(){
			@Override
			protected List<Prize> load() {
				return new ArrayList<Prize>(movie.getObject().getNominations()) ;
			}
		};
		add(new ListView<Prize>("nominationList", nominationsModel) {
			
			@Override
			protected void populateItem(ListItem<Prize> item) {
			   item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
			   item.add(new DeleteLink<Prize>("deleteNominationLink", true, true, false, item.getModel()) {
					@Override
					public void onClick() {
						movie.getObject().removePrize(getModelObject());
					}
				});
			}
			
		});
		
		add(new Image("moviePicture", new ByteArrayResource("JPEG", movie.getObject().getPicture())));
		add(new DeleteLink<Movie>("deletePictureLink", true, true, false, null) {
			@Override
			public void onClick() {
				movie.getObject().deletePicture();			
			}
		});
		ConditionalForm<ViewMoviePage> adminPrizesForm = new ConditionalForm<ViewMoviePage>("adminPrizesForm",new CompoundPropertyModel<ViewMoviePage>(this),true,true,false){
			@Override
			protected void onSubmit() {
				if(name != null){
					Prize pri = new Prize(movie.getObject(), name, prize);
					movie.getObject().addPrize(pri);
				}
				setResponsePage(ViewMoviePage.class,ViewMoviePage.this.params);
			}
		};
		adminPrizesForm.add(new Button("addMovieAward", new ResourceModel("addMovieAward")));
		adminPrizesForm.add(new TextField<String>("name"));
		adminPrizesForm.add(new CheckBox("prize"));
		add(adminPrizesForm);
		
		
		Form<ViewMoviePage> setPicutreForm = new Form<ViewMoviePage>("setPicutreForm", new CompoundPropertyModel<ViewMoviePage>(this)){
			@Override
			protected void onSubmit() {
				onSubmit();
			}
		};
		setPicutreForm.add(new Button("uploadPicture", new ResourceModel("uploadPicture")));
		setPicutreForm.add(new FileUploadField("pic"));
		add(setPicutreForm);
		
		
		
		WebMarkupContainer adminBox = new WebMarkupContainer ("adminSettings");
		add(adminBox);		
		
		
		//Si no es admin
		adminBox.setVisible(false);
		
		
		Form<ViewMoviePage> commentRateForm = new Form<ViewMoviePage>("commentRateForm", new CompoundPropertyModel<ViewMoviePage>(this));		
		
		Form<ViewMoviePage> commentMovieForm = new Form<ViewMoviePage>("commentMovieForm", new CompoundPropertyModel<ViewMoviePage>(this));
		
	}
}
	
	
	
	
	


