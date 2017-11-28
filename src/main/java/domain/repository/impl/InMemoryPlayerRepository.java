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

import domain.Player;
import domain.User;
import domain.repository.PlayerRepository;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {
	
	private ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	
	@Autowired
	private DataSource dataSource;
	
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
		String query = "INSERT INTO player (name,surname,player_fee,position,country_id,real_team_id) VALUES (?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, player.getName());
			ps.setString(2, player.getSurname());
			ps.setDouble(3, player.getPlayer_fee());
			ps.setString(4, player.getPosition());
			ps.setLong(6, player.getReal_team_id());
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
}
