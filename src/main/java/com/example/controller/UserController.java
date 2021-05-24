package com.example.controller;

import com.example.dao.ReimbursementDaoImpl;
import com.example.dao.UserDaoImpl;
import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController
{
    static UserService userService = new UserService(new UserDaoImpl());
    //private UserDaoImpl userDao = new UserDaoImpl();


    public static String login(HttpServletRequest req) throws SQLException {
        //System.out.println("in login");
        
        
        if(!req.getMethod().equals("POST"))
        {
            return "html/index.html";
        }

        User ersUser = userService.getUserVerify(req.getParameter("userName"), req.getParameter("userPassword"));

        if(ersUser == null)
        {
            return "wrongcreds.change";
        }
        else
        {
        	
        	if(ersUser.getRole().getRole().equals("Manager"))
        	{
        		req.getSession().setAttribute("currentUser", ersUser);
                System.out.println("in else statement"+ersUser);
                return "html/manager/home.html";
        	}
        	else
        	{
        		req.getSession().setAttribute("currentUser", ersUser);
                System.out.println("in else statement"+ersUser);
                return "html/employee/home.html";
        	}
          
        }
        //return null;
    }
    
    

    public static String register(HttpServletRequest request)
    {
    	
    	UserDaoImpl userDao = new UserDaoImpl();
    	
    	System.out.println("In Register");
    
        String firstName = request.getParameter("userFirstName");
        String lastName = request.getParameter("userLastName");
        String username = request.getParameter("userUserName");
        String password = request.getParameter("userPassword");
        String email = request.getParameter("userEmail");
        String user_role = "Employee";

        User ers_users = new User(username, password, firstName, lastName, email, new UserRole(user_role));

        boolean register = userDao.createAccount(ers_users);
		if(register)
		{
		    return "html/register/successful.html";
		}
		else
		{
		    return "html/register/unsuccessful.html";
		}
    }
    
    public static void getUserSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException
    {
    	
    	User ersUser = (User)req.getSession().getAttribute("currentUser");
    	System.out.println("in getUserSession"+ersUser);
    	res.getWriter().write(new ObjectMapper().writeValueAsString(ersUser));
    }
    
    //gets the list of expenses
    public static void getExpenseList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException
    {
    	User ersUser = (User)req.getSession().getAttribute("currentUser");
    	ReimbursementDaoImpl reimbDaoImpl = new ReimbursementDaoImpl();
    	
    	
    	
    	if(ersUser.getRole().getRole().equals("Manager")) 
    	{
    		List<Reimbursement> reimbList = reimbDaoImpl.getAllReimbursements();
    		System.out.println(reimbList);
    		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbList));
    	}
    	else
    	{
    		List<Reimbursement> reimbList = reimbDaoImpl.getReimbursementsByUser(ersUser);
    		System.out.println(reimbList);
    		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbList));
    	}
    	
    }
    
    public static String setReimbursementStatus(HttpServletRequest req)
    {
    	User ersUser = (User)req.getSession().getAttribute("currentUser");
    	ReimbursementDaoImpl reimbDaoImpl = new ReimbursementDaoImpl();
    	
    	String reimb_idString = req.getParameter("reimb_id");
    	String reimb_status = req.getParameter("reimb_status");
    	int reimb_id = Integer.parseInt(reimb_idString);
    	
    	
    	boolean setReimbStatus = reimbDaoImpl.setReimbursementStatus(reimb_status, reimb_id, ersUser);
    	
    	if(setReimbStatus)
    	{
    		return "html/manager/home.html";
    	}
    	else
    	{
    		return "html/manager/unsuccessfullchange.html";
    	}
    	
    	
    }
    
    public static String setReimbursement(HttpServletRequest req)
    {
    	User ersUser = (User)req.getSession().getAttribute("currentUser");
    	ReimbursementDaoImpl reimbDaoImpl = new ReimbursementDaoImpl();
    	
    	String reimb_amountString = req.getParameter("reimb_amount");
    	double reimb_amount = Double.parseDouble(reimb_amountString);
    	String reimb_description = req.getParameter("reimb_description");
    	String reimb_typeString = req.getParameter("reimb_type");
    	System.out.println("reimb String" + reimb_typeString);
    	int reimb_type = Integer.parseInt(reimb_typeString);
    	
    	
    	
    	
    	boolean setReimb =reimbDaoImpl.setReimbursement(reimb_amount, reimb_description, ersUser.getId(), reimb_type);
    	
    	if(setReimb)
    	{
    		return "html/employee/home.html";
    	}
    	else
    	{
    		return "html/employee/unsuccessfulladd.html";
    	}
    	
    	
    	// "";
    }
    
   
}




////public static Map<String, Object> createList(List<Reimbursement> reimbList) 
////{
////	
////	
////	return null;
//}