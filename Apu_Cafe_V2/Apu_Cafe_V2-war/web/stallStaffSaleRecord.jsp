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
    <h1>${staffInfo.getStall().getStoreName()} Sales & Review  </h1>  
    <jsp:include page ="stallStaffLink.jsp"></jsp:include>
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
</head>
<body>
    <div class="container">



        <div class="top-bar">
            <input type="text" id="searchInput" onkeyup="searchOrder()" placeholder="Search Order by Id ">
        </div>
        <h2>Sales Record</h2>
        <table id="salesTable">
            <tr>
                <th>Order Id</th>
                <th>DateTime</th>
                <th>Customer Name</th>
                <th>Total</th>
                <th>Feed Back</th>
                <th>Rating</th>
                <th>Receipt</th>


            </tr>
        <%
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            HttpSession s = request.getSession(false);
            List<Order> orderInfo = (List<Order>) request.getAttribute("orders");
            if (orderInfo != null) {
                for (Order order : orderInfo) {
                    if ("Paid".equals(order.getStatus())) {
        %>
        <tr data-name="<%= order.getId()%>">
            <td><%= order.getId()%> </td>
            <td><%= order.getOrderTimestamp().format(formatter)%> </td>
            <td><%= order.getMenuItem().getPrice()%> </td>

            <td><%= order.getCustomer().getFullName()%> </td>
            <td> <%= order.getFeedback()%>
            <td><%= order.getRating()%>/5</td>
            <td> <form action="ManageStallStaffServlet" method="post">
                    <input type="hidden" name="orderId" value="<%= order.getId()%>">
                    <input type="hidden" name="action" value="generateReceipt">
                    <button class="submit-btn">Generate</button>
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

<script>
    function searchOrder() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById('searchInput');
        filter = input.value.toLowerCase();
        table = document.getElementById('salesTable');
        tr = table.getElementsByTagName('tr');

        for (i = 1; i < tr.length; i++) {
            td = tr[i].getElementsByTagName('td')[0]; // Assuming you want to search by Order ID, which is the first column
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = '';
                } else {
                    tr[i].style.display = 'none';
                }
            }
        }
    }
    function confirmCollect() {
        return confirm("Are you sure the payment was made?");
    }

</script>
</body>
</html>