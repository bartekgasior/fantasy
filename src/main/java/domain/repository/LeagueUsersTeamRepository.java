package domain.repository;

import java.util.List;

import domain.LeagueUsersTeam;

public interface LeagueUsersTeamRepository {
	void add(LeagueUsersTeam team);
	List<LeagueUsersTeam> getUsersLeagues(Long usersTeamUserId);
	List<LeagueUsersTeam> getAll();
	Long findLeagueByTeamAndUserId(int usersTeamId, Long usersTeamUserId);
}
