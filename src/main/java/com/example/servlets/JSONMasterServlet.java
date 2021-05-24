package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSONMasterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		  res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	        res.setHeader("Pragma", "no-cache"); 
	        res.setHeader("Expires", "0"); 
		JSONDispatcherServlet.process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		  res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	        res.setHeader("Pragma", "no-cache"); 
	        res.setHeader("Expires", "0"); 
		JSONDispatcherServlet.process(req, res);
	}
}