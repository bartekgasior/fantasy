package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="match_player")
public class MatchPlayer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="match_id", columnDefinition = "INT(11)")
	private Long matchId;
	@Column(name="player_id", columnDefinition = "INT(11)")
	private Long playerId;
	@Column(name="player_result_id")
	private int result;
	
	public MatchPlayer() {
		super();
	}
	
	public MatchPlayer(Long matchId, Long playerId, int result) {
		this.matchId = matchId;
		this.playerId = playerId;
		this.result = result;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
