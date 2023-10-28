<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../asset/css/Message.css">
    <link rel="stylesheet" href="../asset/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="url"/>
    <title>Tin Nhắn Mật Khẩu Mới</title>
</head>
<body>
    <jsp:include page="../Component/header.jsp"/>
    <jsp:include page="../Component/menu1.jsp"/>
    <div class="container">
        <div class="message-box">
            <% 
                String phoneNumber = (String) request.getAttribute("phoneNumber");;
                String newPassWord = (String) request.getAttribute("newPassword");;
            %>
            <p>Đây là mật khẩu mới của số điện thoại <%= phoneNumber %>: <%= newPassWord %></p>
            <p>Sau khi đăng nhập, vui lòng thay đổi mật khẩu để bảo mật tài khoản.</p>
        </div>
    </div>
</body>
</html>
