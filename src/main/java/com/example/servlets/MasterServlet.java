package com.example.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
        //System.out.println("In master doGet");
        try {
        	  res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
              res.setHeader("Pragma", "no-cache"); 
              res.setHeader("Expires", "0"); 
            req.getRequestDispatcher(RequestDispatcher.process(req)).forward(req,res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
       // System.out.println("In master doPost");

        try {
        	  res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
              res.setHeader("Pragma", "no-cache"); 
              res.setHeader("Expires", "0"); 
            req.getRequestDispatcher(RequestDispatcher.process(req)).forward(req,res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}