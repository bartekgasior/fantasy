package service;

import java.util.List;

import domain.RealTeam;

public interface RealTeamService {

	List<RealTeam> getAllRealTeams();
	RealTeam getRealTeam(Long realTeamId);
	void addRealTeam(RealTeam realTeam);
	void deleteRealTeam(Long realTeamId);
}
