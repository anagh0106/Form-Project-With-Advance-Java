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

@WebServlet("/DeleteDataServlet")
public class DeleteDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String dburl = "com.mysql.cj.jdbc.Driver";
        String dblink = "jdbc:mysql://localhost:3306/AnaghDB";
        String dbuser = "root";
        String dbpass = "root";

        try {

            Class.forName(dburl);

            Connection c = DriverManager.getConnection(dblink, dbuser, dbpass);

            // Fixing the issue: Set the email for the DELETE statement
            String q = "DELETE FROM userform WHERE Email_ID = ?";
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, email);  // Set the email parameter

            int count = ps.executeUpdate();

            if (count > 0) {
                req.setAttribute("DeleteSuccess", "Your Data Has Been Deleted");

                // Prepare and set the email for the second query
                PreparedStatement ps1 = c.prepareStatement("SELECT * FROM userform WHERE Email_ID = ?");
                ps1.setString(1, email);  // Fixing the index for the email parameter

                ResultSet rs = ps1.executeQuery();

                if (rs != null && rs.next()) {
                    req.setAttribute("fname", rs.getString("Firstname"));
                    req.setAttribute("lname", rs.getString("Lastname"));
                    req.setAttribute("mail", rs.getString("Email_ID"));
                    req.setAttribute("pass", rs.getString("Password"));
                }
            } else {
                req.setAttribute("UpdateError", "Something went wrong!!");
            }
            req.getRequestDispatcher("DeleteSuccess.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
