package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="league")
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Size(min=3, max=30, message="{Size.League.name.validation}")
	@Column(name="name")
	private String name;
	
	@Min(value=0, message="{Min.League.startingMoney.validation}")
	@Digits(integer=5, fraction=0, message="{Digits.League.startingMoney.validation}")
	@NotNull(message = "{NotNull.League.startingMoney.validation}")
	@Column(name="starting_money", columnDefinition = "INT(11)")
	private Long startingMoney;
	
	@Column(name="league_admin_id", columnDefinition = "INT(11)")
	private Long adminId;
	
	@Min(value=1, message="{Min.League.slots.validation}")
	@Digits(integer=2, fraction=0, message="{Digits.League.slots.validation}")
	@NotNull(message="{NotNull.League.slots.validation}")
	@Column(name="slots")
	private int slots;
	
	public League() {
		super();
	}
	
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
	public Long getStartingMoney() {
		return startingMoney;
	}
	public void setStartingMoney(Long startingMoney) {
		this.startingMoney = startingMoney;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public int getSlots() {
		return slots;
	}
	public void setSlots(int slots) {
		this.slots = slots;
	}
	
}
