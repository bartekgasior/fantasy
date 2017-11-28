package domain.repository;

import java.util.List;

import domain.RealTeam;

public interface RealTeamRepository {
	List <RealTeam> getAllRealTeams();
	void addRealTeam(RealTeam realTeam);
	void deleteRealTeam(Long realTeamId);
}
