package domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.MatchPlayer;
import domain.repository.MatchPlayerRepository;

@Repository
public class InMemoryMatchPlayerRepository implements MatchPlayerRepository{
	
	@PersistenceContext 
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MatchPlayer> getAllMatchPlayers(){
		Query query = entityManager.createQuery("SELECT r FROM MatchPlayer r");
		List<MatchPlayer> list = query.getResultList();
		
		return list;
	}
	
	@Transactional
	public void addMatchPlayer(MatchPlayer matchPlayer) {
		entityManager.merge(matchPlayer);
	}
}
