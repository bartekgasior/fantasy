package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.LeagueUsersTeam;
import domain.repository.LeagueUsersTeamRepository;
import service.LeagueUsersTeamService;

@Service
public class LeagueUsersTeamServiceImpl implements LeagueUsersTeamService {
	@Autowired
	private LeagueUsersTeamRepository leagueUsersTeamRepository;
	
	public void add(LeagueUsersTeam team) {
		leagueUsersTeamRepository.add(team);
	}
	
	public List<LeagueUsersTeam> getUsersLeagues(Long usersTeamUserId){
		return leagueUsersTeamRepository.getUsersLeagues(usersTeamUserId);
	}
	
	public List<LeagueUsersTeam> getAll(){
		return leagueUsersTeamRepository.getAll();
	}

	public Long findLeagueByTeamAndUserId(int usersTeamId, Long usersTeamUserId) {
		return leagueUsersTeamRepository.findLeagueByTeamAndUserId(usersTeamId, usersTeamUserId);
	}
}
