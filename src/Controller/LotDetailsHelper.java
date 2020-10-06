package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.LotDetails;

public class LotDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleCars");
	
	public void insertNewLotDetails(LotDetails s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<LotDetails> getLists() {
		EntityManager em = emfactory.createEntityManager();
		List<LotDetails> allDetails = em.createQuery("SELECT d FROM LotDetails d").getResultList();
		return allDetails;
	}

	public void deleteLot(LotDetails toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<LotDetails> typedQuery = em.createQuery("select detail from LotDetails detail where detail.id = :selectedId", LotDetails.class);
		
		typedQuery.setParameter("selectedId", toDelete.getId());
		
		typedQuery.setMaxResults(1);
		
		LotDetails result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public LotDetails searchForLotDetailsById(Integer tempId) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		LotDetails found = em.find(LotDetails.class, tempId);
		em.close();
		return found;
	}

	public void updateLot(LotDetails toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
