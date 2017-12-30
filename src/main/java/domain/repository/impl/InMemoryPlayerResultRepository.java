package domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.PlayerResult;
import domain.repository.PlayerResultRepository;

@Repository
public class InMemoryPlayerResultRepository implements PlayerResultRepository{
 
	@PersistenceContext 
	EntityManager entityManager;
	
	@Transactional
	public void addResult(PlayerResult result) {
		entityManager.merge(result);
	}
	
	@Transactional
	public List<PlayerResult> getAllResults(){
		//List<PlayerResult> list = new ArrayList<PlayerResult>();
		
		Query query = entityManager.createQuery("SELECT r FROM PlayerResult r");
		List<PlayerResult> list = query.getResultList();
		
		return list;
	}
}
