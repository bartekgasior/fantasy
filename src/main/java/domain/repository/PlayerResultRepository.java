package domain.repository;

import java.util.List;

import domain.PlayerResult;

public interface PlayerResultRepository {
	void addResult(PlayerResult result);
	List<PlayerResult> getAllResults();
}
