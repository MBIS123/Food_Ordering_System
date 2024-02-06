<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="model.ManagingStaff" %>
        <%@ page import="java.util.List" %>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Managing Staff Interface</title>
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
            <h1>Manage Managing Staff</h1>
            <jsp:include page="managingStaffLink.jsp"></jsp:include>

                <div class="top-bar">
                    <input type="text" id="searchInput" onkeyup="searchMenu()" placeholder="Search Managing Staff ID">
                    <button type="button" class="add-btn" onclick="location.href = 'addManagingStaff.jsp'">Add Staff</button>
                </div>

            
                <h2>Managing Staff List</h2>
                    <table>
                        <tr>
                            <th>Managing Staff Id</th>
                            <th>Full Name</th>
                            <th>Actions</th>
                        </tr>
                    <%
                        HttpSession s = request.getSession(false);
                        List<ManagingStaff> mngStaffInfo = (List<ManagingStaff>) request.getAttribute("mngStaffInfo");
                        ManagingStaff managingStaff = (ManagingStaff) s.getAttribute("magStfInfo");

                        if (mngStaffInfo != null) {
                            for (ManagingStaff staff : mngStaffInfo) {
                                if (!staff.getUsername().equals(managingStaff.getUsername())) {
                    %>
                    <tr data-name="<%= staff.getUsername()%>">
                        <td><%= staff.getUsername()%></td>
                        <td><%= staff.getFullName()%></td>
                        <td>
                            <form action="ManageManagingStaffServlet" method="post">
                                <input type="hidden" name="userID" value="<%= staff.getUserAccountID()%>">
                                <input type="hidden" name="userTypeID" value="<%= staff.getId()%>">
                                <input type="hidden" name="user" value="managingStaff">
                                <button type="submit" class="edit-btn" name="action" value="edit">Edit</button>
                                <button type="submit" class="delete-btn" name="action" value="delete">Delete</button>
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
            function searchMenu() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById('searchInput');
                filter = input.value.toLowerCase();
                table = document.querySelector("table");
                tr = table.getElementsByTagName("tr");

                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0]; // Assuming you want to search by username, which is the first column
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toLowerCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </body>
</html>
