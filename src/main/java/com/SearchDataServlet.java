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

@WebServlet("/SearchDataServlet")
public class SearchDataServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			
			String mail=req.getParameter("mail");
			Boolean isError = false;
			
			req.setAttribute("mymail", mail);

			 if(mail==null || mail.trim().length()==0) {
					isError=true;
					req.setAttribute("mailerror", "Enter Email ID");
				}
				else {
					 String mailregEx = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
					if(mail.matches(mailregEx)==false) {
						isError=true;
						req.setAttribute("mailerror", "Enter Valid Email ID");
					}
				}
			
			 if (isError) {
		            req.getRequestDispatcher("SearchUser.jsp").forward(req, res);
		        }
			 else {
				 try {
				
					 	String dburl = "com.mysql.cj.jdbc.Driver";
			            String dblink = "jdbc:mysql://localhost:3306/AnaghDB";
			            String dbuser = "root";
			            String dbpass = "root";
				
			            Class.forName(dburl);
		                Connection c = DriverManager.getConnection(dblink, dbuser, dbpass);
		                
		                PreparedStatement ps=c.prepareStatement("select * from userform where email_id=?");
		                ps.setString(1, mail);
		                ResultSet rs=ps.executeQuery();
		                
		                if(rs!=null && rs.next()) {
		                	req.setAttribute("fname", rs.getString("Firstname"));
		                	req.setAttribute("lname", rs.getString("Lastname"));
		                	req.setAttribute("mail", rs.getString("EMail_ID"));
		                	req.setAttribute("pass", rs.getString("Password"));
		                }
		                else {
		                    req.setAttribute("UpdateError", "Something went wrong!!");
		                }
		                
		                req.getRequestDispatcher("SearchSuccess.jsp").forward(req, res);
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
			 }
	}
}
