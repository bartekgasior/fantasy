package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Match;
import domain.repository.MatchRepository;
import service.MatchService;

@Service
public class MatchServiceImpl implements MatchService{
	
	@Autowired
	private MatchRepository matchRepository;
	
	public List<Match> getAllMatches(){
		return matchRepository.getAllMatches();
	}
	
	public List<Match> getMatchesByPage(int pageId, int rows){
		return matchRepository.getMatchesByPage(pageId, rows);
	}
	
	public void addMatch(Match match) {
		matchRepository.addMatch(match);
	}
	
	public Match findMatchByTeamsId(int homeTeamId, int awayTeamId) {
		return matchRepository.findMatchByTeamsId(homeTeamId, awayTeamId);
	}
	
	public void deleteMatch(Long id) {
		matchRepository.deleteMatch(id);
	}
}
