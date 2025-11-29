package word.dao;

import word.model.User;
import word.util.DBConnection;
import word.sql.SqlUser;
import java.sql.*;

public class UserDAO {

    /**
     * 用户注册
     * @param user 包含用户名和密码的 User 对象
     * @return 注册是否成功 (true: 成功, false: 失败/用户名已存在)
     */
    public boolean register(User user) {
        // 使用 try-with-resources 自动关闭连接，防止内存泄漏
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlUser.REGISTER)) {
            
            // 填空：把 User 对象里的数据填入 SQL 的 ? 占位符
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
            // 执行更新 (返回影响的行数，大于0表示成功)
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            // 如果报错（比如用户名重复），打印错误信息
            System.err.println("注册失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 用户登录
     * @param username 输入的用户名
     * @param password 输入的密码
     * @return 登录成功返回 User 对象 (包含ID)，失败返回 null
     */
    public User login(String username, String password) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlUser.LOGIN)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            // 如果查到了结果 (rs.next() 为 true)
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id")); // 从数据库拿到自动生成的 ID
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 查不到用户或密码错误
    }
}