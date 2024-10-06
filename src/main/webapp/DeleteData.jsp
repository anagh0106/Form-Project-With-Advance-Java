<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<div class="container">
        <div class="text">
            By Using EMail_ID You Can Delete Your Data
        </div>
        <form action="DeleteDataServlet" method="POST">
            <div class="form-row">
                <div class="input-data">
                    <input type="email" name="email" autocomplete="off">
                    <div class="underline"></div>
                    <label for="">EMail_ID</label>
                    <!-- Display error message -->
                    <div class="error-message" style="color: red;">
                   </div>
                </div>
			</div>
			
             <div class="form-row submit-btn" style="padding-top: 20px">
               <div class="input-data">
                  <div class="inner"></div>
                  <input type="submit" value="Delete">
               </div>
               <div class="input-data">
                  <div class="inner"></div>
                  <input type="reset" value="clear">
               </div>
              </div>
         </form>
	</div>
</body>
</html>