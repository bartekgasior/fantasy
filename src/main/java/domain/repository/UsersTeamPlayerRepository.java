package domain.repository;

import java.util.List;

import domain.UsersTeamPlayer;

public interface UsersTeamPlayerRepository {
	void add(UsersTeamPlayer usersTeamPlayer);
	List<UsersTeamPlayer> getAll();
}
