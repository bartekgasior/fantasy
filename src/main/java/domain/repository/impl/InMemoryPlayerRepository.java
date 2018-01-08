package domain.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Player;
import domain.RealTeam;
import domain.repository.PlayerRepository;
import service.RealTeamService;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {
	
	private ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private RealTeamService realTeamService;
	
	public InMemoryPlayerRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Player> getAllPlayers(){
		listOfPlayers.removeAll(listOfPlayers);
		String query = "SELECT * FROM player;";		
		
		Player player = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				player = new Player();
				player.setId(rs.getLong("id"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setPlayer_fee(rs.getDouble("player_fee"));
				player.setPosition(rs.getString("position"));
				player.setReal_team_id(rs.getLong("real_team_id"));
				listOfPlayers.add(player);
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
		return listOfPlayers;
	}
	
	public void addPlayer(Player player) {
		String query = "INSERT INTO player (name, surname, player_fee, position, real_team_id) VALUES (?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, player.getName());
			ps.setString(2, player.getSurname());
			ps.setDouble(3, player.getPlayer_fee());
			ps.setString(4, player.getPosition());
			ps.setLong(5, player.getReal_team_id());
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
		
		listOfPlayers.add(player);
	}

	public List<Player> getRealTeamPlayers(Long realTeamId){
		listOfPlayers.removeAll(listOfPlayers);
		String query = "SELECT * FROM player WHERE real_team_id = ?;";		
		
		Player player = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, realTeamId);
			rs = ps.executeQuery();
			while(rs.next()) {
				player = new Player();
				player.setId(rs.getLong("id"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setPlayer_fee(rs.getDouble("player_fee"));
				player.setPosition(rs.getString("position"));
				player.setReal_team_id(rs.getLong("real_team_id"));
				listOfPlayers.add(player);
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
		return listOfPlayers;
	}
	
	public List<Player> getPlayersByPage(int pageId, int rows){
		listOfPlayers.removeAll(listOfPlayers);
		String query = "SELECT * FROM player LIMIT " + (pageId - 1)*rows + "," + rows;		
		
		Player player = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				player = new Player();
				player.setId(rs.getLong("id"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setPlayer_fee(rs.getDouble("player_fee"));
				player.setPosition(rs.getString("position"));
				player.setReal_team_id(rs.getLong("real_team_id"));
				listOfPlayers.add(player);
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
		return listOfPlayers;
	}
	
	public List<Player> getPlayersByPosition(String position){
		List<Player> players = new ArrayList<>();
		
		String query = "SELECT * FROM player WHERE position = ?";		
		
		Player player = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, position);
			rs = ps.executeQuery();
			while(rs.next()) {
				player = new Player();
				player.setId(rs.getLong("id"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setPlayer_fee(rs.getDouble("player_fee"));
				player.setPosition(rs.getString("position"));
				player.setReal_team_id(rs.getLong("real_team_id"));
				players.add(player);
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
		return players;
	}
	
	public Set<Player> getPlayersByFilter(Map<String, List<String>> filterParams){
		List<Player> players = getAllPlayers();
		Set<Player> playersByRealTeam = new HashSet<Player>();
		Set<Player> playersByPosition = new HashSet<Player>();
		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("realTeam")) {
			for(String realTeamTmp: filterParams.get("realTeam")){
				for(Player player: players) {
					String tmp = realTeamService.getRealTeam(player.getReal_team_id()).getName();
					
					if(realTeamTmp.equalsIgnoreCase(tmp)) 
						playersByRealTeam.add(player);
				}
			}
		}
		
		if(criterias.contains("position")) {
			for(String positionTmp: filterParams.get("position")){
				for(Player player: players) {
					if(positionTmp.equalsIgnoreCase(player.getPosition()))
						playersByPosition.add(player);
				}
			}
		}
		
		if(playersByPosition.size() == 0)
			return playersByRealTeam;
		else if(playersByRealTeam.size() == 0)
			return playersByPosition;
		else {
			playersByRealTeam.retainAll(playersByPosition);
			return playersByRealTeam;
		}
	}
	
	public void deletePlayer(Long playerId) {
		String query = "DELETE FROM player WHERE id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, playerId);
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
	
	public Player getPlayerById(Long id) {
		String query = "SELECT * FROM player WHERE id = ?";
		
		Player player = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				player = new Player();
				player.setId(rs.getLong("id"));
				player.setName(rs.getString("name"));
				player.setSurname(rs.getString("surname"));
				player.setPlayer_fee(rs.getDouble("player_fee"));
				player.setPosition(rs.getString("position"));
				player.setReal_team_id(rs.getLong("real_team_id"));
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
		
		return player;
	}
	
	public void updatePlayer(Player player) {
		String query = "UPDATE player SET name = ?, surname =?, player_fee=?, position=?, real_team_id=? WHERE id = ? ";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, player.getName());
			ps.setString(2, player.getSurname());
			ps.setDouble(3, player.getPlayer_fee());
			ps.setString(4, player.getPosition());
			ps.setLong(5, player.getReal_team_id());
			ps.setLong(6, player.getId());
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
