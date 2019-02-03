package edu.neu.cs5200.web.services.jaxrs;

import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import edu.neu.cs5200.orm.jpa.entities.Movie;
import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.daos.MovieDao;

@Path("movie")
public class MovieService { 
   @GET
   @Path("/")
   @Produces(MediaType.APPLICATION_XML)
	public List<Movie> getMovies() {
	   MovieDao mveDao = new MovieDao();
	   List<Movie>mves = new ArrayList<Movie>();
	   mves = mveDao.fndAllMve();
	   System.out.println(mves.size());
	   return mves;  
	}
   @Path("/{mid}")
   @GET
   @Produces(MediaType.APPLICATION_XML)
	public Movie getMovieById(@PathParam("mid") int mid) {
	   MovieDao mveDao = new MovieDao();
	   Movie mve = new Movie();
	   mve = mveDao.fndMveId(mid);
	   System.out.println(mve.getTitle());
	   return mve;  
	}
   @Path("/{mid}/actor")
   @GET
   @Produces(MediaType.APPLICATION_XML)
	public List<Actor> getMoviesByActorId(@PathParam("mid") int mid) {
	   MovieDao mveDao = new MovieDao();
	   Movie mve = new Movie();
	   mve = mveDao.fndMveId(mid);
	   List<Actor>actors = mve.getActors();
	   System.out.println(actors);
	   return actors;
	}
}
