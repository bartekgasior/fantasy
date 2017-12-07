package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.RealTeam;
import domain.repository.RealTeamRepository;
import service.RealTeamService;

@Service
public class RealTeamServiceImpl implements RealTeamService{
	
	@Autowired
	private RealTeamRepository realTeamRepository;
	
	public List<RealTeam> getAllRealTeams(){
		return realTeamRepository.getAllRealTeams();
	}
	
	public RealTeam getRealTeam(Long realTeamId) {
		return realTeamRepository.getRealTeam(realTeamId);
	}
	
	public void addRealTeam(RealTeam realTeam) {
		realTeamRepository.addRealTeam(realTeam);
	}
	
	public void deleteRealTeam(Long realTeamId) {
		realTeamRepository.deleteRealTeam(realTeamId);
	}
}
