package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.UsersTeamPlayer;
import domain.repository.UsersTeamPlayerRepository;
import service.UsersTeamPlayerService;

@Service
public class UsersTeamPlayerServiceImpl implements UsersTeamPlayerService{

	@Autowired
	private UsersTeamPlayerRepository usersTeamPlayerRepository;
	
	public void add(UsersTeamPlayer usersTeamPlayer) {
		usersTeamPlayerRepository.add(usersTeamPlayer);
	}
	
	public List<UsersTeamPlayer> getAll(){
		return usersTeamPlayerRepository.getAll();
	}
}
