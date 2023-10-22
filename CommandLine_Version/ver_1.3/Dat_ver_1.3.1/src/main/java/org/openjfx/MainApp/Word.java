package org.openjfx.MainApp;

public class Word {

    // từ vựng tiếng Anh
    private String word_target;

    // giải nghĩa tiếng Việt
    private String word_explain;

    // Phương thức khởi tạo từ vựng.
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    // trả về từ vựng tiếng Anh
    public String getWordTarget() {
        return this.word_target;
    }

    // trả về giải nghĩa tiếng Việt
    public String getWordExplain() {
        return this.word_explain;
    }

    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    public void setWordExplain(String word_explain) {
        this.word_explain = word_explain;
    }
}
