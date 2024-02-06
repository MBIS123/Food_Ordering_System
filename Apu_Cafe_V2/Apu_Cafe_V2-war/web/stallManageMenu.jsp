<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page import="model.MenuItem" %>
        <%@ page import="java.util.List" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">

        <link rel="stylesheet" type="text/css" href="style.css"> 
    <h1>${staffInfo.getStall().getStoreName()} Manage Menu</h1>
    <jsp:include page ="stallStaffLink.jsp"></jsp:include>

        <style>

        </style>
    </head>
    <body>
        <br><br><br>

        <div class="top-bar">
            <input type="text" id="searchInput" onkeyup="searchMenu()" placeholder="Search Menu Item">
        </div>
        <div class="container">


            <div class="menu-items">
            <%-- Iterate over menu items --%>
            <%
                List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                if (menuItems != null) {
                    for (MenuItem item : menuItems) {
            %>


            <div class="menu-item" data-name="<%= item.getName().toLowerCase()%>">
                <img src="<%= item.getImageUrl()%>" alt="Menu Item"/>
                <div class="menu-item-details">
                    <h3><%= item.getName()%></h3>
                    <p class="price">RM<%= item.getPrice()%></p>
                    <p><%= item.getCategory()%></p>
                    <form action="LoadStallStaffManageServlet" method="POST"  >
                        <input type="hidden" name="editItem" value="editSelectedMenuItem">

                        <input type="hidden" name="itemId" value="<%= item.getId()%>">
                        <input type="hidden" name="itemName" value="<%= item.getName()%>">
                        <input type="hidden" name="itemPrice" value="<%= item.getPrice()%>">
                        <input type="hidden" name="itemCategory" value="<%= item.getCategory()%>">
                        <input type="hidden" name="itemImgUrl" value="<%= item.getImageUrl()%>">

                        <button class="edit-btn" type="submit">Edit</button>
                    </form>

                    <form action="DeleteMenuItemServlet" method="post">
                        <input type="hidden" name="itemId" value="<%= item.getId()%>">
                        <button type="submit" class="delete-btn">Delete</button>
                    </form>

                </div>
            </div>

            <%
                    }
                }
            %>
        </div>


        <div class="add-item-form">
            <!-- Add/Edit Menu Form -->
            <h2>Add/Edit Menu Item</h2>
            <form action="AddItemServlet" method="post">
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" name="itemName" placeholder="Enter item name" required>
                <br>

                <label for="itemType">Item Type:</label>
                <select id="itemType" name="itemCategory" >
                    <option value="Main Dish">Main Dishes</option>
                    <option value="Side Dish">Side Dishes</option>
                    <option value="Beverage">Beverages</option>
                </select>
                <br>

                <label for="itemPrice">Price:</label>
                <input type="number" id="itemPrice" name="itemPrice" placeholder="Enter price" min="0" required>
                <br>

                <label for="itemImg">Item Image Url:</label>
                <input type="text" id="itemImg" name="itemImg" placeholder="Enter item url" required>
                <br>

                <input type="submit" value="Add">
            </form>


        </div>




    </div>

    <script>
        function limitDecimalPlaces(e, count) {
            if (e.target.value.indexOf('.') == -1) {
                return;
            }
            if ((e.target.value.length - e.target.value.indexOf('.')) > count) {
                e.target.value = parseFloat(e.target.value).toFixed(count);
            }
        }
        
      

        function searchMenu() {
            var input, filter, menuItems, menuItem, i, txtValue;
            input = document.getElementById('searchInput');
            filter = input.value.toLowerCase();
            menuItems = document.getElementsByClassName('menu-item');

            for (i = 0; i < menuItems.length; i++) {
                menuItem = menuItems[i];
                txtValue = menuItem.getAttribute('data-name');
                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                    menuItem.style.display = "";
                    menuItem.style.visibility = "";

                } else {
                    menuItem.style.visibility = "hidden";
                }
            }
        }
    </script>
</body>
</html>
