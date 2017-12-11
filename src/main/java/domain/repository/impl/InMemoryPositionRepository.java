package domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.Position;
import domain.repository.PositionRepository;

@Repository
public class InMemoryPositionRepository implements PositionRepository{
	private ArrayList<Position> listOfPositions = new ArrayList<Position>();
	
	public InMemoryPositionRepository() {
		Position p1 = new Position(1, "GoalKeepers");
		Position p2 = new Position(2, "Defenders");
		Position p3 = new Position(3, "Midfielders");
		Position p4 = new Position(4, "Forwards");
		
		listOfPositions.add(p1);
		listOfPositions.add(p2);
		listOfPositions.add(p3);
		listOfPositions.add(p4);
	}
	
	public List<Position> getAllPositions(){
		return listOfPositions;
	}
	
	public Position getPositionById(int id) {
		Position position = null;
		for(Position pos : listOfPositions) {
			if(pos.getId() == id) {
				position = pos;
				break;
			}
		}
		if(position == null)
			throw new IllegalArgumentException("Brak pozycji o wskazanym id: " + id);
		
		return position;
	}
}
