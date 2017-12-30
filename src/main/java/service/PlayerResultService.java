package service;

import java.util.List;

import domain.PlayerResult;

public interface PlayerResultService{

	void addResult(PlayerResult result);
	List<PlayerResult> getAllResults();
}
