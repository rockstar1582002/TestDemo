<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../asset/css/Register.css">
    <link rel="stylesheet" href="../asset/themify-icons/themify-icons.css">
    <title>Document</title>
</head>
<body>
    <jsp:include page="../Component/header.jsp"/>
    <jsp:include page="../Component/menu1.jsp"/>
    <div class="container">
        <div class="form-register">
            <form method="post" action="RegisterControl">
                <h1>Register</h1>
                <% String errorMessage = (String) request.getAttribute("error"); %>
                <% if (errorMessage != null && !errorMessage.trim().isEmpty()) { %>
                    <div class="error-message" style="color:red; margin-left: 31px;"><%= errorMessage %></div>
                <% } %>
                <div class="title">Username: *</div>
                <input type="text" name="Username" id="Username" required>
                <div class="title">Phone Number: *</div>
                <input type="text" name="PhoneNumber" id="PhoneNumber" required>
                <div class="title">Password: *</div>
                <input type="password" name="Password" id="Password" required>
                <div class="title">Confirm Password: *</div>
                <input type="password" name="ConfirmPassword" id="ConfirmPassword" required>
                <br>
                <input class="btnSubmit" type="submit" name="btnsubmit" value="Register">
            </form>
        </div>
    </div>
    <div class="footer">

    </div>
</body>
</html>
