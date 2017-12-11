package service;

import java.util.List;

import domain.Position;

public interface PositionService {
	List<Position> getAllPositions();
	Position getPositionById(int id);
}
