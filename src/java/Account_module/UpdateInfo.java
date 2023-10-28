/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Account_module;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Quoc Hung
 */
@WebServlet(name = "UpdateInfo", urlPatterns = {"/UpdateInfo"})
public class UpdateInfo extends HttpServlet {

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
            out.println("<title>Servlet UpdateInfo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInfo at " + request.getContextPath() + "</h1>");
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
        
        Cookie[] cookies = request.getCookies();
        String phone = "";
        String fullname = "";
        String password = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("PhoneNumber")) {
                    phone = cookie.getValue();
                } else if (cookie.getName().equals("username")) {
                    fullname = cookie.getValue();
                } else if (cookie.getName().equals("pass")) {
                    password = cookie.getValue();
                }
            }
        }
        
        String PhoneNumber = request.getParameter("phoneNumber");
        String newUsername = request.getParameter("username");
        String newPassword = request.getParameter("password");
        
        boolean updateSuccess = AcountModel.updateUserInfo(phone,newPassword,newUsername);
        if (updateSuccess) {
            // Cập nhật thành công, cập nhật giá trị trong cookies
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("phone")) {
                    cookie.setValue(PhoneNumber);
                    response.addCookie(cookie);
                } else if (cookie.getName().equals("username")) {
                    cookie.setValue(newUsername);
                    response.addCookie(cookie);
                } else if (cookie.getName().equals("pass")) {
                    cookie.setValue(newPassword);
                    response.addCookie(cookie);
                }
            }
            
            String message = "Cập nhật thành công";
            response.getWriter().println("<script>alert('" + message + "'); window.location.href = 'Profile.jsp';</script>");
        } else {
            String errorMessage = "Cập nhật không thành công";
            response.getWriter().println("<script>alert('" + errorMessage + "'); window.location.href = 'Profile.jsp';</script>");
        }
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
