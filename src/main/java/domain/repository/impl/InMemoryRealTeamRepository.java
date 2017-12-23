package domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.RealTeam;
import domain.repository.RealTeamRepository;

@Repository
public class InMemoryRealTeamRepository implements RealTeamRepository{
	
	private List<RealTeam> listOfRealTeams = new ArrayList<RealTeam>();
	
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	
	public InMemoryRealTeamRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<RealTeam> getAllRealTeams(){
		listOfRealTeams.removeAll(listOfRealTeams);
		String query = "SELECT * FROM real_team;";
		
		RealTeam realTeam = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				realTeam = new RealTeam();
				realTeam.setName(rs.getString("name"));
				realTeam.setId(rs.getLong("id"));
				listOfRealTeams.add(realTeam);
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
		
		Collections.sort(listOfRealTeams, new Comparator<RealTeam>(){
			
			@Override
			public int compare(RealTeam rt1, RealTeam rt2) {
				// TODO Auto-generated method stub
				return rt1.getName().compareTo(rt2.getName());
			}
		});
		
		return listOfRealTeams;
	}
	
	public void addRealTeam(RealTeam realTeam) {
		String query = "INSERT INTO real_team (name) VALUES (?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, realTeam.getName());
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
				
		listOfRealTeams.add(realTeam);
	}
	
	public void deleteRealTeam(Long realTeamId) {
		String query = "DELETE FROM real_team WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, realTeamId);
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
	
	public RealTeam getRealTeam(Long realTeamId) {
		String query = "SELECT * FROM real_team WHERE id = ?";
		
		RealTeam realTeam = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, realTeamId);
			rs = ps.executeQuery();
			while(rs.next()) {
				realTeam = new RealTeam();
				realTeam.setName(rs.getString("name"));
				realTeam.setId(rs.getLong("id"));
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
		
		return realTeam;
	}
	
	public void updateRealTeam(RealTeam realTeam) {
		String query = "UPDATE real_team SET name = ? WHERE id = ? ";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, realTeam.getName());
			ps.setLong(2, realTeam.getId());
			ps.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				 ps.close(); con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}		

	}
}
