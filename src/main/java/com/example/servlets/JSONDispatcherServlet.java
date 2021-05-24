package com.example.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.UserController;
import com.example.model.User;
//import com.example.controller.VillainController;
//import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcherServlet {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 case "/ExpenseReimbursementSystem/getusersession.json":
			// VillainController.getSessionVill(req, res);
			 UserController.getUserSession(req, res);
			 break;
		 case "/ExpenseReimbursementSystem/getexpenselist.json":
			 UserController.getExpenseList(req, res);
			 break;
			 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		
		}
	}

}