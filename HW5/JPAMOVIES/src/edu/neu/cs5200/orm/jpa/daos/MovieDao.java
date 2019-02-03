package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Movie;
import edu.neu.cs5200.orm.jpa.entities.Director;

public class MovieDao {
	private static final String DIV = "JPAMOVIES";
	private static EntityManagerFactory fr = Persistence.createEntityManagerFactory(DIV);
	public void showMveDrtr(Director dr) {
		List<Movie>mves= dr.getMoviesDirected();
		System.out.println(dr.getFirstName()+" "+dr.getLastName());
		for(Movie m:mves) {
			System.out.println(m.getTitle());
		}
	}
	public void joinActrMve(int actID, int mveID) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Movie mve = entityMgr.find(Movie.class, mveID);
		Actor actr = entityMgr.find(Actor.class, actID);
		actr.getMoviesActed().add(mve);
		mve.getActors().add(actr);
		entityMgr.persist(actr);
		entityMgr.persist(mve);
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public int crtMve(Movie mve) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		entityMgr.persist(mve);
		entityMgr.flush();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return mve.getId();
	}
	public void joinDrtrMve(int drID, int mveID) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Movie mve = entityMgr.find(Movie.class, mveID);
		Director drtr = entityMgr.find(Director.class, drID);
		drtr.getMoviesDirected().add(mve);
		mve.getDirectors().add(drtr);
		entityMgr.persist(drtr);
		entityMgr.persist(mve);
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public Movie fndMveId(int id) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Movie mve = entityMgr.find(Movie.class, id);
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return mve;
	}
	public List<Movie> fndAllMve() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select mve from Movie mve");
		List<Movie> mves = (List<Movie>)qry.getResultList();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return mves;
	}
	public void delMve(int id) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Movie mve = entityMgr.find(Movie.class, id);
		entityMgr.remove(mve);
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public Movie fndTitle(String title) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select mve from Movie mve where mve.title=:title");
		qry.setParameter("title", title);
		Movie mve = (Movie)qry.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return mve;
	}
	public void showMve() {
		List<Movie>mves= fndAllMve();
		for(Movie mve:mves) {
			System.out.println(mve.getTitle());
			List<Director>drtrs = mve.getDirectors();
			List<Actor>actrs = mve.getActors();
			for(Actor act:actrs) {
				System.out.println(act.getFirstName()+" "+act.getLastName());
			}
			for(Director dr:drtrs) {
				System.out.println(dr.getFirstName()+" "+dr.getLastName());
			}			
		}
	}
	public void rmMve(int id, String nTitle) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Movie mve = entityMgr.find(Movie.class, id);
		mve.setTitle(nTitle);
		entityMgr.getTransaction().commit();
	}
	public void showMveActr(Actor act) {
		List<Movie>mves= act.getMoviesActed();
		System.out.println(act.getFirstName()+" "+act.getLastName());
		for(Movie m:mves) {
			System.out.println(m.getTitle());
		}
	}
	public void test() {
		DirectorDao drtrDao = new DirectorDao();
		ActorDao actrDao = new ActorDao();
		int mveId; 
		int actId;
		int drId;
		mveId = crtMve(new Movie("Blade Runner"));
		actId = actrDao.crtActr(new Actor("Harrison","Ford"));
		joinActrMve(actId,mveId);
    	joinActrMve(actrDao.crtActr(new Actor("Rutger","Hauer")),mveId);
		drId= drtrDao.crtDrtr(new Director("Ridley","Scott"));
		joinDrtrMve(drId,mveId);
		mveId = crtMve(new Movie("Raiders of The Lost Ark"));
		joinActrMve(actId,mveId);
		actId= actrDao.crtActr(new Actor("Karen","Allen"));
    	joinActrMve(actId,mveId);
    	drId=drtrDao.fndDrtrId("Steven","Spielberg");
		joinDrtrMve(drId,mveId);
		mveId = crtMve(new Movie("Close Encounters of the Third Kind"));
		joinDrtrMve(drId,mveId);
		actId=actrDao.crtActr(new Actor("Richard","Dreyfus"));
		joinActrMve(actId,mveId);
		actId=actrDao.crtActr(new Actor("Melinda","Dillon"));
		joinActrMve(actId,mveId);
		showMve();
		showMveActr(actrDao.fndActName("Harrison","Ford"));
		showMveDrtr(drtrDao.fndDrtrName("Steven", "Spielberg"));
	}	
}
