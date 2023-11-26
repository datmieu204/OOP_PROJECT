package MainDictionary;

public class Word {

    private String word_target;

    private String word_explain;

    public Word(String word_target, String word_explain) {
        this.word_target = word_target.toLowerCase();
        this.word_explain = word_explain.toLowerCase();
    }

    public String getWordTarget() {
        return this.word_target;
    }

    public String getWordExplain() {
        return this.word_explain;
    }

    public void setWordTarget(String word_target) {
        this.word_target = word_target.toLowerCase();
    }

    public void setWordExplain(String word_explain) {
        this.word_explain = word_explain.toLowerCase();
    }
}
