<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <%@ page import="model.Customer" %>

        <title>Edit Profile</title>
    </head>
    <body>
        <h1>Edit Customer Info</h1>
        <jsp:include page="managingStaffLink.jsp"></jsp:include>
        <%
            Customer cusInfo = (Customer) request.getAttribute("customerInfo");
            if (cusInfo != null) {
        %>
        <form action="UpdateProfileServlet" method="post" class="edit-profile-form">
            <table class="edit-profile-table">
                <tr>
                    <td><label for="fullName">Full Name:</label></td>
                    <td>
                        <input value="<%= cusInfo.getFullName()%>" 
                               type="text" id="fullName" name="fullName" 
                               placeholder="Enter full name" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="age">Age:</label></td>
                    <td>
                        <input value="<%= cusInfo.getAge()%>" 
                               type="number" id="age" name="age" 
                               placeholder="Enter age" min="18" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td>
                        <input value="<%= cusInfo.getPassword()%>" id="password" name="password" 
                               placeholder="Enter new password" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td>
                        <input value="<%= cusInfo.getCity()%>" 
                               type="text" id="city" name="city" 
                               placeholder="Enter city" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="addressLine1">Address Line 1:</label></td>
                    <td>
                        <input value="<%= cusInfo.getAddressLine1()%>" 
                               type="text" id="addressLine1" name="addressLine1" 
                               placeholder="Enter address line 1" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="addressLine2">Address Line 2:</label></td>
                    <td>
                        <input value="<%= cusInfo.getAddressLine2()%>" 
                               type="text" id="addressLine2" name="addressLine2" 
                               placeholder="Enter address line 2">
                    </td>
                </tr>
                <tr>
                    <td><label for="phoneNumber">Phone Number:</label></td>
                    <td>
                        <input value="<%= cusInfo.getPhoneNumber()%>" 
                               type="text" id="phoneNumber" name="phoneNumber" 
                               placeholder="Enter phone number" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td>
                        <input value="<%= cusInfo.getEmail()%>" 
                               type="email" id="email" name="email" 
                               placeholder="Enter email" required>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="userType" value="customer">

            <input type="hidden" name="cusId" value="<%= cusInfo.getId()%>">
            <input type="submit" class="submit-button" value="Save Changes">
        </form>
        <% } else { %>
        <p>Profile not found.</p>
        <% }%>
    </body>
</html>
