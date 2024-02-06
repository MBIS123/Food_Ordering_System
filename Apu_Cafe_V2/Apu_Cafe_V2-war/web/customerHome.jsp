<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page import="model.MenuItem" %>
        <%@ page import="java.util.List" %>
        <title>Customer Home Page</title>
            <h1>Welcome Back ${cusInfo.getFullName()}</h1>
    <jsp:include page ="customerLink.jsp"></jsp:include>
    </head>
    <body
        <div class="top-bar">
            <br>
            <br>
            <input type="text" id="searchInput" onkeyup="searchMenu()" placeholder="Search Menu Item">
        </div>
        <div class="container">
            <div class="menu-items">
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
                    <form action="ManageCustomerServlet" method="post" onsubmit="return confirmOrder();">
                        <input type="hidden" name="action" value="makeOrder">

                        <input type="hidden" name="itemId" value="<%= item.getId()%>">
                        <input type="hidden" name="itemName" value="<%= item.getName()%>">
                        <input type="hidden" name="itemPrice" value="<%= item.getPrice()%>">
                        <input type="hidden" name="itemCategory" value="<%= item.getCategory()%>">
                        <input type="hidden" name="itemImgUrl" value="<%= item.getImageUrl()%>">
                        <button id="makeOrderBtn"class="edit-btn" type="submit">Order</button>
                    </form>

                </div>
            </div>

            <%
                    }
                }
            %>
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

        function confirmOrder() {
            return confirm("Are you sure you want to make this order?");
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