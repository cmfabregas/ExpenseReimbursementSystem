package com.example.servlets;

import com.example.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

public class RequestDispatcher
{

    public static String process(HttpServletRequest req) throws SQLException {

        switch(req.getRequestURI())
        {
            case "/ExpenseReimbursementSystem/login.change":
                System.out.println("in Login.change dispatcher");
                return UserController.login(req);

            case "/ExpenseReimbursementSystem/register.change":
                System.out.println("In register.change");
                return UserController.register(req);
            
            case "/ExpenseReimbursementSystem/approve.change":
            	System.out.println("In approve.change");
            	return UserController.setReimbursementStatus(req);
            case "/ExpenseReimbursementSystem/add.change":
            	System.out.println("In add.change");
            	return UserController.setReimbursement(req);
            case "/ExpenseReimbursementSystem/logout.change":
            	System.out.println("In logout.change");
            	HttpSession session = req.getSession();
            	session.invalidate();
            	return "/html/index.html";
            	

            default:
                System.out.println("In Default");
                return "html/runsuccessfulllogin.html";
        }
    }
}
