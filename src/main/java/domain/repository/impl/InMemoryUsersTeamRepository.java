package domain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.UsersTeam;
import domain.repository.UsersTeamRepository;

@Repository
public class InMemoryUsersTeamRepository implements UsersTeamRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void addUsersTeam(UsersTeam team) {
		entityManager.merge(team);
	}
	
}
