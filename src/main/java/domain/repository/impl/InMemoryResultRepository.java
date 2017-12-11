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

import domain.Result;
import domain.repository.ResultRepository;

@Repository
public class InMemoryResultRepository implements ResultRepository{
	
	private ArrayList<Result> listOfResults = new ArrayList<Result>();
	
	@Autowired
	private DataSource dataSource;
	
	public InMemoryResultRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public List<Result> getResultsNames(){
		listOfResults.removeAll(listOfResults);
		String query = "SELECT * FROM result";		
		
		Result result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				result=new Result();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("result_type"));
				listOfResults.add(result);
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
		return listOfResults;
	}
	
}
