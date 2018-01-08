package domain.repository;

import java.util.List;

import domain.UsersTeam;

public interface UsersTeamRepository {
	UsersTeam addUsersTeam(UsersTeam team);
	List<UsersTeam> getUsersTeams(Long userId);
	List<UsersTeam> getAll();
}
