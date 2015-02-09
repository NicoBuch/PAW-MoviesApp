package ar.edu.itba.it.paw.web.movie;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.ocpsoft.prettytime.PrettyTime;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.movie.NoStockAvailableException;
import ar.edu.itba.it.paw.domain.prize.Prize;
import ar.edu.itba.it.paw.domain.prize.PrizeRepo;
import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.ConditionalForm;
import ar.edu.itba.it.paw.web.ConditionalFormData;
import ar.edu.itba.it.paw.web.DeleteLink;
import ar.edu.itba.it.paw.web.LoggedLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.comment.ReportCommentPage;

@SuppressWarnings("serial")
public class ViewMoviePage  extends BasePage {
	
	@SpringBean static MovieRepo movies;
	@SpringBean static CommentRepo comments;
	@SpringBean static GenreRepo genres;
	@SpringBean static PrizeRepo prizes;
	private transient boolean prize;
	private transient String name;
	private transient Integer rating;
	private transient String body;
	
	EntityModel<Movie> movie;
	PageParameters params;
	FileUploadField pictureToUpload;
	
	private static int MIN_RATING = 1;
	private static int MAX_RATING = 5;
	public ViewMoviePage(PageParameters params)  throws Exception{
		this.params = params;
		final EntityModel<Movie> movie = new EntityModel<Movie>(Movie.class,movies.get(params.get("movieId").toInteger()));
		movie.getObject().visit();
		
		add(new Label(("visits"), new PropertyModel<Integer>(movie, "visits")));
		add(new Label(("stock"), new PropertyModel<String>(movie, "stock"){
			@Override
			public String getObject() {
				try {
					return String.valueOf(movies.getStock(movie.getObject()));
				} catch (FileNotFoundException e) {
					throw new RuntimeException();
				} catch (NoStockAvailableException e) {
					return e.getMessage();
				}
			}
		}));
		add(new Label(("title"), new PropertyModel<String>(movie, "title")));
		add(new Label(("director"), new PropertyModel<String>(movie, "director")));
		add(new Label(("releaseDate"), new PrettyTime().format(movie.getObject().getReleaseDate())));
		add(new Label(("minutes"), new PropertyModel<String>(movie, "minutes")));
		add(new Label(("description"), new PropertyModel<String>(movie, "description")));
		
		RepeatingView genreList = new RepeatingView("genreList");
		 for (Genre g : movie.getObject().getGenres()) {
			genreList.add(new Label(genreList.newChildId(),g.getName()));
		}
		add(genreList);
		add(new RefreshingView<Prize>("prizeList") {
			@Override
			protected Iterator<IModel<Prize>> getItemModels() {
				List<IModel<Prize>> result = new ArrayList<IModel<Prize>>();
				for(Prize a: movie.getObject().getPrices()) {
					result.add(new EntityModel<Prize>(Prize.class, a));
				}
				return result.iterator();
			}
	
			@Override
			protected void populateItem(final Item<Prize> item) {
				 item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
				   item.add(new DeleteLink<Prize>("deletePrizeLink", true, true, false, item.getModel()) {
						@Override
						public void onClick() {
							movie.getObject().removePrize(getModelObject());	
						}
					});
			}
	});
		add(new RefreshingView<Prize>("nominationList") {
			@Override
			protected Iterator<IModel<Prize>> getItemModels() {
				List<IModel<Prize>> result = new ArrayList<IModel<Prize>>();
				for(Prize a: movie.getObject().getNominations()) {
					result.add(new EntityModel<Prize>(Prize.class, a));
				}
				return result.iterator();
			}
	
			@Override
			protected void populateItem(final Item<Prize> item) {
				 item.add(new Label("name", new PropertyModel<String>(item.getModel(), "name")));
				   item.add(new DeleteLink<Prize>("deleteNominationLink", true, true, false, item.getModel()) {
						@Override
						public void onClick() {
							movie.getObject().removePrize(getModelObject());
						}
					});
			}
		});
		add(new NonCachingImage("moviePicture", new DynamicImageResource(){

			@Override
			protected byte[] getImageData(Attributes attributes) {
				return movie.getObject().getPicture();
			}
			
		}));
		add(new DeleteLink<Movie>("deletePictureLink", true, true, false, movie) {
			@Override
			public void onClick() {
				movie.getObject().deletePicture();			
			}
		});
		ConditionalForm<ViewMoviePage> adminPrizesForm = new ConditionalForm<ViewMoviePage>("adminPrizesForm",new CompoundPropertyModel<ViewMoviePage>(this),
																							true,true,false){
			@Override
			protected void onSubmit() {
				if(name != null){
					Prize pri = new Prize(movie.getObject(), name, prize);
					movie.getObject().addPrize(pri);
					movie.detach();
				}
				setResponsePage(ViewMoviePage.class,ViewMoviePage.this.params);
			}
		};
		adminPrizesForm.add(new Button("addMovieAward", new ResourceModel("addMovieAward")));
		adminPrizesForm.add(new TextField<String>("name"));
		adminPrizesForm.add(new CheckBox("prize"));
		add(adminPrizesForm);
		
		
		Form<ViewMoviePage> setPictureForm = new ConditionalForm<ViewMoviePage>("setPicutreForm", new CompoundPropertyModel<ViewMoviePage>(this),
																				true, true ,false){
			@Override
			protected void onSubmit() {
				List<FileUpload> file = pictureToUpload.getFileUploads();
				byte[] moviePicture = file.get(0).getBytes();
				movie.getObject().setPicture(moviePicture);
				onSubmit();
			}
		};
		setPictureForm.add(new Button("uploadPicture", new ResourceModel("uploadPicture")));
		setPictureForm.add(pictureToUpload = new FileUploadField("pic", new LoadableDetachableModel<List<FileUpload>>() {

			@Override
			protected List<FileUpload> load() {
				return null;
			}
		}));
		add(setPictureForm);
			
		List<Integer> ratings = new ArrayList<Integer>();
		for(int i = MIN_RATING ; i <= MAX_RATING ; i++){
			ratings.add(i);
		}
		Form<ViewMoviePage> commentMovieForm = new ConditionalForm<ViewMoviePage>("commentMovieForm", new CompoundPropertyModel<ViewMoviePage>(this), true, false, false){
			@Override
			protected void onSubmit(){
				Comment comment;
				try {
					comment = new Comment(body, rating, movie.getObject(),
							MoviesWicketSession.get().getUserModel().getObject());
							movie.getObject().addComment(comment);
//							users.get(MoviesWicketSession.get().getUserId()).addComment(comment);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public boolean isVisible(){
				if(!super.isVisible()){
					return false;
				}
				MoviesWicketSession session = MoviesWicketSession.get();
				if(session.isSignedIn()){
					try {
						user = users.getByEmail(MoviesWicketSession.get().getEmail());
						return user.canComment(movie.getObject());
					} catch (EmailNotFound e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return false;
			}
		};
		TextField<Integer> bodyField = new TextField<Integer>("body");
		bodyField.setRequired(true);
		commentMovieForm.add(bodyField);
		DropDownChoice<Integer> dpc = new DropDownChoice<Integer>("rating", ratings);
		dpc.add(new RangeValidator<Integer>(MIN_RATING, MAX_RATING));
		dpc.setRequired(true);
		commentMovieForm.add(new Button("addComment"));
		commentMovieForm.add(dpc);
		add(commentMovieForm);
		add(new RefreshingView<Comment>("comment", movie) {		
			@Override
			protected void populateItem(final Item<Comment> item) {
				item.add(new Label("commentAuthor", new PropertyModel<String>(new PropertyModel<User>(item.getModel(), "user"), "email")));
				item.add(new Label("ratingLabel", new PropertyModel<Integer>(item.getModel(), "rating")));
				item.add(new Label("body", new PropertyModel<String>(item.getModel(), "body")));
				item.add(new Label("avgCommentRatings", new PropertyModel<Double>(item.getModel(), "avgCommentRatings")));
				item.add(new LoggedLink<Comment>("reportCommentLink",true,false,false,ListMoviesPage.class,null,item.getModel()){
					@Override
					public boolean isVisible(){
						if(super.isVisible()){
							try {
								User user;
								user = users.getByEmail(MoviesWicketSession.get().getEmail());
								return user.canReport(getModelObject());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						return false;
					}
					@Override
					public void onClick() {
						PageParameters commentParams = new PageParameters();
						commentParams.add("commentId", item.getModelObject().getId());
						setResponsePage(ReportCommentPage.class, commentParams);
					}
				});
				item.add(new DeleteLink<Comment>("deleteCommentLink", true, true, false, item.getModel()) {
					@Override
					public void onClick() {
						comments.delete(getModelObject());
					}
				});
				List<Integer> ratings = new ArrayList<Integer>();
				for(int i = MIN_RATING ; i <= MAX_RATING ; i++){
					ratings.add(i);
				}
				DropDownChoice<Integer> dpcComment = new DropDownChoice<Integer>("rating", ratings);
				dpcComment.add(new RangeValidator<Integer>(MIN_RATING, MAX_RATING));
				dpcComment.setRequired(true);
				ConditionalFormData<ViewMoviePage,Comment> commentRateForm = new ConditionalFormData<ViewMoviePage,Comment>("rating"
																					,new CompoundPropertyModel<ViewMoviePage>(ViewMoviePage.this)
																					,true,false,false,
																					item.getModel()){
					User user;
					@Override
					protected void onSubmit(){
						try {
							
							user = users.getByEmail(MoviesWicketSession.get().getEmail());
							data.getObject().rate(user,rating);
							data.detach();
							} catch (EmailNotFound e) {
								e.printStackTrace();
							}
					}
					@Override
					public boolean isVisible(){
						try {
							if(super.isVisible() == false){
								return false;
							}
							MoviesWicketSession session = MoviesWicketSession.get();
							if(session.isSignedIn()){
								user = users.getByEmail(MoviesWicketSession.get().getEmail());
								return user.canRate(data.getObject());
							}
						} catch (EmailNotFound e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					}
				};	
				
				commentRateForm.add(dpcComment);
				item.add(commentRateForm);
			}

			@Override
			protected Iterator<IModel<Comment>> getItemModels() {
				List<IModel<Comment>> result = new ArrayList<IModel<Comment>>();
				for(Comment c: movie.getObject().getComments()) {
					result.add(new EntityModel<Comment>(Comment.class, c));
				}
				return result.iterator();
			}
		});
	}
	
	@Override
	public
	void detachModels(){
		super.detachModels();
		if(movie!=null){
			movie.detach();
		}
	}
}
	