package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.UsersTeam;
import domain.repository.UsersTeamRepository;
import service.UsersTeamService;

@Service
public class UsersTeamServiceImpl implements UsersTeamService{
	
	@Autowired
	private UsersTeamRepository usersTeamRepository;
	
	public UsersTeam addUsersTeam(UsersTeam team) {
		return usersTeamRepository.addUsersTeam(team);
	}
	
	public List<UsersTeam> getUsersTeams(Long userId){
		return usersTeamRepository.getUsersTeams(userId);
	}
	
	public List<UsersTeam> getAll(){
		return usersTeamRepository.getAll();
	}
}
