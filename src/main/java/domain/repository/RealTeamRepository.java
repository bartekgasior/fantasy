package domain.repository;

import java.util.List;

import domain.RealTeam;

public interface RealTeamRepository {
	List <RealTeam> getAllRealTeams();
	RealTeam getRealTeam(Long realTeamId);
	void addRealTeam(RealTeam realTeam);
	void deleteRealTeam(Long realTeamId);
	void updateRealTeam(RealTeam realTeam);
}
