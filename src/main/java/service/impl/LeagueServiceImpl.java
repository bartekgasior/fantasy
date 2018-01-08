package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.League;
import domain.repository.LeagueRepository;
import service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService{
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	public League addLeague(League league) {
		return leagueRepository.addLeague(league);
	}
	
	public List<League> getUserLeagues(){
		return leagueRepository.getUserLeagues();
	}
	
	public League findLeagueById(int id) {
		return leagueRepository.findLeagueById(id);
	}
}
