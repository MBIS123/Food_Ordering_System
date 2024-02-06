<%-- 
    Document   : userRegisterLanding
    Created on : Nov 28, 2023, 2:10:54 PM
    Author     : User
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">


    </head>
    <body>
        <h1>User Type Selection Page </h1>

        <div class="content" style="margin-left: 20px;" >
            <form id ="registrationForm" action="RegisterServlet" method="post"> 
                <label for="userType">Register as:</label>
                <br>
                <select name="userType" id="userType" required>
                    <option value="" disabled selected>Select User Type</option>
                    <option value="customer">Customer</option>
                    <option value="stallStaff">Stall Staff</option> 
                    <option value="managingStaff">Managing Staff</option> 
                </select>

                <br><br>


                <a href="landing.jsp" class="back-button">Back</a>



                <a href="#" id="proceedLink" class="submit-button">Proceed</a>



            </form>
        </div>

        <script>
            // JavaScript code to toggle the "Proceed" link's href based on user selection
            const userTypeSelect = document.getElementById("userType");
            const proceedLink = document.getElementById("proceedLink");

            userTypeSelect.addEventListener("change", function () {
                if (userTypeSelect.value === "customer") {
                    proceedLink.href = "customerRegistration.jsp"; // Set the href for customer
                } else if (userTypeSelect.value === "stallStaff") {
                    proceedLink.href = "stallStaffRegistration.jsp"; // Set the href for stall staff
                } else if (userTypeSelect.value === "managingStaff") {
                    proceedLink.href = "managingStaffRegistration.jsp"; // Set the href for stall staff
                } else {
                    proceedLink.href = "#"; // Set a default href (e.g., a placeholder link)
                }
            });
        </script>
    </body>
</html>
