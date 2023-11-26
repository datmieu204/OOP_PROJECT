package MainDictionary;

import java.util.ArrayList;

import MainDictionary.CommandLine.Dictionary;

public class DictionaryManagementApp extends Dictionary{

    public DictionaryManagementApp() {
        super();
    }

    public DictionaryManagementApp(ArrayList<Word> list_app) {
        super(list_app);
    }

    //===================================================================================================================================

    public void insertFromFile() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        ArrayList<Word> mainList = dictionaryFile.read("src\\main\\resources\\data\\dic.txt");
        super.setList_word(mainList);
        Support.mergeSort(super.getList_word(), 0, super.getList_word().size() - 1);
    }

    //===================================================================================================================================

    public int lookUp(String word_target) {
        Support.mergeSort(super.getList_word(), 0, super.getList_word().size() - 1);
        return Support.binarySearch(word_target.toLowerCase(), super.getList_word());
    }

    public ArrayList<String> searcher(String word_target) {
        ArrayList<String> res = new ArrayList<String>();

        for (int i = 0; i < super.getList_word().size(); i++) {
            if (super.getList_word().get(i).getWordTarget().startsWith(word_target)) {
                res.add(super.getList_word().get(i).getWordTarget());
            }
        }
        return res;
    }
    //===================================================================================================================================

    public void addWord(String word_target, String word_explain) {
        Word word = new Word(word_target, word_explain);

        DictionaryUser.list_user.add(word);
        Support.mergeSort(DictionaryUser.list_user, 0, DictionaryUser.list_user.size() - 1);
        DictionaryManagementUser.exportToFileUser();
    }

}
