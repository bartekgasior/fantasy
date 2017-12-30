package domain.repository;

import java.util.List;

import domain.MatchPlayer;

public interface MatchPlayerRepository {

	List<MatchPlayer> getAllMatchPlayers();
	void addMatchPlayer(MatchPlayer matchPlayer);
}
