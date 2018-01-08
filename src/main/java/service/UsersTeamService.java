package service;

import java.util.List;

import domain.UsersTeam;

public interface UsersTeamService {
	UsersTeam addUsersTeam(UsersTeam team);
	List<UsersTeam> getUsersTeams(Long userId);
	List<UsersTeam> getAll();
}
