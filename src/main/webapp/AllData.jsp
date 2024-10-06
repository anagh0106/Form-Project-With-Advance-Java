<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
 	<div class="table-container">
	<table border="1" id="styledTable">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Password</th>
    </tr>
    <% 
    ResultSet rs = (ResultSet) request.getAttribute("rs");
    while (rs != null && rs.next()) { %>
        <tr>
            <td><%= rs.getString("Firstname") %></td>
            <td><%= rs.getString("Lastname") %></td>
            <td><%= rs.getString("EMail_ID") %></td>
            <td><%= rs.getString("Password") %></td>
        </tr>
    <% } %>
    </table>
	</div>
    
</body>
</html>