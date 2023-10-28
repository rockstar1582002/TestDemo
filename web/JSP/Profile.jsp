<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../asset/css/Profile.css">
    <link rel="stylesheet" href="../asset/themify-icons/themify-icons.css">
    <title>Thông tin cá nhân</title>
</head>        
<%!
    // Định nghĩa hàm lấy giá trị của cookie
    public String getCookieValue(javax.servlet.http.Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
%>
<%
    String fullname = getCookieValue(request.getCookies(), "username");
    String phone = getCookieValue(request.getCookies(), "PhoneNumber");
    String password = getCookieValue(request.getCookies(), "pass");
%>
<body>
    <jsp:include page="../Component/header.jsp"/>
    <div class="container">
        <div class="container-left">
            <div class="menu">
                <img src="../images/Profile.png" alt="">
                <h1>Nguyễn Văn A</h1>
                <div class="button">
                    <button class="btn1"><a href="../JSP/showAccount.jsp">Trang chủ</a></button>
                    <button class="btn2"><a href="LogoutServlet">Đăng xuất</a></button>
                </div>
                <br>
            </div>
        </div>
        <div class="container-right">
            <div class="title">
                <span class="title1">Hồ sơ của tôi</span>
                <br>
                <span>Quản lý thông tin hồ sơ để bảo mật</span>
            </div>
            <div class="content">
                <div class="left">
                    <div class="menu-left">
                        <form action="UpdateInfo" method="post">
                            <div class="title2">Phone number</div>
                            <input type="text" name="phoneNumber" value="<%= phone %>" disabled>
                            <div class="title2">Password</div>
                            <input type="text" name="password" value="<%= password %>">
                            <div class="title2">Username</div>
                            <input type="text" name="username" value="<%= fullname %>">
                            <br>
                            <br>
                            <input class="btn2" type="submit" name="btbsubmit" value="Chỉnh sửa"> 
                        </form>                                               
                    </div>
                </div>
                <div class="right">
                    <img src="../images/Profile.png" alt="">
                    <input type="file" name="" id="">
                </div>
            </div>
        </div>
    </div>
</body>
</html>