package word.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset; // 1. 必须引入这个
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataImporter {

    public static void importFromTxt(String fileName, String category) {
        // ... (SQL 语句保持不变)
        String sql = "INSERT INTO Words (english, chinese, category) VALUES (?, ?, ?)";
        
        InputStream is = DataImporter.class.getResourceAsStream("/word/resource/data/" + fileName);
        
        if (is == null) {
            System.err.println("? 错误：找不到文件 [" + fileName + "]");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             // 【核心修改】这里必须改成 GBK，因为你刚才把文件存为了 ANSI
             BufferedReader reader = new BufferedReader(
                 new InputStreamReader(is, Charset.forName("GBK")) 
             )) {
            
            // ... (中间的读取逻辑保持不变) ...
            
            conn.setAutoCommit(false); 
            String line;
            int count = 0;
            System.out.println("开始读取文件 (GBK模式): " + fileName + " ...");

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                // 确保你的txt里是用 # 分隔的
                String[] parts = line.split("#"); 
                
                if (parts.length >= 2) {
                    pstmt.setString(1, parts[0].trim()); 
                    pstmt.setString(2, parts[1].trim()); 
                    pstmt.setString(3, category);       
                    pstmt.addBatch(); 
                    count++;
                    
                    if (count % 1000 == 0) {
                        pstmt.executeBatch();
                        conn.commit();
                        System.out.println("-> 已导入 " + count + " 条...");
                    }
                }
            }
            pstmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true); 
            
            System.out.println("? 导入完成！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}