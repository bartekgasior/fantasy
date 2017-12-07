package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Player;
import domain.repository.PlayerRepository;
import service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	private PlayerRepository playerRepository;

	public List<Player> getAllPlayers(){
		return playerRepository.getAllPlayers();
	}
	
	public List<Player> getRealTeamPlayers(Long realTeamId){
		return playerRepository.getRealTeamPlayers(realTeamId);
	}
	
	public List<Player> getPlayersByPage(int pageId, int rows){
		return playerRepository.getPlayersByPage(pageId, rows);
	}
	
	public void addPlayer(Player player) {
		playerRepository.addPlayer(player);
	}
}
