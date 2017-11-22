package domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.User;
import domain.repository.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository{
	private List<User> listOfUsers = new ArrayList<User>();
	
	@Autowired
	private DataSource dataSource;
	
	public InMemoryUserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<User> getAllUsers(){
		return listOfUsers;
	}
	
	public void addUser(User user) {
		String query = "INSERT INTO users (username, password) VALUES (?, ?)";
		
		System.out.println(user.getUsername());
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		listOfUsers.add(user);
	}
}
