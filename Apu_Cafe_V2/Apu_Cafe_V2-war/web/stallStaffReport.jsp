<%-- 
    Document   : stallStaffHome
    Created on : Nov 21, 2023, 2:36:57 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="model.MenuItem" %>
        <%@ page import="model.Order" %>
        <%@ page import="java.util.List" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page import="java.time.format.DateTimeFormatter" %>

        <title>Stall Staff Sales Record</title>


    </head>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
        }
        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {background-color: #f5f5f5;}
        .edit-btn, .delete-btn {
            padding: 5px 10px;
            border: none;
            cursor: pointer;
        }
        .edit-btn {
            background-color: #007bff;
            color: white;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .top-bar {
            width: 80%; /* Or the width that matches your table */
            display: flex;
            justify-content: space-between; /* Positions items on the ends */
            padding: 10px;
            margin-bottom: 20px;
        }

        #searchInput {
            padding: 5px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 300px; /* Or the size you prefer */
        }

        .add-btn {
            padding: 5px 10px;
            background-color: #28a745; /* Bootstrap 'success' color */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 0.9em;
        }
    </style>
    <body>


        <div class="container">
            <h1>${staffInfo.getStall().getStoreName()} : ${staffInfo.getFullName()}</h1>

            <jsp:include page="stallStaffLink.jsp"></jsp:include>


                <br><br><br>
                <h2>Menu Item Sales</h2>
                <table border="1">
                    <tr>
                        <th>Menu Item</th>
                        <th>Count</th>
                    </tr>
                <%
                    List<Object[]> menuItemCounts = (List<Object[]>) request.getAttribute("menuItemCounts");
                    for (Object[] itemCount : menuItemCounts) {
                        String itemName = (String) itemCount[0];
                        Long count = (Long) itemCount[1];
                %>
                <tr>
                    <td><%= itemName%></td>
                    <td><%= count%></td>
                </tr>
                <% }%>
            </table>




            <h2>Completed Orders Count</h2>
            <table border="1">
                <tr>
                    <th>Count of Paid Orders</th>
                </tr>
                <tr>
                    <td><%= request.getAttribute("paidOrdersCount")%></td>
                </tr>
            </table>

            <h2>Customer Age Group Counts </h2>
            <table border="1">
                <tr>
                    <th>Age Group</th>
                    <th>Count</th>
                </tr>
                <%
                    List<Object[]> ageGroupCounts = (List<Object[]>) request.getAttribute("ageGroupCounts");
                    for (Object[] ageGroupCount : ageGroupCounts) {
                        String ageGroup = (String) ageGroupCount[0];
                        Long count = (Long) ageGroupCount[1];
                %>
                <tr>
                    <td><%= ageGroup%></td>
                    <td><%= count%></td>
                </tr>
                <% } %>
            </table>


            <h2>Customer Gender Counts</h2>
            <table border="1">
                <tr>
                    <th>Gender</th>
                    <th>Count</th>
                </tr>
                <%
                    List<Object[]> genderCounts = (List<Object[]>) request.getAttribute("genderCounts");
                    for (Object[] genderCount : genderCounts) {
                        String gender = (String) genderCount[0];
                        Long count = (Long) genderCount[1];
                %>
                <tr>
                    <td><%= gender%></td>
                    <td><%= count%></td>
                </tr>
                <% } %>
            </table>


            <h2>Customer Race Counts</h2>
            <table border="1">
                <tr>
                    <th>Race</th>
                    <th>Count</th>
                </tr>
                <%
                    List<Object[]> raceCounts = (List<Object[]>) request.getAttribute("raceCounts");
                    for (Object[] raceCount : raceCounts) {
                        String race = (String) raceCount[0];
                        Long count = (Long) raceCount[1];
                %>
                <tr>
                    <td><%= race%></td>
                    <td><%= count%></td>
                </tr>
                <% } %>
            </table>


            <h2>Ordered Menu Item Average Ratings </h2>
            <table border="1">
                <tr>
                    <th>Menu Item</th>
                    <th>Average Rating</th>
                </tr>
                <%
                    List<Object[]> menuItemRatings = (List<Object[]>) request.getAttribute("menuItemRatings");
                    for (Object[] ratingInfo : menuItemRatings) {
                        String menuItemName = (String) ratingInfo[0];
                        Double avgRating = (Double) ratingInfo[1];
                %>
                <tr>
                    <td><%= menuItemName%></td>
                    <td><%= avgRating%></td>
                </tr>
                <% }%>
            </table>


        </div>


    </body>
</html>