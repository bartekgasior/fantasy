package service;

import java.util.List;

import domain.MatchPlayer;

public interface MatchPlayerService {
	List<MatchPlayer> getAllMatchPlayers();
	void addMatchPlayer(MatchPlayer matchPlayer);
}
