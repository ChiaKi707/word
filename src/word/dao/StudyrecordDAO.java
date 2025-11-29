package word.dao;

import word.util.DBConnection;
import word.sql.SqlStudyrecord;
import java.sql.*;

public class StudyrecordDAO {

    /**
     * 为指定用户初始化单词本
     * @param userId 用户的ID
     * @return 初始化了多少条记录
     */
    public int initRecords(int userId) {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlStudyrecord.INIT_RECORDS_FOR_USER)) {
            
            // 填入 SQL 中的两个问号
            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId); // 第二个问号也是 userId，用于 NOT EXISTS 检查
            
            count = pstmt.executeUpdate();
            // System.out.println("用户 " + userId + " 初始化单词数: " + count);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 更新某个单词的学习状态
     * @param userId 当前用户ID
     * @param wordId 单词ID
     * @param known 是否认识 (true=认识, false=不认识)
     * @return 是否更新成功
     */
    public boolean updateStatus(int userId, int wordId, boolean known) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlStudyrecord.UPDATE_STATUS)) {
            
            pstmt.setBoolean(1, known);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, wordId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}