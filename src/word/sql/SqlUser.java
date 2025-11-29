package word.sql;

public class SqlUser {
    // 用户注册
    public static final String REGISTER = "INSERT INTO Users (username, password) VALUES (?, ?)";
    // 用户登录
    public static final String LOGIN = "SELECT * FROM Users WHERE username = ? AND password = ?";
}