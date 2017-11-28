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
	
	public void addPlayer(Player player) {
		playerRepository.addPlayer(player);
	}
}
