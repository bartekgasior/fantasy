package domain.repository;

import java.util.List;

import domain.Player;

public interface PlayerRepository {
	List<Player> getAllPlayers();
	void addPlayer(Player player);
}
