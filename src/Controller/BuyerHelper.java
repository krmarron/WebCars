package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.Buyer;

public class BuyerHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleCars");
	
	public void insertBuyer(Buyer b) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Buyer> showAllBuyers() {
		EntityManager em = emfactory.createEntityManager();
		List<Buyer> allBuyers = em.createQuery("SELECT b FROM Buyer b").getResultList();
		return allBuyers;
	}

	public Buyer findBuyer(String nameToLookUp) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Buyer> typedQuery = em.createQuery("select bh from Buyer bh where bh.buyerName = :selectedName", Buyer.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		Buyer foundBuyer;
		try {
			foundBuyer = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundBuyer = new Buyer(nameToLookUp);
		}
		em.close();
		
		return foundBuyer;
	}
}
