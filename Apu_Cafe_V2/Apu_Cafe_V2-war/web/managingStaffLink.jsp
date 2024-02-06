<%-- 
    Document   : managingStaffLink
    Created on : Nov 28, 2023, 5:18:06 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
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

        <nav>
            <nav>
                <ul>
                    <li><a href="LoadUserInfoServlet?action=backHome">Home</a></li>
                    <li><a href="LoadUserInfoServlet?action=manageManagingStaff">Manage managing staff</a></li>
                    <li><a href="LoadUserInfoServlet?action=manageStallStaff">Manage Stall Staff</a></li>
                    <li><a href="LoadUserInfoServlet?action=manageCustomerInfo">Manage Customer </a></li>
                    <li><a href="LogOutServlet">Logout</a></li>
                </ul>
            </nav>

        </nav>
    </body>
</html>
