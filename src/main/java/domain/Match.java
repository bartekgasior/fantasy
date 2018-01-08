package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="match_tab")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull(message="{NotNull.Match.home_team_id.validation}")
	@Column(name="home_team_id")
	private int home_team_id;
	@Column(name="away_team_id")
	private int away_team_id;
	
	@Min(value=0, message="{Min.Match.home_team_score.validation}")
	@Digits(integer=2, fraction=0, message="{Digits.Match.home_team_score.validation}")
	@NotNull(message="{NotNull.Match.home_team_score.validation}")
	@Column(name="home_team_score")
	private int home_team_score;
	
	@Min(value=0, message="{Min.Match.away_team_score.validation}")
	@Digits(integer=2, fraction=0, message="{Digits.Match.away_team_score.validation}")
	@NotNull(message="{NotNull.Match.away_team_score.validation}")
	@Column(name="away_team_score")
	private int away_team_score;
	
	@Column(name="result_id")
	private int result_id;
	
	public Match() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHome_team_id() {
		return home_team_id;
	}
	public void setHome_team_id(int home_team_id) {
		this.home_team_id = home_team_id;
	}
	public int getAway_team_id() {
		return away_team_id;
	}
	public void setAway_team_id(int away_team_id) {
		this.away_team_id = away_team_id;
	}
	public int getHome_team_score() {
		return home_team_score;
	}
	public void setHome_team_score(int home_team_score) {
		this.home_team_score = home_team_score;
	}
	public int getAway_team_score() {
		return away_team_score;
	}
	public void setAway_team_score(int away_team_score) {
		this.away_team_score = away_team_score;
	}
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	
}
