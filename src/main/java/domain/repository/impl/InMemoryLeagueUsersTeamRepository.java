package domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.LeagueUsersTeam;
import domain.repository.LeagueUsersTeamRepository;

@Repository
public class InMemoryLeagueUsersTeamRepository implements LeagueUsersTeamRepository{

	@PersistenceContext 
	EntityManager entityManager;
	
	@Transactional
	public void add(LeagueUsersTeam team) {
		entityManager.merge(team);
	}
	
	@Transactional
	public List<LeagueUsersTeam> getUsersLeagues(Long usersTeamUserId){
		Query query = entityManager.createQuery("SELECT r FROM LeagueUsersTeam r WHERE r.usersTeamUserId = :usersTeamUserId").setParameter("usersTeamUserId", usersTeamUserId);
		List<LeagueUsersTeam> list = query.getResultList();
		
		return list;
	}
	
	@Transactional
	public Long findLeagueByTeamAndUserId(int usersTeamId, Long usersTeamUserId) {
		Query query = entityManager.createQuery("SELECT r FROM LeagueUsersTeam r WHERE r.usersTeamUserId = :usersTeamUserId AND r.usersTeamId = :usersTeamId").
				setParameter("usersTeamUserId", usersTeamUserId).setParameter("usersTeamId", usersTeamId);
		LeagueUsersTeam lut = (LeagueUsersTeam) query.getSingleResult();
		Long league = (long) lut.getLeagueId();
		return league;
	}
	
	@Transactional
	public List<LeagueUsersTeam> getAll(){
		Query query = entityManager.createQuery("SELECT r FROM LeagueUsersTeam r");
		List<LeagueUsersTeam> list = query.getResultList();
		
		return list;
	}
}
