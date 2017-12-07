package domain.repository;

import java.util.List;

import domain.Player;

public interface PlayerRepository {
	List<Player> getAllPlayers();
	List<Player> getRealTeamPlayers(Long realTeamId);
	List<Player> getPlayersByPage(int pageId, int rows);
	void addPlayer(Player player);
}
