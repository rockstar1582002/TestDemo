/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account_module;

import DAL.MySQLConnect;
import Account_module.Account;
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AcountModel {
    private Connection connection;

    public AcountModel(){
        connection = MySQLConnect.getConnection();
    }
    
    public Account getUserByPhoneAndPassword(String phone, String password) {
        Account account = null;
        try {
            String sql = "SELECT * FROM user WHERE phone = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setPhoneNumber(resultSet.getString("phone"));
                account.setPassWord(resultSet.getString("pass"));
                account.setName(resultSet.getString("name"));
                account.setType(resultSet.getInt("type"));
                // Các thuộc tính khác
                
                // Đóng tài nguyên
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public static void Register(String phoneNumber, String password, String username) {
        try (Connection conn = MySQLConnect.getConnection()) {
            String getMaxIdQuery = "SELECT MAX(id) FROM user";
            PreparedStatement st = conn.prepareStatement(getMaxIdQuery);
            ResultSet resultSet = st.executeQuery();

            int currentMaxId = 0;

            if (resultSet.next()) {
                currentMaxId = resultSet.getInt(1);
            }
            // Tạo giá trị 'id' mới bằng cách tăng giá trị 'id' lớn nhất hiện tại thêm một đơn vị
            String newId = String.valueOf(currentMaxId + 1);
            // Tạo câu lệnh SQL với cột ID tự động tăng
            String sql = "INSERT INTO user (id, phone, pass, name, createdAt, updatedAt, status, type) VALUES (?, ?, ?, ?, NOW(), NOW(), 1, 3)";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, newId);
                statement.setString(2, phoneNumber);
                statement.setString(3, password);
                statement.setString(4, username);

                int rowsInserted = statement.executeUpdate(); // Thực hiện câu lệnh SQL

                if (rowsInserted > 0) {
                System.out.println("Đăng ký tài khoản thành công, ID của tài khoản mới là: " + newId);
                } else {
                System.out.println("Đăng ký tài khoản thất bại.");
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Đăng ký tài khoản thất bại");
        }
    }
    
    public static boolean isPhoneNumberExists(String phoneNumber) {
        try (Connection conn = MySQLConnect.getConnection()) {
            String sql = "SELECT phone FROM user WHERE phone = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, phoneNumber);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Nếu số điện thoại đã tồn tại, ResultSet sẽ có ít nhất một kết quả
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
    
    public static void updateUserPassword(String phoneNumber, String newPassword) {
        try (Connection connection = MySQLConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET pass = ? WHERE phone = ?")) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean updateUserInfo(String Phone, String newPass, String newUserName){
        try (Connection conn = MySQLConnect.getConnection()){
            String sql = "SELECT phone FROM user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                String updateSQL = "UPDATE user SET name = ?, pass = ? WHERE phone = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
                updateStatement.setString(1, newUserName);
                updateStatement.setString(2, newPass);
                updateStatement.setString(3, Phone);
                
                int rowsAffected = updateStatement.executeUpdate();
                
                if(rowsAffected > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        String phone = "0123456789";  // Thay thế bằng số điện thoại cần cập nhật
        String newUsername = "HungHoang";    // Thay thế bằng tên người dùng mới
        String newPassword = "12345678910";    // Thay thế bằng mật khẩu mới

        try {
            boolean updated = AcountModel.updateUserInfo(phone, newPassword, newUsername);

            if (updated) {
                System.out.println("Thông tin người dùng đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy người dùng hoặc có lỗi xảy ra khi cập nhật thông tin người dùng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Có lỗi ngoại lệ xảy ra khi cập nhật thông tin người dùng.");
        }
    }
}
