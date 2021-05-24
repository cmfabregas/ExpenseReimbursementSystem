package com.example.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;
import com.example.model.UserRole;

public class UserDaoImpl
{

    private ERSDbConnection ersCon;

    public UserDaoImpl()
    {
        ersCon = new ERSDbConnection();
    }


    public boolean createAccount(User ersUser) 
    {

        String userRole  = ersUser.getRole().getRole();
        int roleId =0;

        if(userRole.equals("Manager"))
        {
            roleId = 1;
        }
        else if(userRole.equals("Employee"))
        {
            roleId = 2;
        }
        else
        {
            System.out.println("invalid user role");
            return false;
        }

        try(Connection con =  ersCon.getDbConnection())
        {

            String sql = "{? = call create_account(?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.registerOutParameter(1, Types.VARCHAR);

            cs.setString(2, ersUser.getUsername());
            cs.setString(3, ersUser.getPassword());
            cs.setString(4, ersUser.getFirst_name());
            cs.setString(5, ersUser.getLast_name());
            cs.setString(6, ersUser.getEmail());
            cs.setInt(7, roleId);

            cs.execute();


            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;

    }

    public List<User> getAllUsers()
    {

        List<User> userList= new ArrayList<User>();

        try(Connection con = ersCon.getDbConnection())
        {
            String sql = "select eu.user_id, eu.username, eu.password, eu.first_name, eu.last_name, eu.email, eu.user_role_id, ur.roletype "
                    + "from ers_users eu JOIN  user_role ur "
                    + "ON eu.user_role_id = ur.id";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();



            while(rs.next())
            {

                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), new UserRole(rs.getInt(7), rs.getString(8))));

            }

            return userList;

        } catch (SQLException e)
        {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }


    public User getByUsername(String username) 
    {


        try(Connection con = ersCon.getDbConnection())
        {
            User ersUser = new User();

            String sql = "SELECT eu.user_id, eu.username, eu.password, eu.first_name, eu.last_name, eu.email, eu.user_role_id, ur.roletype "
                    + "FROM ers_users eu "
                    + "JOIN  user_role ur "
                    + "ON eu.user_role_id = ur.id "
                    + "WHERE eu.username = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                //System.out.println(rs.getInt(getId()))
                ersUser =  new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), new UserRole(rs.getInt(7), rs.getString(8)));
            }

            return ersUser;

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    public User getByEmail(String email) 
    {

        try(Connection con = ersCon.getDbConnection())
        {
            User ersUser = new User();

            String sql = "SELECT eu.user_id, eu.username, eu.password, eu.first_name, eu.last_name, eu.email, eu.user_role_id, ur.roletype "
                    + "FROM ers_users eu "
                    + "JOIN  user_role ur "
                    + "ON eu.user_role_id = ur.id "
                    + "WHERE eu.email = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,email);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                ersUser =  new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), new UserRole(rs.getInt(7), rs.getString(8)));
            }

            return ersUser;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    public User getUserById(int user_id)
    {
    	 try(Connection con = ersCon.getDbConnection())
         {
             User ersUser = new User();

             String sql = "SELECT eu.user_id, eu.username, eu.password, eu.first_name, eu.last_name, eu.email, eu.user_role_id, ur.roletype "
                     + "FROM ers_users eu "
                     + "JOIN  user_role ur "
                     + "ON eu.user_role_id = ur.id "
                     + "WHERE eu.user_id = ?";

             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,user_id);

             ResultSet rs = ps.executeQuery();

             while(rs.next())
             {
                 ersUser =  new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), new UserRole(rs.getInt(7), rs.getString(8)));
             }

             return ersUser;
         } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         return null;
    }

    public int getId() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
}