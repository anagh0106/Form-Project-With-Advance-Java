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
	
	String fname=(String)request.getAttribute("fname");
	String lname=(String)request.getAttribute("lname");
	String mail=(String)request.getAttribute("mail");
	String pass=(String)request.getAttribute("pass");
	String UpdateError=(String) request.getAttribute("UpdateError");
	
	String realmail=(String)request.getAttribute("mymail");
	%>
	  <% if (fname != null && lname != null && mail != null && pass != null && mail.equals(realmail)) { %>
	  		<h1>Your Data Is Following</h1>
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
          
        <% } else { %>
            <h1>Sorry We Are Unable To Find You &#128517;</h1>
        <% } %>
</body>
</html>