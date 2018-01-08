package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="league_users_team")
public class LeagueUsersTeam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", columnDefinition = "INT(11)")
	private Long id;
	
	@Column(name="league_id")
	private int leagueId;
	@Column(name="users_team_id")
	private int usersTeamId;
	@Column(name="users_team_user_id", columnDefinition = "INT(11)")
	private Long usersTeamUserId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public int getUsersTeamId() {
		return usersTeamId;
	}
	public void setUsersTeamId(int usersTeamId) {
		this.usersTeamId = usersTeamId;
	}
	public Long getUsersTeamUserId() {
		return usersTeamUserId;
	}
	public void setUsersTeamUserId(Long usersTeamUserId) {
		this.usersTeamUserId = usersTeamUserId;
	}
}
