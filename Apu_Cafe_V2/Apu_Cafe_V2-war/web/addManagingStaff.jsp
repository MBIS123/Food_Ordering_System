<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">


    </head>
    <body>
        <h1>Adding New Managing Staff</h1>

        <div class="content" style="margin-left: 20px;" >
            <form id ="managingRegistrationForm" action="AddManagingStaffServlet" method="post"> 

                <input type="hidden" id="userType" name="userType" value="managingStaff">


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
                    <option value="other">Other</option>
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
                <a href="#" onclick="history.back();" class="back-button">Back</a>

                <input type="submit" class="submit-button" value="Add Staff">
            </form>

        </div>

        <script>
            // JavaScript code to toggle the display of store-related fields

        </script>
    </body>
</html>
