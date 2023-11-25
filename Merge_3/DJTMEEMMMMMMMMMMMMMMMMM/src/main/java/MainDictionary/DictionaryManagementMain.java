package MainDictionary;

import java.util.ArrayList;

public class DictionaryManagementMain {

    public static void insertFromFile() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        ArrayList<Word> mainList = dictionaryFile.read("src\\main\\resources\\data\\dic.txt");
        DictionaryMain.setMainDictionary(mainList);
    }

    //===================================================================================================================================

    public static int lookUp(String word_target) {
        Support.mergeSort(DictionaryMain.mainDictionary, 0, DictionaryMain.mainDictionary.size() - 1);
        return Support.binarySearch(word_target, DictionaryMain.mainDictionary);
    }

    public static ArrayList<String> searcher(String word_target) {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < DictionaryMain.mainDictionary.size(); i++) {
            if (DictionaryMain.mainDictionary.get(i).getWordTarget().startsWith(word_target)) {
                res.add(DictionaryMain.mainDictionary.get(i).getWordTarget());
            }
        }
        return res;
    }
    //===================================================================================================================================

    public static void addWord(String word_target, String word_explain) {
        Word word = new Word(word_target, word_explain);
        DictionaryUser.list_user.add(word);
        Support.mergeSort(DictionaryUser.list_user, 0, DictionaryUser.list_user.size() - 1);
        DictionaryManagementUser.exportToFileUser();
    }

}
