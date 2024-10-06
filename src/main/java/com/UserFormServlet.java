package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserFormServlet")
public class UserFormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
	
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String mail=req.getParameter("mail");
		String pass=req.getParameter("pass");
		
		Boolean isError=false;
		
		//regEx
		req.setAttribute("fname", fname);
		req.setAttribute("lname", lname);
		req.setAttribute("mail", mail);
		req.setAttribute("pass", pass);
		
		
		
		
		// fname
		if(fname==null || fname.trim().length()==0) {
			isError=true;
			req.setAttribute("ferror", "Enter First Name");
		}
		else{

			String fnameregEx="[A-Za-z]{2,30}";
			if(fname.matches(fnameregEx)==false) {
			isError=true;
			req.setAttribute("ferror", "Invalid FirstName");
		}
			}
		
		// lname
		if(lname==null || lname.trim().length()==0) {
			isError=true;
			req.setAttribute("lerror", "Enter Last Name");
		}
		else {
			String lnameregEx="[A-Za-z]{2,30}";
			if(lname.matches(lnameregEx)==false) {
				isError=true;
				req.setAttribute("lerror", "Invalid LastName");
			}
		}
		//mail
		
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
		
		
		// pass
		if(pass==null || pass.trim().length()==0) {
			isError=true;
			req.setAttribute("passerror", "Enter Password");
		}
		else {
			String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,10}$";

			if(pass.length()<8){
				isError=true;
				req.setAttribute("passerror", "Password lenght atleast 8");
			}
			
			else if(pass.matches(passRegex)==false) {
				isError=true;
				req.setAttribute("passerror", "Enter Valid Password");
			}
		}	
		
		// isError
		
		if(isError==true) {
			req.getRequestDispatcher("UserForm.jsp").forward(req, res);
		}
		// Validation
		else {
			
		String dburl="com.mysql.cj.jdbc.Driver";
		String dblink="jdbc:mysql://localhost:3306/AnaghDB";
		String dbuser="root";
		String dbpass="root";
	
		try {
		
			Class.forName(dburl);
			
			Connection c=DriverManager.getConnection(dblink,dbuser,dbpass);
			
			PreparedStatement ps=c.prepareStatement("insert into Userform values(?,?,?,?)");
			
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, mail);
			ps.setString(4, pass);
			ps.executeUpdate();
			res.sendRedirect("EntrySuccess.jsp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
  }
}
