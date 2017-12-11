package domain.repository;

import java.util.List;

import domain.Position;

public interface PositionRepository {
	List<Position> getAllPositions();
	Position getPositionById(int id);
}
