package domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.League;
import domain.LeagueUsersTeam;
import domain.repository.LeagueRepository;

@Repository
public class InMemoryLeagueRepository implements LeagueRepository {
	@PersistenceContext 
	EntityManager entityManager;
	
	@Transactional
	public League addLeague(League league) {
		entityManager.persist(league);
		entityManager.flush();
		return league;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<League> getUserLeagues(){
		Query query = entityManager.createQuery("SELECT r FROM League r");
		List<League> list = query.getResultList();
		
		return list;
	}
	
	@Transactional 
	public League findLeagueById(int id) {
		return entityManager.find(League.class, id);
	}
}
