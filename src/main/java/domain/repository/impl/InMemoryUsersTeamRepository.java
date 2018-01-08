package domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.League;
import domain.UsersTeam;
import domain.repository.UsersTeamRepository;

@Repository
public class InMemoryUsersTeamRepository implements UsersTeamRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public UsersTeam addUsersTeam(UsersTeam team) {
		entityManager.persist(team);
		entityManager.flush();
		return team;
	}
	
	@Transactional
	public List<UsersTeam> getUsersTeams(Long userId){
		Query query = entityManager.createQuery("SELECT r FROM UsersTeam r WHERE r.userId = :userId").setParameter("userId", userId);
		List<UsersTeam> list = query.getResultList();
		
		return list;
	}
	
	@Transactional
	public List<UsersTeam> getAll(){
		Query query = entityManager.createQuery("SELECT r FROM UsersTeam r");
		List<UsersTeam> list = query.getResultList();
		
		return list;
	}
}
