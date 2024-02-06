<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <%@ page import="java.time.format.DateTimeFormatter" %>


        <title>Order Receipt</title>
        <%@ page import="model.Order" %>

        <style>
            .receipt-container {
                width: 400px;
                margin: 0 auto;
                border: 1px solid #ddd;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .receipt-header {
                text-align: center;
                margin-bottom: 20px;
            }
            .receipt-body {
                margin-bottom: 20px;
            }
            .receipt-footer {
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="receipt-container">
            <div class="receipt-header">
                <h2>Order Receipt</h2>
            </div>

            <div class="receipt-body">
                <%
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    Order order = (Order) request.getAttribute("orderDetails");
                    if (order != null) {
                %>
                <p><strong>Order ID:</strong> <%= order.getId()%></p>
                <p><strong>Date:</strong> <%= order.getOrderTimestamp().format(formatter)%></p>
                <p><strong>Customer:</strong> <%= order.getCustomer().getFullName()%></p>
                <p><strong>Item:</strong> <%= order.getMenuItem().getName()%></p>
                <p><strong>Price:</strong> RM<%= order.getMenuItem().getPrice()%></p>
                <p><strong>Rating:</strong> <%= order.getRating()%>/5</p>
                <p><strong>Feedback:</strong> <%= order.getFeedback()%></p>
                <% } else { %>
                <p>Order details not found.</p>
                <% }%>
            </div>



            <div class="receipt-footer">
                <p>Apu Cafeteria</p>
                <p>Thank you for your purchase!</p>

            </div>
        </div>
        <br>                    <br>
        <br>

        <a href="#" onclick="history.back();" class="back-button">Back</a>
    </body>
</html>
