package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;
import com.example.model.ReimbursementStatus;
import com.example.model.ReimbursementType;
import com.example.model.User;

public class ReimbursementDaoImpl 
{

	private ERSDbConnection ersCon;
	public ReimbursementDaoImpl() {
		// TODO Auto-generated constructor stub
		ersCon = new ERSDbConnection();
	}
	
	public List<Reimbursement> getAllReimbursements()
	{
		
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try(Connection con = ersCon.getDbConnection())
		{
			
			String sql = "select er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description, er.reimb_author, er.reimb_resolver, er.reimb_status_id, er.reimb_type_id, ers.reimb_status ,ert.reimb_type "
					+ "from ers_reimbursement er "
					+ "left join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id "
					+ "left join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				int reimbAuthorId = rs.getInt(6);
				int reimbResolverId = rs.getInt(7);
				
				User reimbAuthor = new UserDaoImpl().getUserById(reimbAuthorId);
				User reimbResolver = new UserDaoImpl().getUserById(reimbResolverId);
				
				int reimbStatusId = rs.getInt(8);
				int reimbTypeId = rs.getInt(9);
				
				
				
				ReimbursementStatus reimbStatus = new ReimbursementStatus(reimbStatusId, rs.getString(10));
				ReimbursementType reimbType = new ReimbursementType(reimbTypeId, rs.getString(11));
				
				
				Reimbursement reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), null, reimbAuthor, reimbResolver, reimbStatus, reimbType);
				reimbList.add(reimb);
			}

			return reimbList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	public List<Reimbursement> getReimbursementsByUser(User ersUser)
	{
		
	List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try(Connection con = ersCon.getDbConnection())
		{
			
			String sql = "select er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description, er.reimb_author, er.reimb_resolver, er.reimb_status_id, er.reimb_type_id, ers.reimb_status ,ert.reimb_type "
					+ "from ers_reimbursement er "
					+ "left join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id "
					+ "left join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id "
					+ "where er.reimb_author = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ersUser.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				int reimbAuthorId = rs.getInt(6);
				int reimbResolverId = rs.getInt(7);
				
				User reimbAuthor = new UserDaoImpl().getUserById(reimbAuthorId);
				User reimbResolver = new UserDaoImpl().getUserById(reimbResolverId);
				
				int reimbStatusId = rs.getInt(8);
				int reimbTypeId = rs.getInt(9);
				
				
				
				ReimbursementStatus reimbStatus = new ReimbursementStatus(reimbStatusId, rs.getString(10));
				ReimbursementType reimbType = new ReimbursementType(reimbTypeId, rs.getString(11));
				
				
				Reimbursement reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), null, reimbAuthor, reimbResolver, reimbStatus, reimbType);
				reimbList.add(reimb);
			}

			return reimbList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String getReimbursementStatus(int reimb_status_id)
	{
		try(Connection con = ersCon.getDbConnection())
		{
			
			String sql = "select reimb_status from ers_reimbursement_status where reimb_status_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimb_status_id);
			
			ResultSet rs = ps.executeQuery();
			
			String reimbStatus = "";
			while(rs.next())
			{
				reimbStatus = rs.getString(1);
			}
			
			return reimbStatus;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getReimbursementType(int reimb_type_id)
	{
		try(Connection con = ersCon.getDbConnection())
		{
			
			String sql = "select reimb_type from ers_reimbursement_type where reimb_status_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimb_type_id);
			
			ResultSet rs = ps.executeQuery();
			
			String reimbType = "";
			while(rs.next())
			{
				reimbType = rs.getString(1);
			}
			
			return reimbType;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public boolean setReimbursementStatus(String status, int reimb_id, User user)
	{
		
		int reimb_status_id = 1;
		if(status.equals("approve"))
		{
			reimb_status_id = 2;
		}else
		{
			reimb_status_id = 3;
		}
		
		
		try(Connection con = ersCon.getDbConnection())
		{
			String sql = "update ers_reimbursement set reimb_status_id = ?, reimb_resolver = ?, reimb_resolved= ? where reimb_id =?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimb_status_id);
			ps.setInt(2, user.getId());
			ps.setTimestamp(3, Timestamp.from(ZonedDateTime.now().toInstant()));
			ps.setInt(4, reimb_id);
			
			int affectedRows = ps.executeUpdate();
			
			if(affectedRows == 0)
			{
				return false;
			}
			else
				return true;
		
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean setReimbursement(double reimb_amount, String reimb_description, int user_id, int reimb_type) 
	{
		try(Connection con = ersCon.getDbConnection())
		{
			
			String sql = "{? = call set_reimbursement(?, ?, ?, ?, ?)} ";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setDouble(2, reimb_amount);
			cs.setTimestamp(3, Timestamp.from(ZonedDateTime.now().toInstant()));
			cs.setString(4, reimb_description);
			cs.setInt(5, user_id);
			cs.setInt(6, reimb_type);
			
			cs.execute();
			
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
