package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.PlayerResult;
import domain.repository.PlayerResultRepository;
import service.PlayerResultService;

@Service
public class PlayerResultServiceImpl implements PlayerResultService {

	@Autowired
	private PlayerResultRepository playerResultRepository;
	
	public void addResult(PlayerResult result) {
		playerResultRepository.addResult(result);
	}
	
	public List<PlayerResult> getAllResults(){
		return playerResultRepository.getAllResults();
	}
}
