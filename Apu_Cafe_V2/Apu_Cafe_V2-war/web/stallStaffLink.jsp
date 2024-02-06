<%-- 
    Document   : stallStaffLink
    Created on : Nov 21, 2023, 2:39:59 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> <style>
        /* Remove default padding and margin from all elements to ensure nav takes full width */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif; /* Example font */
        }

        nav {
            background-color: #f0ad4e; /* Your nav background color */
            width: 100%;
            overflow: hidden;
        }

        nav ul {
            list-style-type: none;
            width: 100%;
            display: flex;
            justify-content: space-around; /* This will space your nav items evenly */
            align-items: center; /* This will vertically center your nav items in the bar */
            padding: 10px 0; /* This adds some padding inside your nav bar */
        }

        nav ul li {
            /* No specific styles needed here unless you want individual styling */
        }

        nav ul li a {
            display: block;
            padding: 10px 15px; /* Add padding to increase clickable area */
            color: white; /* Color of your nav item links */
            text-decoration: none;
            transition: background-color 0.3s; /* Smooth transition for hover effect */
        }

        nav ul li a:hover {
            background-color: #d9534f; /* Color for hover effect */
        }
    </style>
    <body>

        <nav>
            <ul>
                <li><a href="LoadStallStaffManageServlet?action=backHome">Home</a></li>
                <li><a href="LoadStallStaffManageServlet?action=profile">Profile</a></li>
                <li><a href="LoadStallStaffManageServlet?action=salesRecord">Sales Record & Review</a></li>
                <li><a href="LoadStallStaffManageServlet?action=stallReport">Stall Report</a></li>
                <li><a href="LoadStallStaffManageServlet?action=editMenu" id="EditMenuLink">Manage Menu</a></li>
                <li><a href="LogOutServlet">Logout</a></li>


            </ul>
        </nav>

    </body>


</html>
