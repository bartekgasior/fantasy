package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.MatchPlayer;
import domain.repository.MatchPlayerRepository;
import service.MatchPlayerService;

@Service
public class MatchPlayerServiceImpl implements MatchPlayerService{
	
	@Autowired
	private MatchPlayerRepository matchPlayerRepository;
	
	public List<MatchPlayer> getAllMatchPlayers(){
		return matchPlayerRepository.getAllMatchPlayers();
	}
	
	public void addMatchPlayer(MatchPlayer matchPlayer) {
		matchPlayerRepository.addMatchPlayer(matchPlayer);
	}
}
