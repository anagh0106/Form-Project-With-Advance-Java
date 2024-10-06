<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
		 	String DeleteSuccess = (String) request.getAttribute("DeleteSuccess");
        	String DeleteError = (String) request.getAttribute("DeleteError");
		%>
		<% if (DeleteSuccess != null) { %>
            <p style="color: green;"><%= DeleteSuccess %></p>
        <% } %>

        <% if (DeleteError != null) { %>
            <p style="color: red;"><%= DeleteError %></p>
        <% } %>
		
</body>
</html>