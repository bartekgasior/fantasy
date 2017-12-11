package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Position;
import domain.repository.PositionRepository;
import service.PositionService;

@Service
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Position> getAllPositions(){
		return positionRepository.getAllPositions();
	}
	
	public Position getPositionById(int id) {
		return positionRepository.getPositionById(id);
	}
}
