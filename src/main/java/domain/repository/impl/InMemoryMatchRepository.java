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

import domain.Match;
import domain.Player;
import domain.repository.MatchRepository;

@Repository
public class InMemoryMatchRepository implements MatchRepository{
	
	private ArrayList<Match> listOfMatches = new ArrayList<Match>();
	
	@Autowired
	private DataSource dataSource;
	
	public InMemoryMatchRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addMatch(Match match) {
		String query = "INSERT INTO match_tab (home_team_id, away_team_id, home_team_score, away_team_score, result_id) VALUES (?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, match.getHome_team_id());
			ps.setLong(2, match.getAway_team_id());
			ps.setInt(3, match.getHome_team_score());
			ps.setInt(4,  match.getAway_team_score());
			ps.setInt(5, match.getResult_id());
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
		
		listOfMatches.add(match);
	}
	
	public List<Match> getAllMatches(){
		listOfMatches.removeAll(listOfMatches);
		String query = "SELECT * FROM match_tab;";		
		
		Match match = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				match = new Match();
				match.setId(rs.getInt("id"));
				match.setHome_team_id(rs.getInt("home_team_id"));
				match.setAway_team_id(rs.getInt("away_team_id"));
				match.setHome_team_score(rs.getInt("home_team_score"));
				match.setAway_team_score(rs.getInt("away_team_score"));
				match.setResult_id(rs.getInt("result_id"));
				listOfMatches.add(match);
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
		return listOfMatches;
	}
	
	public List<Match> getMatchesByPage(int pageId, int rows){
		listOfMatches.removeAll(listOfMatches);
		String query = "SELECT * FROM match_tab LIMIT " + (pageId - 1)*rows + "," + rows;	
		
		Match match = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				match = new Match();
				match.setId(rs.getInt("id"));
				match.setHome_team_id(rs.getInt("home_team_id"));
				match.setAway_team_id(rs.getInt("away_team_id"));
				match.setHome_team_score(rs.getInt("home_team_score"));
				match.setAway_team_score(rs.getInt("away_team_score"));
				match.setResult_id(rs.getInt("result_id"));
				listOfMatches.add(match);
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
		return listOfMatches;
	}
	
	public Match findMatchByTeamsId(int homeTeamId, int awayTeamId) {	
		String query = "SELECT * FROM match_tab WHERE home_team_id=? AND away_team_id=?;";		
		
		Match match = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, homeTeamId);
			ps.setInt(2, awayTeamId);
			rs = ps.executeQuery();
			while(rs.next()) {
				match = new Match();
				match.setId(rs.getInt("id"));
				match.setHome_team_id(rs.getInt("home_team_id"));
				match.setAway_team_id(rs.getInt("away_team_id"));
				match.setHome_team_score(rs.getInt("home_team_score"));
				match.setAway_team_score(rs.getInt("away_team_score"));
				match.setResult_id(rs.getInt("result_id"));
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
		
		return match;
	}
	
	public void deleteMatch(Long id) {
		String query = "DELETE FROM match_tab WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
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
