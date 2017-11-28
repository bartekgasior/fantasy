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
	private ArrayList<User> listOfUsers = new ArrayList<User>();
	
	@Autowired
	private DataSource dataSource;
	
	public InMemoryUserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<User> getAllUsers(){
		listOfUsers.removeAll(listOfUsers);
		String query = "SELECT * FROM users WHERE user_role = 'ROLE_USER';";		
		
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setUser_role(rs.getString("user_role"));
				user.setUserId(rs.getLong("id"));
				listOfUsers.add(user);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close(); ps.close(); con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfUsers;
	}
	
	public void addUser(User user) {
		String query = "INSERT INTO users (username, password) VALUES (?, ?)";
		
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
	
	public void deleteUser(Long userId) {
		String query = "DELETE FROM users WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, userId);
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
	}
}
