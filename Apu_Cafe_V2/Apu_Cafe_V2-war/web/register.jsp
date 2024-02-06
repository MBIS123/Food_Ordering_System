<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">


    </head>
    <body>
        <h1>Registration Form</h1>

        <div class="content" style="margin-left: 20px;" >
            <form id ="registrationForm" action="RegisterServlet" method="post"> 
                <label for="userType">Register as:</label>
                <br>
                <select name="userType" id="userType" required>
                    <option value="" disabled selected>Select User Type</option>
                    <option value="customer">Customer</option>
                    <option value="stallStaff">Stall Staff</option> <!-- Move to the bottom -->
                </select>

                <br><br>

                <input type="hidden" id="storeConfirmation" name="storeConfirmation" value="">


                <label for="username">Username:</label><br><br>
                <input type="text" id="username" name="username" required>
                <br><br>

                <label for="displayname">Display name:</label><br><br>  
                <input type="text" id="displayname" name="displayname" required>
                <br><br>

                <label for="password">Password:</label><br><br>
                <input type="password" id="password" name="password" required>
                <br><br>

                <label for="confirmPassword">Confirm Password:</label><br><br>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
                <br><br>

                <label for="email">Email:</label><br><br>
                <input type="email" id="email" name="email" required>
                <br><br>

                <label for="phoneNumber">Phone Number:</label><br><br>
                <input type="text" id="phoneNumber" name="phoneNumber" required>
                <br><br>

                <label for="age">Age:</label><br><br>
                <select id="age" name="age" required>
                    <option value=""disabled selected>Select Your Age</option>

                    <!-- Populate ages, for example, 18 to 100 -->
                    <% for (int i = 18; i <= 100; i++) {%>
                    <option value="<%= i%>"><%= i%></option>
                    <% }%>
                </select>
                <br><br>

                <label for="gender">Gender:</label><br><br>
                <select id="gender" name="gender" required>
                    <option value=""disabled selected>Select Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
                <br><br>

                <label for="race">Race:</label><br><br>
                <select id="race" name="race" required>
                    <option value=""disabled selected>Select Your Race</option>
                    <option value="Malay">Malay</option>
                    <option value="Indian">Indian</option>
                    <option value="Chinese">Chinese</option>
                    <option value="Other">Other</option>
                </select>
                <br><br>

                <label for="addressLine1">Address Line 1:</label><br><br>
                <input type="text" id="addressLine1" name="addressLine1" required>
                <br><br>

                <label for="addressLine2">Address Line 2:</label><br><br>
                <input type="text" id="addressLine2" name="addressLine2">
                <br><br>

                <label for="city">City:</label><br><br>
                <select id="city" name="city" required>
                    <option value=""disabled selected>Select Your Living Area</option>

                    <option value="Ampang">Ampang</option>
                    <option value="Balakong">Balakong</option>
                    <option value="Bandar Baru Bangi">Bandar Baru Bangi</option>
                    <option value="Bandar Tun Hussein Onn">Bandar Tun Hussein Onn</option>
                    <option value="Bangi">Bangi</option>
                    <option value="Beranang">Beranang</option>
                    <option value="Cheras">Cheras</option>
                    <option value="Kajang">Kajang</option>
                    <option value="Semenyih">Semenyih</option>
                    <option value="Taman Sri Nanding">Taman Sri Nanding</option>
                    <option value="Jeram">Jeram</option>
                    <option value="Pasangan">Pasangan</option>
                    <option value="Puncak Alam">Puncak Alam</option>
                    <option value="Sungai Buloh">Sungai Buloh</option>
                    <option value="Tanjung Karang">Tanjung Karang</option>
                    <option value="Petaling district">Petaling district</option>
                    <option value="Bandar Sri Damansara">Bandar Sri Damansara</option>
                    <option value="Bukit Raja">Bukit Raja</option>
                    <option value="Country Heights">Country Heights</option>
                    <option value="Damansara">Damansara</option>
                    <option value="Kota Raja">Kota Raja</option>
                    <option value="Petaling Jaya">Petaling Jaya</option>
                    <option value="Puchong">Puchong</option>
                    <option value="Puchong Jaya">Puchong Jaya</option>
                </select>
                <br><br>
                
                <div id="storeFields" style="display:none;">
                    <br><br>

                    <label for="race">Would you like to join or create a stall:</label><br><br>
                    <select id="joinOrCreateStall" name="joinOrCreateStall" required>
                        <option value=""disabled selected>Select Your Action</option>
                        <option value="joinStall">Join</option>
                        <option value="createStall">Create Stall</option>
                    </select>

                    <br><br>
                    <div id ="joinStallField" style="display:none;">
                        <label for="storeName">Enter the Store Name You would like to join</label>
                        <br><br>
                        <input type="text" id="storeName" name="storeName" required>
                        <br><br>
                    </div>

                </div>

                <a href="landing.jsp" class="back-button">Back</a>

                <input id="registerButton"type="submit" class="submit-button" value="Register">
            </form>
        </div>

        <script>
            // JavaScript code to toggle the display of store-related fields
            const userTypeSelect = document.getElementById("userType");
            const storeFields = document.getElementById("storeFields");
            const actionSelect = document.getElementById("joinOrCreateStall");
            const joinStallField = document.getElementById("joinStallField");

            userTypeSelect.addEventListener("change", function () {
                // Check if the selected user type is "stallStaff"
                if (userTypeSelect.value === "stallStaff") {
                    storeFields.style.display = "block"; // Show store-related fields

                } else {
                    storeFields.style.display = "none"; // Hide store-related fields
                }
            });

            actionSelect.addEventListener("change", function () {
                if (actionSelect.value === "joinStall") {
                    joinStallField.style.display = "block";
                } else {
                    joinStallField.style.display = "none"; // Hide store-related fields
                }
            });

        </script>
    </body>
</html>
