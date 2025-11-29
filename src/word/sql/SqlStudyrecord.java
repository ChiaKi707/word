package word.sql;

public class SqlStudyrecord {
    
    /**
     * 初始化用户的学习记录
     * 逻辑：当新用户注册时，把词库里所有的单词都插入到这个用户的记录里，默认状态是 unknown (false)
     * 这样我们在查询“未掌握单词”时，才能查到数据。
     */
    public static final String INIT_RECORDS_FOR_USER = 
        "INSERT INTO StudyRecords (user_id, word_id, known) " +
        "SELECT ?, id, false FROM Words " +
        "WHERE NOT EXISTS (SELECT 1 FROM StudyRecords WHERE user_id = ? AND word_id = Words.id)";

    /**
     * 更新单词的学习状态
     * 逻辑：当用户点击“认识”或“不认识”时，更新 known 字段
     */
    public static final String UPDATE_STATUS = 
        "UPDATE StudyRecords SET known = ? WHERE user_id = ? AND word_id = ?";
    
    /**
     * 查询单个记录的状态（可选，用于验证）
     */
    public static final String GET_RECORD_STATUS = 
        "SELECT known FROM StudyRecords WHERE user_id = ? AND word_id = ?";
}