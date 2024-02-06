<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <%@ page import="model.Stall" %>
        <%@ page import="java.util.List" %>


    </head>
    <body>
        <h1>Stall Staff Approval</h1>

        <div class="content" style="margin-left: 20px;" >

                <div id="storeFields" >
                    <br><br>

                    <label for="race">Add or Create Stall for this Stall Staff:</label><br><br>
                    <select id="joinOrCreateStall" name="joinOrCreateStall" required>
                        <option value=""disabled selected>Select Your Action</option>
                        <option value="joinStall">Join</option>
                        <option value="createStall">Create Stall</option>
                    </select>

                    <div id ="joinStallField" style=" display: none">

                        <table>
                            <tr>

                                <th>Stall Name</th>
                                <th>Actions</th>
                            </tr>
                            <%
                                HttpSession s = request.getSession(false);
                                List<Stall> stallInfo = (List<Stall>) request.getAttribute("stallInfo");
                                String staffPendingId = (String) request.getAttribute("staffPendingId");
                                if (stallInfo != null) {
                                    for (Stall stall : stallInfo) {
                            %>
                            <tr data-name="<%= stall.getStoreName()%>">
                                <td><%= stall.getStoreName()%></td>
                                <td>
                                    <form action="ApproveStallStaffServlet" method="post">
                                        <input type="hidden" name="staffPendingId" value="<%= staffPendingId%>">
                                        <input type="hidden" name="stallID" value="<%= stall.getId()%>">
                                        <button type="submit" class="edit-btn" name="action" value="add">Add</button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>

                    <br><br>
       

                </div>

                <input type="hidden" id="storeConfirmation" name="storeConfirmation" value="">
                <input type="hidden" id="userType" name="userType" value="stallStaff">
                <a href="#" onclick="history.back();" class="back-button">Back</a>


            </form>
        </div>

        <script>
                    const storeFields = document.getElementById("storeFields");
            const actionSelect = document.getElementById("joinOrCreateStall");
            const joinStallField = document.getElementById("joinStallField");



            actionSelect.addEventListener("change", function () {
                if (actionSelect.value === "joinStall") {
                    joinStallField.style.display = "block";
                } else {
                    joinStallField.style.display = "none"; // Hide store-related fields
                }

                if (actionSelect.value === "createStall") {
                    window.location.href = "createStore.jsp";
                }
            });

        </script>
    </body>
</html>
