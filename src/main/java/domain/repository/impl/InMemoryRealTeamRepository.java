package domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import domain.RealTeam;
import domain.repository.RealTeamRepository;

@Repository
public class InMemoryRealTeamRepository implements RealTeamRepository{
	
	private List<RealTeam> listOfRealTeams = new ArrayList<RealTeam>();
	
	private DataSource dataSource;
	
	public InMemoryRealTeamRepository(DataSource dataSource) {
		this.dataSource = dataSource;
		
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
		RealTeam rt = new RealTeam();
		rt.setName("Chelsea");
		listOfRealTeams.add(rt);
		listOfRealTeams.add(rt);
	}
	
	
	public List<RealTeam> getAllRealTeams(){
		return listOfRealTeams;
	}
}
