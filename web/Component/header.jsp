<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
%>
<div class="header">
    <style>
        /* Định dạng cơ bản cho dropdown */
        .dropdown {
            padding-top: 23px;
            padding-right: 140px;
        }

        /* Định dạng cho nút mở dropdown */
        .dropbtn {
            color: white;
            border: none;
        }

        /* Định dạng cho nội dung dropdown */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        /* Định dạng cho các tùy chọn dropdown */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Định dạng khi di chuột qua tùy chọn */
        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        /* Hiển thị dropdown khi hover chuột */
        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Định dạng cho dropdown khi hover chuột trên nút mở */
        .dropdown:hover .dropbtn {
            background-color: #2980b9;
        }

    </style>
        <a href="#"><img src="../images/brandlogocoloredi244-saie.svg" alt=""></a>
<!--        <a style="padding-right: 150px; padding-top: 10px;" href="../JSP/Login.jsp"><i style="padding-left: 12px; size: 10%;" class="ti-user"><br></i>Login</a>-->
        <% 
            if (fullname != null) { 
        %>
<!--        <a style="padding-right: 150px; padding-top: 23px;" href="../JSP/Profile.jsp"><i class="ti-linkedin"></i> <%= fullname %></a>-->
            <div class="dropdown">
                <a href="#" class="dropbtn"><i class="ti-linkedin"></i> <%= fullname %></a>
                <div class="dropdown-content">
                    <a href="../JSP/Profile.jsp">Thông tin cá nhân</a>
                    <a href="#">Đơn hàng của tôi</a>                   
                    <a href="LogoutControl">Đăng xuất</a>
                </div>
            </div>
        <%
            } else {
        %>
            <a style="padding-right: 150px; padding-top: 10px;" href="../JSP/Login.jsp"><i style="padding-left: 12px; size: 10%;" class="ti-user"><br></i>Login</a>
        <%
            }
        %>
    </div>
