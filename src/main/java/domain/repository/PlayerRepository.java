package domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Player;

public interface PlayerRepository {
	List<Player> getAllPlayers();
	List<Player> getRealTeamPlayers(Long realTeamId);
	List<Player> getPlayersByPage(int pageId, int rows);
	Set<Player> getPlayersByFilter(Map<String, List<String>> filterParams);
	void addPlayer(Player player);
	void deletePlayer(Long playerId);
}
