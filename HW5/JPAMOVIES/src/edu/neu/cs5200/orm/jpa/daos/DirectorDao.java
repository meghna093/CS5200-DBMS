package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import edu.neu.cs5200.orm.jpa.entities.Director;

public class DirectorDao {
	private static final String DIV = "JPAMOVIES";
	private static EntityManagerFactory fr = Persistence.createEntityManagerFactory(DIV);
	public void showDrtr() {
		List<Director> drtrs = fndAllDrtr();
		for (Director dr : drtrs) {
			System.out.println(dr.getFirstName() + " " + dr.getLastName());
		}
	}
	public int crtDrtr(Director dr) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		entityMgr.persist(dr);
		entityMgr.flush();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return dr.getId();
	}
	public void delDrtrName(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("delete from Director dr where dr.firstName =:firstName and dr.lastName =:lastName");
		qry.setParameter("firstName", fName);
		qry.setParameter("lastName", lName);
		qry.executeUpdate();
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public int delDrtr() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		int cnt = entityMgr.createQuery("delete from Director").executeUpdate();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return cnt;
	}
	public Director fndDrtrId(int id) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Director drtr = entityMgr.find(Director.class, id);
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return drtr;
	}
	public List<Director> fndAllDrtr() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select dr from Director dr");
		List<Director> directors = (List<Director>) qry.getResultList();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return directors;
	}
	public void rmDrtr(int id, String nfName, String nlName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Director dr = entityMgr.find(Director.class, id);
		dr.setFirstName(nfName);
		dr.setLastName(nlName);
		entityMgr.getTransaction().commit();
	}
	public Director fndFirstDrtr() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select dr from Director dr");
		List<Director> directors = (List<Director>) qry.getResultList();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return directors.get(0);
	}
	public Director fndDrtrName(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select dr from Director dr where dr.firstName =:firstName and dr.lastName =:lastName");
		qry.setParameter("firstName", fName);
		qry.setParameter("lastName", lName);
		Director dr = (Director) qry.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return dr;
	}
	public int fndDrtrId(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select dr from Director dr where dr.firstName =:firstName and dr.lastName =:lastName");
		qry.setParameter("firstName", fName);
		qry.setParameter("lastName", lName);
		Director dr = (Director) qry.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return dr.getId();
	}
	public void test() {
		Director drtr;
		delDrtr();
		drtr = new Director("Jimmy", "Camaron");
		crtDrtr(drtr);
		drtr = new Director("Steven","Spielberg");
		crtDrtr(drtr);
		drtr = new Director("Ron", "Howard");
		crtDrtr(drtr);
		drtr = fndFirstDrtr();
		System.out.println(drtr.getFirstName() + " " + drtr.getLastName());
		showDrtr();
		rmDrtr(fndDrtrId("Jimmy", "Camaron"), "James", "Cameron");
		delDrtrName("Ron","Howard");
		showDrtr();
	}

}
