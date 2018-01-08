package domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import domain.PlayerResult;
import domain.UsersTeamPlayer;
import domain.repository.UsersTeamPlayerRepository;

@Repository
public class InMemoryUsersTeamPlayerRepository implements UsersTeamPlayerRepository{
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void add(UsersTeamPlayer usersTeamPlayer) {
		entityManager.merge(usersTeamPlayer);
	}
	
	@Transactional
	public List<UsersTeamPlayer> getAll(){
		Query query = entityManager.createQuery("SELECT r FROM UsersTeamPlayer r");
		List<UsersTeamPlayer> list = query.getResultList();
		
		return list;
	}
}
