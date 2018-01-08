package domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Player {
	private Long id;
	@Size(min=3, max=30, message="{Size.Player.name.validation}")
	private String name;
	@Size(min=3, max=30, message="{Size.Player.surname.validation}")
	private String surname;
	@Min(value=0, message="{Min.Player.player_fee.validation}")
	@Digits(integer=5, fraction=0, message="{Digits.Player.player_fee.validation}")
	@NotNull(message="{NotNull.Player.player_fee.validation}")
	private double player_fee;
	@NotNull(message="{NotNull.Player.position.validation}")
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
