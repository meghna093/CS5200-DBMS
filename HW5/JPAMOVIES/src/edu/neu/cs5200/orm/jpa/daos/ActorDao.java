package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import edu.neu.cs5200.orm.jpa.entities.Actor;

public class ActorDao {
	private static final String DIV = "JPAMOVIES";
	private static EntityManagerFactory fr = Persistence.createEntityManagerFactory(DIV);
	public void showAct() {
		List<Actor>atrs= fndAllAct();
		for(Actor act:atrs) {
			System.out.println(act.getFirstName()+" "+act.getLastName());
		}
	}
	public int crtActr(Actor act) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		entityMgr.persist(act);
		entityMgr.flush();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return act.getId();
	}
	public void delActName(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query query = entityMgr.createQuery("delete from Actor act where act.firstName =:firstName and act.lastName =:lastName");
		query.setParameter("firstName", fName);
		query.setParameter("lastName", lName);
		query.executeUpdate();
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	public int delActr() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		int cnt = entityMgr.createQuery("delete from Actor").executeUpdate();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return cnt;
	}
	public Actor fndActId(int id) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Actor atr = entityMgr.find(Actor.class, id);
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return atr;
	}
	public List<Actor> fndAllAct() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select act from Actor act");
		List<Actor> atrs = (List<Actor>)qry.getResultList();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return atrs;
	}
	public void rmActr(int id, String nfName, String nlName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Actor atr = entityMgr.find(Actor.class, id);
		atr.setFirstName(nfName);
		atr.setLastName(nlName);
		entityMgr.getTransaction().commit();
	}
	public Actor fndFirstAct() {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select act from Actor act");
		List<Actor> atrs = (List<Actor>)qry.getResultList();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return atrs.get(0);
	}
	public int fndActId(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query qry = entityMgr.createQuery("select act from Actor act where act.firstName =:firstName and act.lastName =:lastName");
		qry.setParameter("firstName", fName);
		qry.setParameter("lastName", lName);
		Actor atr = (Actor)qry.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return atr.getId();
	}
	public Actor fndActName(String fName, String lName) {
		EntityManager entityMgr = fr.createEntityManager();
		entityMgr.getTransaction().begin();
		Query query = entityMgr.createQuery("select act from Actor act where act.firstName =:firstName and act.lastName =:lastName");
		query.setParameter("firstName", fName);
		query.setParameter("lastName", lName);
		Actor atr = (Actor)query.getSingleResult();
		entityMgr.getTransaction().commit();
		entityMgr.close();
		return atr;
	}
	public void test() {
		Actor actr;
		delActr();
		actr = new Actor("Sigorney","Weaver");
		crtActr(actr);
		actr = new Actor("Dan","Creg");
		crtActr(actr);
		actr = new Actor("Jim","Carrey");
		crtActr(actr);
		actr = fndFirstAct();
		System.out.println(actr.getFirstName()+" "+actr.getLastName());
		showAct();
		rmActr(fndActId("Dan","Creg"),"Daniel","Craig");
		delActName("Jim","Carrey");
		showAct();
	}
}
