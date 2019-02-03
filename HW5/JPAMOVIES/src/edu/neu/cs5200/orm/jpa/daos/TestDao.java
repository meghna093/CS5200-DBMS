package edu.neu.cs5200.orm.jpa.daos;

public class TestDao {
	public static void main(String[] args) {
		ActorDao actDao = new ActorDao();
		DirectorDao drDao = new DirectorDao();
		MovieDao mveDao = new MovieDao();
		MovieLibraryDao mveLibDao = new MovieLibraryDao();
		actDao.test();
		drDao.test();
		mveDao.test();
		mveLibDao.test();
	}
}
