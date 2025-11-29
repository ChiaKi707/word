package word.model;

public class Word {
    private int id;
    private String english;
    private String chinese;
    private String category; // 【新增】分类
    private String exampleEn;
    private String exampleCn;
    
    public Word() {}

    // 简单的构造函数
    public Word(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }
    
    // 全参构造函数（可选更新，或者保持原样）
    public Word(int id, String english, String chinese, String category, String exampleEn, String exampleCn) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
        this.category = category;
        this.exampleEn = exampleEn;
        this.exampleCn = exampleCn;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEnglish() { return english; }
    public void setEnglish(String english) { this.english = english; }

    public String getChinese() { return chinese; }
    public void setChinese(String chinese) { this.chinese = chinese; }

    // 【新增】Category 的 Getter/Setter
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getExampleEn() { return exampleEn; }
    public void setExampleEn(String exampleEn) { this.exampleEn = exampleEn; }

    public String getExampleCn() { return exampleCn; }
    public void setExampleCn(String exampleCn) { this.exampleCn = exampleCn; }

    @Override
    public String toString() {
        return english + " (" + category + ")";
    }
}