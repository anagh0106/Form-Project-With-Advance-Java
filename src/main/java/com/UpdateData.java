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

@WebServlet("/UpdateData")
public class UpdateData extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        Boolean isError = false;

        // Set the input data as request attributes
        req.setAttribute("f_name", fname);
        req.setAttribute("l_name", lname);
        req.setAttribute("mail_", mail);
        req.setAttribute("pass_", pass);

        if (fname == null || fname.trim().length() == 0) {
            isError = true;
            req.setAttribute("ferror", "Enter First Name");
        } else {
            String fnameregEx = "[A-Za-z]{2,30}";
            if (!fname.matches(fnameregEx)) {
                isError = true;
                req.setAttribute("ferror", "Invalid First Name");
            }
        }
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

        if (isError) {
            req.getRequestDispatcher("Update.jsp").forward(req, res);
        } else {
            String dburl = "com.mysql.cj.jdbc.Driver";
            String dblink = "jdbc:mysql://localhost:3306/AnaghDB";
            String dbuser = "root";
            String dbpass = "root";

            try {
                Class.forName(dburl);
                Connection c = DriverManager.getConnection(dblink, dbuser, dbpass);

                // Update query
                String UpdateQuery = "UPDATE userform SET Lastname=?, Firstname=?, Password=? WHERE Email_Id=?";
                PreparedStatement psUpdate = c.prepareStatement(UpdateQuery);
                psUpdate.setString(1, lname);
                psUpdate.setString(2, fname);
                psUpdate.setString(3, pass);
                psUpdate.setString(4, mail);

                int count = psUpdate.executeUpdate();
                // logic to display data on JSP
                if (count > 0) {
                    req.setAttribute("UpdateSuccess", "Update Done Successfully");

                    // Retrieve the updated user data
                    PreparedStatement psSelect = c.prepareStatement("SELECT * FROM userform WHERE Email_Id=?");
                    psSelect.setString(1, mail);
                    ResultSet rs = psSelect.executeQuery();

                    if (rs != null && rs.next()) {
                        // Move cursor to the first row and set attributes
                        req.setAttribute("dbfname", rs.getString("Firstname"));
                        req.setAttribute("dblname", rs.getString("Lastname"));
                        req.setAttribute("dbmail", rs.getString("Email_ID"));
                        req.setAttribute("dbpass", rs.getString("Password"));
                    }
                } else {
                    req.setAttribute("UpdateError", "Something went wrong!!");
                }

                req.getRequestDispatcher("UpdateSuccess.jsp").forward(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
