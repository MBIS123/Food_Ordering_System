<%-- 
    Document   : login
    Created on : Nov 15, 2023, 2:12:31 AM
    Author     : User
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <style>

            .usernameInput{
                margin-top: 40px; /* Adjust the value as needed */
            }

        </style>       
    </head>


    <body>
        <h1>Login</h1>
        <form action="LoginServlet" method="post">
            <div class ="usernameInput">
                <label for="username">Username:</label><br>
                <input type="text" id="username" name="username" required><br><br>
            </div>

            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" required><br><br>

            <input type="submit" value="Login"  class="submit-button" style="margin-right:8px;
                   margin-left:0px;">

            <a href="userRegisterLanding.jsp" class="submit-button" style=" font-size:14px; 
               text-decoration: none; margin-left:0px; ">Register</a>
        </form>
    </body>
</html>
