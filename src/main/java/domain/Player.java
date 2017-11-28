package domain;

public class Player {
	private Long id;
	private String name;
	private String surname;
	private double player_fee;
	private String position;
	
	private Long real_team_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public double getPlayer_fee() {
		return player_fee;
	}
	public void setPlayer_fee(double player_fee) {
		this.player_fee = player_fee;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Long getReal_team_id() {
		return real_team_id;
	}
	public void setReal_team_id(Long real_team_id) {
		this.real_team_id = real_team_id;
	}
	
}
