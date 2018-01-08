package service;

import java.util.List;

import domain.UsersTeamPlayer;

public interface UsersTeamPlayerService {
	void add(UsersTeamPlayer usersTeamPlayer);
	List<UsersTeamPlayer> getAll();
}
