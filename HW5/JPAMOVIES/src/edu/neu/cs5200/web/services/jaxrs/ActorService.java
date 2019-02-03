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
import edu.neu.cs5200.orm.jpa.daos.ActorDao;

@Path("/actor")
public class ActorService {
   @GET
   @Path("/")
   @Produces(MediaType.APPLICATION_XML)
	public List<Actor> getActors() {
	   ActorDao actDao = new ActorDao();
	   List<Actor>actrs = new ArrayList<Actor>();
	   actrs = actDao.fndAllAct();
	   System.out.println(actrs.size());
	   return actrs;  
	}
   @Path("/{aid}")
   @GET
   @Produces(MediaType.APPLICATION_XML)
	public Actor getActorById(@PathParam("aid") int aid) {
	   ActorDao actDao = new ActorDao();
	   Actor actr = new Actor();
	   actr = actDao.fndActId(aid);
	   System.out.println(actr);
	   return actr;  
	}
    @Path("/{aid}/movie")
    @GET
    @Produces(MediaType.APPLICATION_XML)
	public List<Movie> getMoviesByActorId(@PathParam("aid") int aid) {
    	ActorDao actDao = new ActorDao();
    	Actor actr = new Actor();
    	actr = actDao.fndActId(aid);
    	System.out.println(actr.getMoviesActed());
    	return actr.getMoviesActed();  
	}
}
