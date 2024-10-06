package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AllDataServlet")
public class AllDataServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		 try {
	            // Database connection
	            String dbDriver = "com.mysql.cj.jdbc.Driver";
	            String dbLink = "jdbc:mysql://localhost:3306/AnaghDB";
	            String dbUser = "root";
	            String dbPass = "root";

	            // Load driver and create connection
	            Class.forName(dbDriver);
	            Connection c = DriverManager.getConnection(dbLink, dbUser, dbPass);

	            // Create statement and execute query
	            PreparedStatement ps = c.prepareStatement("SELECT * FROM userform");
	            ResultSet rs = ps.executeQuery();

	            // Set ResultSet as attribute and forward to JSP
	            req.setAttribute("rs", rs);
	            req.getRequestDispatcher("AllData.jsp").forward(req, res);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	
	}
}
