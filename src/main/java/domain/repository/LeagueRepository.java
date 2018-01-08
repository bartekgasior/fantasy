package domain.repository;

import java.util.List;

import domain.League;

public interface LeagueRepository {
	League addLeague(League league);
	List<League> getUserLeagues();
	League findLeagueById(int id);
}
