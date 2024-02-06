<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@ page import="model.ManagingStaff" %>
        <%@ page import="model.UserAccount" %>
        <%@ page import="java.util.List" %>
        <%@ page import="javax.persistence.Tuple" %>
        <%@ page import="java.util.Map" %>


        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Managing Staff Page</title>
    </head>
    <body>
        <h1>Managing Staff: ${magStfInfo.getFullName()}</h1>
        <jsp:include page="managingStaffLink.jsp"></jsp:include>

            <!-- Displaying the Top 5 Stores Table -->
            <h2>Top 5 Stores with Most Paid Orders</h2>
            <table border="1">
                <tr>
                    <th>Store</th>
                    <th>Number of Paid Orders</th>
                </tr>
            <%
                List<Object[]> topStores = (List<Object[]>) request.getAttribute("topStores");
                if (topStores != null) {
                    for (Object[] storeInfo : topStores) {
            %>
            <tr>
                <td><%= storeInfo[0]%></td>
                <td><%= storeInfo[1]%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>


        <h2>Stall Types and Counts</h2>
        <table>
            <tr>
                <th>Store Type</th>
                <th>Stall Count</th>
            </tr>
            <%
                List<Object[]> stallCounts = (List<Object[]>) request.getAttribute("stallCounts");
                for (Object[] stallCount : stallCounts) {
                    String storeType = (String) stallCount[0];
                    Long count = (Long) stallCount[1];
            %>
            <tr>
                <td><%= storeType%></td>
                <td><%= count%></td>
            </tr>
            <% } %>
        </table>

        <h2>Stall staff Counts</h2>
        <table>
            <tr>
                <th>Store Type</th>
                <th>Staff Count</th>
            </tr>
            <%
                List<Object[]> stallStaffCountStore = (List<Object[]>) request.getAttribute("stallStaffCountStore");
                for (Object[] stallCount : stallStaffCountStore) {

            %>
            <tr>
                <td><%= stallCount[0]%></td>
                <td><%= stallCount[1]%></td>
            </tr>
            <% }%>
        </table>
        
         <h2>User Roles Count</h2>
    <table border="1">
        <tr>
            <th>Role</th>
            <th>User Count</th>
        </tr>
        <%
            Map<String, Long> userCountsByRole = (Map<String, Long>) request.getAttribute("userCountsByRole");
            for (Map.Entry<String, Long> entry : userCountsByRole.entrySet()) {
                String role = entry.getKey();
                Long count = entry.getValue();
        %>
        <tr>
            <td><%= role %></td>
            <td><%= count %></td>
        </tr>
        <% } %>
    </table>
    
        <h2>Stall staff Gender Counts</h2>
        <table>
            <tr>
                <th>Gender</th>
                <th>Staff Count</th>
            </tr>
            <%
                List<Object[]> cntStaffGender = (List<Object[]>) request.getAttribute("cntStaffGender");
                for (Object[] genderCount : cntStaffGender) {

            %>
            <tr>
                <td><%= genderCount[0]%></td>
                <td><%= genderCount[1]%></td>
            </tr>
            <% }%>
        </table>
    
    
        
        
        
    </body>
</html>
