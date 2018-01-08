package service;

import java.util.List;

import domain.League;

public interface LeagueService {
	League addLeague(League league);
	List<League> getUserLeagues();
	League findLeagueById(int id);
}
