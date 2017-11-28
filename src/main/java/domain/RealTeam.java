package domain;

public class RealTeam {
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
