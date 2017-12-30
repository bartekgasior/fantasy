package service;

import java.util.List;

import domain.Match;

public interface MatchService {
	List<Match> getAllMatches();
	List<Match> getMatchesByPage(int pageId, int rows);
	void addMatch(Match match);
	Match findMatchByTeamsId(int homeTeamId, int awayTeamId);
	void deleteMatch(Long id);
}
