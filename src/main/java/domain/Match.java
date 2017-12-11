package domain;

public class Match {
	private int id;
	private int home_team_id;
	private int away_team_id;
	private int home_team_score;
	private int away_team_score;
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
