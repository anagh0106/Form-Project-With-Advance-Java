<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Status</title>
</head>
<body>
    <%
        String updateSuccess = (String) request.getAttribute("UpdateSuccess");
        String updateError = (String) request.getAttribute("UpdateError");
        String fname = (String) request.getAttribute("dbfname");
        String lname = (String) request.getAttribute("dblname");
        String mail = (String) request.getAttribute("dbmail");
        String pass = (String) request.getAttribute("dbpass");
        
        String realmail= (String) request.getAttribute("mail_");
     %>

    <div>
        <% if (updateSuccess != null) { %>
            <p style="color: green;"><%= updateSuccess %></p>
        <% } %>

        <% if (updateError != null) { %>
            <p style="color: red;"><%= updateError %></p>
        <% } %>

        <!-- Only show table if data is available -->
        <% if (fname != null && lname != null && mail != null && pass != null && mail.equals(realmail)) { %>
            <table border="1">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td><%= fname != null ? fname : "No data" %></td>
                    <td><%= lname != null ? lname : "No data" %></td>
                    <td><%= mail != null ? mail : "No data" %></td>
                    <td><%= pass != null ? pass : "No data" %></td>
                </tr>
            </table>
            <h1>Your Record Has Been Updated!</h1>
        <% } else { %>
            <p>Email_ID Not Found!!.</p>
        <% } %>
    </div>
</body>
</html>
