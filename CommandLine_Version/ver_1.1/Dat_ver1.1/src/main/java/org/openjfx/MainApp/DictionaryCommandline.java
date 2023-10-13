package org.openjfx.MainApp;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public DictionaryCommandline() {
    }

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("");
        System.out.println("No\t|English\t\t|Vietnamese");

        for (int i = 0; i < dictionaryManagement.getSize(); i++) {
            System.out.println(i + 1 + "\t|"
                    + dictionaryManagement.getWord(i).getWordTarget()
                    + "\t\t\t|"
                    + dictionaryManagement.getWord(i).getWordExplain());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromcommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryBasic();
    }
}
