<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="model.MenuItem" %>
        <%@ page import="model.Order" %>
        <%@ page import="java.util.List" %>
        <%@ page import="java.time.format.DateTimeFormatter" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
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
            .top-bar {
                width: 100%;
                padding: 10px;
                box-sizing: border-box;
            }
            #searchInputStaff {
                padding: 5px;
                font-size: 1em;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 50%; /* Adjust as necessary */
                margin: 0 auto; /* Center the search bar */
                display: block; /* Block display for the input */
            }
            .tables-container {
                display: flex;
                width: 100%;
                justify-content: space-evenly;
                margin-top: 20px;
            }
            .table-wrapper {
                width: 48%; /* Adjust to use available space */
                margin: 0;
            }
            table {
                width: 100%;
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
            .edit-btn, .delete-btn, .grey-btn {
                padding: 5px 10px;
                border: none;
                cursor: pointer;
                color: white;
            }
            .edit-btn {
                background-color: #007bff;
            }
            .delete-btn {
                background-color: #dc3545;
            }
            .grey-btn {
                background-color: #6c757d;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>${cusInfo.getFullName()} Order History</h1>
            <jsp:include page="customerLink.jsp"></jsp:include>

                <div class="top-bar">
                    <input type="text" id="searchInputStaff" onkeyup="searchMenu()" placeholder="Search Order by Id ">
                </div>

                <div class="tables-container">
                    <!-- Table for Paid Orders -->
                    <div class="table-wrapper">
                        <h2>Paid Orders</h2>
                        <table id="paidOrderTable">
                            <tr>
                                <th>Order Details</th>
                                <th>Status</th>
                                <th>DateTime</th>
                                <th>Feedback</th>
                                <th>Rating</th>
                                <th>Edit Feedback</th>
                                <th>Edit Rating</th>
                            </tr>
                        <%
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            List<Order> orderInfo = (List<Order>) request.getAttribute("madeOrder");
                            if (orderInfo != null) {
                                for (Order order : orderInfo) {
                                    if ("Paid".equals(order.getStatus())) {
                                        // Generate the rows for paid orders
%>
                        <tr data-name="<%= order.getId()%>">
                            <td>
                                Order Id: <%= order.getId()%><br>
                                Item: <%= order.getMenuItem().getName()%><br>
                                Total: <%= order.getMenuItem().getPrice()%><br>
                                Stall: <%= order.getMenuItem().getStallName()%><br>
                            </td>
                            <td><%= order.getStatus()%> </td>
                            <td><%= order.getOrderTimestamp().format(formatter)%> </td>
                            <td> <%= order.getFeedback().isEmpty() ? "No feedback given" : order.getFeedback()%> </td>
                            <td> <%= order.getRating() == 0 ? "No rating given" : order.getRating()+"\\5"%> </td>

                            <td>
                                <form action="ManageCustomerServlet" method="post" onsubmit="return confirmCollect();">
                                    <input type="hidden" name="orderId" value="<%= order.getId()%>">
                                    <input type="hidden" name="manageType" value="editOrDelete">

                                    <div style="margin-bottom: 10px;">
                                        <textarea name="feedback" id="feedback" rows="2"
                                                  style="width: 100%; max-width: 400px;"
                                                  placeholder= "Enter your feedback here"
                                                  ></textarea>
                                        <button type="submit"class= "edit-btn"  name="action" value="editFeedback">Edit Feedback</button>

                                    </div>

                                </form>

                            </td>
                            <td>
                                <form action="ManageCustomerServlet" method="post" onsubmit="return confirmCollect();">
                                    <input type="hidden" name="orderId" value="<%= order.getId()%>">
                                    <input type="hidden" name="manageType" value="editOrDelete">
                                    <select name="rating" id="rating<%= order.getId()%>" style="width: 100%; max-width: 400px;">
                                        <option value="" disabled <%= order.getRating() == 0 ? "selected" : ""%> hidden>Rate</option>
                                        <option value="1" <%= order.getRating() == 1 ? "selected" : ""%>>1</option>
                                        <option value="2" <%= order.getRating() == 2 ? "selected" : ""%>>2</option>
                                        <option value="3" <%= order.getRating() == 3 ? "selected" : ""%>>3</option>
                                        <option value="4" <%= order.getRating() == 4 ? "selected" : ""%>>4</option>
                                        <option value="5" <%= order.getRating() == 5 ? "selected" : ""%>>5</option>
                                    </select>
                                    <button type="submit" class="edit-btn" name="action" value="editRating" style="padding-top: 10px ">Edit Rating</button>
                                </form>
                            </td>


                        </tr>
                        <%
                                    }
                                }
                            }
                        %>
                    </table>
                </div>
                <div class="table-wrapper">
                    <h2>Unpaid Orders</h2>
                    <table id="unpaidOrderTable">
                        <tr>
                            <th>Order Details</th>
                            <th>Status</th>
                            <th>DateTime</th>
                        </tr>
                        <%
                            if (orderInfo != null) {
                                for (Order order : orderInfo) {
                                    if ("Unpaid".equals(order.getStatus())) {
                                        // Generate the rows for unpaid orders
%>
                        <tr data-name="<%= order.getId()%>">
                            <td>
                                Order Id: <%= order.getId()%><br>
                                Item: <%= order.getMenuItem().getName()%><br>
                                Total: <%= order.getMenuItem().getPrice()%><br>
                                Stall: <%= order.getMenuItem().getStallName()%><br>
                            </td>
                            <td><%= order.getStatus()%> </td>
                            <td><%= order.getOrderTimestamp().format(formatter)%> </td>

                        </tr>
                        <%
                                    }
                                }
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>

        <script>
            function searchMenu() {
                // ... Your existing searchMenu function ...
            }
            function confirmCollect() {
                // ... Your existing confirmCollect function ...
            }
        </script>
    </body>
</html>
