<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
                <%@ page import="model.MenuItem" %>

        <title>Edit Menu Item</title>
    </head>
    <body>
        <h1>Edit Menu Item</h1>
        <jsp:include page ="stallStaffLink.jsp"></jsp:include>
        <% 
            MenuItem menuItem = (MenuItem) request.getAttribute("menuItem");
            if (menuItem != null) {
        %>
        <form action="EditMenuServlet" method="post" class="edit-menu-form">
            <table class="edit-menu-table">
                <tr>
                    <td><label for="itemName">Item Name:</label></td>
                    <td>
                        <input value="<%= menuItem.getName() %>" 
                           type="text" id="itemName" name="itemName" 
                           placeholder="Enter item name" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="itemType">Item Type:</label></td>
                    <td>
                        <select id="itemType" name="itemType">
                            <option value="mainDishes" <%= "mainDishes".equals(menuItem.getCategory()) ? "selected" : ""%>>Main Dishes</option>
                            <option value="sideDishes" <%= "sideDishes".equals(menuItem.getCategory()) ? "selected" : ""%>>Side Dishes</option>
                            <option value="beverage" <%= "beverage".equals(menuItem.getCategory()) ? "selected" : ""%>>Beverages</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="itemPrice">Price:</label></td>
                    <td>
                        <input value="<%= menuItem.getPrice() %>" 
                           type="number" id="itemPrice" name="itemPrice" 
                           placeholder="Enter price" min="0" step="0.01" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="itemImg">Item Image Url:</label></td>
                    <td>
                        <input value="<%= menuItem.getImageUrl() %>" 
                           type="text" id="itemImg" name="itemImg" 
                           placeholder="Enter item url" required>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="itemId" value="<%= menuItem.getId() %>">
            <input type="submit" value="Save Changes">
        </form>
        <% } else { %>
            <p>Menu item not found.</p>
        <% } %>
    </body>
</html>
