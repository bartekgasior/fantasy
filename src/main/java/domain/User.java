package domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User{
	@Size(min=3, max=30, message="{Size.User.username.validation}")
	private String username;
	@Size(min=3, max=30, message="{Size.User.password.validation}")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "{Pattern.User.password.validation}")
	private String password;
	private String user_role;
	private Long userId;

	public User() {
		super();
	}
	
	public User(String username, Long userId, String password, String role) {
	        this.username = username;
	        this.userId = userId;
	        this.password = password;
	        this.user_role = role;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
