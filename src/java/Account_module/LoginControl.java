/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Account_module;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Quoc Hung
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/LoginControl"})
public class LoginControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String phone = request.getParameter("PhoneNumber");
        String password = request.getParameter("Password");

        String errorMessage = null;

        // Kiểm tra xem phone có phải là số không
        if (phone != null && phone.matches("^[0-9]+$") && phone.length() == 10) {
            AcountModel acountDAO = new AcountModel();
            Account account = acountDAO.getUserByPhoneAndPassword(phone, password);

            if (account != null) {
                int usertype = account.getType();
                switch (usertype) {
                    case 1:
                        // Kiểm tra giá trị của cookie trước khi thêm vào
                        String usernameAdmin = account.getName();
                        String phoneNumberAdmin = account.getPhoneNumber();
                        String passAdmin = account.getPassWord();
                        if (isValidCookieValue(usernameAdmin)&& isValidCookieValue(phoneNumberAdmin) && isValidCookieValue(passAdmin)) {
                            Cookie usernameCookie = new Cookie("username", usernameAdmin);
                            Cookie phoneCookie = new Cookie("PhoneNumber", phoneNumberAdmin);
                            Cookie passwordCookie = new Cookie("pass", passAdmin);

                            usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
                            passwordCookie.setMaxAge(7 * 24 * 60 * 60);
                            phoneCookie.setMaxAge(7 * 24 * 60 * 60);

                            response.addCookie(usernameCookie);
                            response.addCookie(phoneCookie);
                            response.addCookie(passwordCookie);
                            response.sendRedirect("Admin.jsp");
                            return;
                        } else {
                            // Giá trị của cookie không hợp lệ
                            errorMessage = "Giá trị cookie không hợp lệ";
                        }
                        break;                        
                    case 2:
                        // Kiểm tra giá trị của cookie trước khi thêm vào
                        String usernameEmployee = account.getName();
                        String phoneNumberEmployee = account.getPhoneNumber();
                        String passEmployee = account.getPassWord();
                        if (isValidCookieValue(usernameEmployee)&& isValidCookieValue(phoneNumberEmployee) && isValidCookieValue(passEmployee)) {
                            Cookie usernameCookie = new Cookie("username", usernameEmployee);
                            Cookie phoneCookie = new Cookie("PhoneNumber", phoneNumberEmployee);
                            Cookie passwordCookie = new Cookie("pass", passEmployee);

                            usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
                            passwordCookie.setMaxAge(7 * 24 * 60 * 60);
                            phoneCookie.setMaxAge(7 * 24 * 60 * 60);

                            response.addCookie(usernameCookie);
                            response.addCookie(phoneCookie);
                            response.addCookie(passwordCookie);
                            response.sendRedirect("Employee.jsp");
                            return;
                        } else {
                            // Giá trị của cookie không hợp lệ
                            errorMessage = "Giá trị cookie không hợp lệ";
                        }
                        break;                        
                    case 3:
                        // Kiểm tra giá trị của cookie trước khi thêm vào
                        String username = account.getName();
                        String phoneNumber = account.getPhoneNumber();
                        String pass = account.getPassWord();
                        if (isValidCookieValue(username)&& isValidCookieValue(phoneNumber) && isValidCookieValue(pass)) {
                            Cookie usernameCookie = new Cookie("username", username);
                            Cookie phoneCookie = new Cookie("PhoneNumber", phoneNumber);
                            Cookie passwordCookie = new Cookie("pass", pass);

                            usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
                            passwordCookie.setMaxAge(7 * 24 * 60 * 60);
                            phoneCookie.setMaxAge(7 * 24 * 60 * 60);

                            response.addCookie(usernameCookie);
                            response.addCookie(phoneCookie);
                            response.addCookie(passwordCookie);
                            response.sendRedirect("showAccount.jsp");
                            return;
                        } else {
                            // Giá trị của cookie không hợp lệ
                            errorMessage = "Giá trị cookie không hợp lệ";
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                // Đăng nhập thất bại, đặt thông báo lỗi
                errorMessage = "Tài khoản hoặc mật khẩu không đúng";
            }
        } else {
            // Số điện thoại không hợp lệ hoặc null, đặt thông báo lỗi
            errorMessage = "Số điện thoại không hợp lệ";
        }

        // Đặt thông báo lỗi trong request attribute
        request.setAttribute("errorMessage", errorMessage);

        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp?error=true");
        dispatcher.forward(request, response);
    }
    
    private boolean isValidCookieValue(String value) {
        if (value != null) {
            // Sử dụng biểu thức chính quy để kiểm tra giá trị cookie
            return value.matches("^[\\p{L}0-9_\\s'-]+$");
        }
        return false;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
