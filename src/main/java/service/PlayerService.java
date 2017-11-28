package service;

import java.util.List;

import domain.Player;

public interface PlayerService {
	List<Player> getAllPlayers();
	void addPlayer(Player player);
}
