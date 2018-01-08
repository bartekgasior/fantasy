package domain;

import javax.validation.constraints.Size;

public class RealTeam {
	@Size(min=3, max=30, message="{Size.RealTeam.name.validation}")
	private String name;
	private Long id;

	
	public RealTeam() {
		super();
	}
	
	public RealTeam(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
