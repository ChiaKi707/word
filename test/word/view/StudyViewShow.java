/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package word.view;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用于手动测试 StudyView 界面的显示效果和交互逻辑
 * 模拟 Controller 的行为
 * @author yuzhe
 */
public class StudyViewShow {
    
    // 模拟一些测试数据
    private static String[] words = {"Apple", "Banana", "Cherry", "Date"};
    private static String[] meanings = {"n. 苹果", "n. 香蕉", "n. 樱桃", "n. 枣"};
    // 使用数组来包装索引，以便在 Lambda 表达式中修改
    private static int[] currentIndex = {0}; 

    public static void main(String[] args) {
        // 1. 设置外观风格 (Windows风格等)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        // 2. 启动 GUI 线程
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // --- 创建 View ---
            StudyView view = new StudyView();
            
            // --- 初始化第一条数据 ---
            view.setWord(words[0]);
            view.setMeaning(meanings[0]);
            
            // *** 关键：初始状态设为“答题阶段” (隐藏答案，显示选择按钮) ***
            view.switchMode(true);
            
            // --- 模拟 Controller：给按钮添加临时逻辑 ---
            
            // 逻辑1：点击“认识” -> 切换到结果模式
            view.addKnownListener(e -> {
                System.out.println("点击了：我认识");
                view.switchMode(false); // 显释义，显下一个
            });

            // 逻辑2：点击“不认识” -> 切换到结果模式
            view.addUnknownListener(e -> {
                System.out.println("点击了：不认识");
                view.switchMode(false); // 显释义，显下一个
            });

            // 逻辑3：点击“下一个” -> 加载新词，重置回答题模式
            view.addNextListener(e -> {
                System.out.println("点击了：下一个");
                
                // 索引 +1
                currentIndex[0]++;
                
                // 判断是否还有词
                if (currentIndex[0] < words.length) {
                    // 设置新词
                    view.setWord(words[currentIndex[0]]);
                    view.setMeaning(meanings[currentIndex[0]]);
                    
                    // *** 关键：重置回“答题阶段” ***
                    view.switchMode(true);
                } else {
                    JOptionPane.showMessageDialog(view, "测试数据演示完毕！");
                    // 重置回第一个，方便循环测试
                    currentIndex[0] = 0;
                    view.setWord(words[0]);
                    view.setMeaning(meanings[0]);
                    view.switchMode(true);
                }
            });

            // --- 显示窗口 ---
            view.setVisible(true);
        });
    }
}