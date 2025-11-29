package word.resource;

/**
 * 数据库配置常量类
 * 作用：统一管理数据库连接信息，方便维护
 */
public class DbProperties {
    // 数据库名称：WordLearningDB
    // create=true 表示如果数据库不存在，Derby会自动在项目根目录创建它
    public static final String DB_URL = "jdbc:derby:WordLearningDB;create=true";
    
    // Derby 嵌入式模式通常不需要复杂的用户名密码，但为了规范我们预留
    public static final String USER = "";
    public static final String PASSWORD = "";
    
    // 驱动名称
    public static final String DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
}