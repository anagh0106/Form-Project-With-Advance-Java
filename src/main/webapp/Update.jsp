<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Update Information</title>
</head>
<body>

	 <%
	 
    // Retrieve any error messages and previously entered values from the request
    String ferror = (String) request.getAttribute("ferror");
    String lerror = (String) request.getAttribute("lerror");
    String mailerror = (String) request.getAttribute("mailerror");
    String passerror = (String) request.getAttribute("passerror");

    String fname = (String) request.getAttribute("fname");
    String lname = (String) request.getAttribute("lname");
    String mail = (String) request.getAttribute("mail");
    String pass = (String) request.getAttribute("pass");
    
    %>
    
    
	<div class="container">
        <div class="text">
            Update Information Form
        </div>
        <form action="UpdateData" method="POST">
            <div class="form-row">
                <div class="input-data">
                    <input type="text" name="fname" autocomplete="off" value="<%=fname == null ? "" : fname%>"
                        >
                    <div class="underline"></div>
                    <label for="">First Name</label>
                    <!-- Display error message -->
                    <div class="error-message" style="color: red;">
                        <%= ferror != null ? ferror : "" %>
                    </div>
                </div>

                <div class="input-data">
                    <input type="text" name="lname" autocomplete="off" value="<%=lname == null ? "" : lname%>"
                        >
                    <div class="underline"></div>
                    <label for="">Last Name</label>
                    <!-- Display error message -->
                    <div class="error-message" style="color: red;">
                        <%= lerror != null ? lerror : "" %>
                    </div>
                </div>
            </div>

            <div class="form-row">

				<div class="input-data">
                    <input type="email" name="mail"autocomplete="off" value="<%=mail== null ? "" : mail%>"
                        >
                    <div class="underline"></div>
                    <label for="">EmailId</label>
                    <!-- Display error message -->
                    <div class="error-message" style="color: red;">
                        <%= mailerror != null ? mailerror : "" %>
                    </div>
                </div>

                <div class="input-data">
                    <input type="password" name="pass" autocomplete="off" value="<%=pass == null ? "" : pass%>"
                        >
                    <div class="underline"></div>
                    <label for="">Password</label>
                    <!-- Display error message -->
                    <div class="error-message" style="color: red;">
                        <%= passerror != null ? passerror : "" %>
                    </div>
                </div>
            </div>

             <div class="form-row submit-btn" style="padding-top: 20px">
               <div class="input-data">
                  <div class="inner"></div>
                  <input type="submit" value="Update">
               </div>
               <div class="input-data">
                  <div class="inner"></div>
                  <input type="reset" value="clear">
               </div>
               <div class="input-data">
                  <div class="inner"></div>
                  <input type="button" value="Delete" id="b">
               </div>
            </div>
        </form>
    </div>
	
	<script src="Script1.js"></script>
</body>
</html>