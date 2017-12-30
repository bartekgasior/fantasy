package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.UsersTeam;
import domain.repository.UsersTeamRepository;
import service.UsersTeamService;

@Service
public class UsersTeamServiceImpl implements UsersTeamService{
	
	@Autowired
	private UsersTeamRepository usersTeamRepository;
	
	public void addUsersTeam(UsersTeam team) {
		usersTeamRepository.addUsersTeam(team);
	}
}
