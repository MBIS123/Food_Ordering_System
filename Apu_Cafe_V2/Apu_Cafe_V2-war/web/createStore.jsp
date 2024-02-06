<%-- 
    Document   : createStore
    Created on : Nov 21, 2023, 2:51:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">

        <title>Create Stall Page</title>


        <style>

            .createStoreBlock{
                margin-top: 40px; /* Adjust the value as needed */
            }

        </style>
    </head>
    <body>
        <h1 style="padding-left:20px;" >Create a New Stall</h1>
        <form action="CreateStoreServlet" method="post"> 
            <div class="createStoreBlock">
                <label for="storeName" style="margin-top:100px;">Store Name:</label><br>
                <input type="text" id="stallName" name="stallName" required><br><br>

                <label for="storeCategory">Store Category:</label><br>
                <select id="storeCategory" name="storeCategory">
                    <option value="beverage">Beverage</option>
                    <option value="chineseCuisine">Chinese Cuisine</option>
                    <option value="malayCuisine">Malay Cuisine</option>
                    <option value="indianCuisine">Indian Cuisine</option>
                    <option value="mamakStalls">Mamak Stalls</option>
                    <option value="westernFood">Western Food</option>
                    <option value="halalFood">Halal Food</option>
                    <option value="vegetarianFood">Vegetarian Food</option>
                </select><br><br>

                <input type="submit" class="submit-button" value="Create Stall">
                <a href="#" onclick="history.back();" class="back-button">Back</a>

            </div>
        </form>
    </body>
</html>
