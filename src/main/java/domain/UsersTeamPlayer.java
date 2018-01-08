package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_team_player")
public class UsersTeamPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="player_id", columnDefinition = "INT(11)")
	private Long playerId;
	@Column(name="users_team_id", columnDefinition = "INT(11)")
	private int usersTeamId;
	
	public UsersTeamPlayer() {
		super();
	}
	
	public UsersTeamPlayer(Long playerId, int usersTeamId) {
		this.playerId = playerId;
		this.usersTeamId = usersTeamId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public int getUsersTeamId() {
		return usersTeamId;
	}
	public void setUsersTeamId(int usersTeamId) {
		this.usersTeamId = usersTeamId;
	}
}
