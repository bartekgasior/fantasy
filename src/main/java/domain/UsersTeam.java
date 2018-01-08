package domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="users_team")
public class UsersTeam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(min=3, max=30, message="{Size.UsersTeam.name.validation}")
	private String name;
	@Column(name="user_id", columnDefinition = "INT(11)")
	private Long userId;
	private int score;
	@Column(name="teamSelected")
	private int teamSelected;
	
	public UsersTeam() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getTeamSelected() {
		return teamSelected;
	}

	public void setTeamSelected(int teamSelected) {
		this.teamSelected = teamSelected;
	}

}
