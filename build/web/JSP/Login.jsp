<%@page import="Account_module.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../asset/css/Login.css">
    <link rel="stylesheet" href="../asset/themify-icons/themify-icons.css">
    <title>Document</title>
</head>
<body>
    <jsp:include page="../Component/header.jsp"/>
    <jsp:include page="../Component/menu1.jsp"/>
    <div class="container">
        <div class="form-login">
            <form onsubmit="return checkLogin();" action="LoginControl" method="post">
                <h1>Login</h1> 
<!--                <span style='color:red; margin-left: 31px;'>Login Fail</span>-->
                <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null && !errorMessage.trim().isEmpty()) { %>
                    <div class="error-message" style="color:red; margin-left: 31px;"><%= errorMessage %></div>
                <% } %>
                <div class="title">Phone Number: *</div>
                <input type="text" name="PhoneNumber" id="PhoneNumber" required>
                <div class="title">Password: *</div>
                <input type="password" name="Password" id="Password" required>
                <br>
                <br>
                <input class="btnSubmit" type="submit" name="btnLogin" value="Login">
            </form>
            <div class="link-form">
                <a style="padding-left: 30px;" href="../JSP/Register.jsp">Create Account</a>
                <a style="padding-left: 182px;" href="#Form-ResetPassword">Forgot Password</a>
            </div>
        </div>
        <div class="modal overplay" id="Form-ResetPassword">
            <div class="modal-body">
                <form action="ResetPasswordControl" method="post">
                    <div class="btn-close">
                        <a href="#">X</a>
                    </div>
                    <h1>Reset Password</h1>
                    <div class="title">Phone Number: *</div>
                    <input type="text" name="PhoneNumber" id="PhoneNumber" required>
                    <div class="title">Confirm Phone Number: *</div>
                    <input type="text" name="Confirm" id="Confirm" required>
                    <br>
                    <br>
                    <input class="btnSubmit" type="submit" value="Reset Password">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
