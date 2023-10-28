/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Account_module;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Nguyen Quoc Hung
 */
@WebServlet(name = "ResetPasswordControl", urlPatterns = {"/ResetPasswordControl"})
public class ResetPasswordControl extends HttpServlet {
    

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
            out.println("<title>Servlet ResetPasswordControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordControl at " + request.getContextPath() + "</h1>");
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
        String phoneNumber = request.getParameter("PhoneNumber");
        String confirmPhoneNumber = request.getParameter("Confirm");

        // Kiểm tra xem số điện thoại chỉ chứa chữ số
        if (isValidPhoneNumber(phoneNumber) && phoneNumber.equals(confirmPhoneNumber)) {
            // Số điện thoại trùng khớp, tạo mật khẩu mới và cập nhật trong CSDL
            String newPassword = generateRandomPassword();
            AcountModel.updateUserPassword(phoneNumber, newPassword);

            // Chuyển hướng đến trang message.jsp để hiển thị thông báo
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("newPassword", newPassword);
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        } else {
            // Số điện thoại không hợp lệ hoặc không khớp, thông báo lỗi
            String errorMessage = "Lỗi: Số điện thoại không hợp lệ hoặc không khớp.";
            response.setContentType("text/html");
            response.getWriter().println("<script>alert('" + errorMessage + "'); window.location.href = 'Login.jsp';</script>");
        }     
    }
    
    private String generateRandomPassword() {
        // Tạo mật khẩu mới ngẫu nhiên (ví dụ: mật khẩu gồm 8 ký tự)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }
        return password.toString();
    }
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Sử dụng regex để kiểm tra số điện thoại chỉ chứa chữ số
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, phoneNumber);
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
