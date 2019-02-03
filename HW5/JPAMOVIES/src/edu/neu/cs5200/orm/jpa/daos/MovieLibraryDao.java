package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Movie;
import edu.neu.cs5200.orm.jpa.entities.Director;
import edu.neu.cs5200.orm.jpa.entities.MovieLibrary;

public class MovieLibraryDao {
	private static final String DIV = "JPAMOVIES";
	private static EntityManagerFactory fr = Persistence.createEntityManagerFactory(DIV);
	public void showMveLib(MovieLibrary mveLib) {
		System.out.println(mveLib.getName());
		List<Movie>mves = mveLib.getMovies();
		for(Movie mve:mves) {
			System.out.println(mve.getTitle());
			List<Actor>actrs = mve.getActors();
			List<Director>drtrs = mve.getDirectors();
			for(Actor act:actrs) {
				System.out.println(act.getFirstName()+" "+act.getLastName());
			}
			for(Director dr:drtrs) {
				System.out.println(dr.getFirstName()+" "+dr.getLastName());
			}
		}
	}
	public int crtMveLib(MovieLibrary lbry) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		entityMgr.persist(lbry);
		entityMgr.flush();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return lbry.getId();
	}
	public int delMveLib() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		int cnt = entityMgr.createQuery("delete from MovieLibrary").executeUpdate();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return cnt;
	}
	public void joinMveLib(int mveID, int libID) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		MovieLibrary lbry = entityMgr.find(MovieLibrary.class, libID);
		Movie mve = entityMgr.find(Movie.class, mveID);
		lbry.getMovies().add(mve);
		mve.setLibrary(lbry);
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public MovieLibrary fndMveLibName(String name) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select mve from MovieLibrary mve where mve.name=:name");
		qry.setParameter("name", name);
		MovieLibrary mvLib = (MovieLibrary)qry.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return mvLib;
	}
	public void test() {
		MovieDao mveDao = new MovieDao();
		ActorDao actDao = new ActorDao();
		DirectorDao drDao = new DirectorDao();
		int mveId;
		int mveLibId;
		delMveLib();
		mveId = mveDao.crtMve(new Movie("Star Wars A New Hope"));
		mveDao.joinActrMve(actDao.crtActr(new Actor("Mark","Hamill")),mveId);
    	mveDao.joinActrMve(actDao.crtActr(new Actor("Carrie","Fisher")),mveId);
		mveDao.joinDrtrMve(drDao.crtDrtr(new Director("George","Lucas")),mveId);
		MovieLibrary mlib = new MovieLibrary("Favorite Movies");
		mveLibId = crtMveLib(mlib);
		joinMveLib(mveId,mveLibId);
		mveId = mveDao.crtMve(new Movie("The Revanant"));
		mveDao.joinActrMve(actDao.crtActr(new Actor("Leonardo","DiCaprio")),mveId);
    	mveDao.joinActrMve(actDao.crtActr(new Actor("Tom","Hardy")),mveId);
		mveDao.joinDrtrMve(drDao.crtDrtr(new Director("Alejandro","Inarritu")),mveId);
		joinMveLib(mveId,mveLibId);
		showMveLib(fndMveLibName("Favorite Movies"));
	}
}
