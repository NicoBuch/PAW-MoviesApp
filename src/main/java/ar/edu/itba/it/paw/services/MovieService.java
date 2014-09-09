package ar.edu.itba.it.paw.services;

import java.sql.Date;
import java.util.List;

import extra.Filter;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.Movie.Genre;

public class MovieService {
		private static MovieService obj = null;
	
		private MovieService(){
			
		}
		public static MovieService getInstance(){
			if(obj == null)
				obj = new MovieService();
			return obj;
		}
		/*Devuelve listado de peliculas*/
		public Iterable<Movie> getAll(){
			return null;
		}
		public Iterable<Movie> getAll(Filter filter){
			return null;
		}
		
		/*Devuelve una pelicula, se puede cambiar el tipo long por el tipo que tenga entity*/
		public Movie getById(long id){
			return null;
		}
		/*Actualiza una pelicula, la crea si no estaba creada*/
		public void update(Movie movie){
			
		}
		public void update(String movieName, Date releaseDate, String directorName,
								String genre, int minutes){
			
		}
		/*Se fija si es estreno la pelicula*/
		public boolean isNew(Movie movie){
			return false;
		}
}
