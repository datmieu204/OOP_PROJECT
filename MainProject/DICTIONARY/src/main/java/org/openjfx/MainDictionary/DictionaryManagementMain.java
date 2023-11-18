package org.openjfx.MainDictionary;

import java.util.ArrayList;

public class DictionaryManagementMain {

    public static void insertFromFile() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        ArrayList<Word> mainList = dictionaryFile.read("src\\main\\resources\\org\\openjfx\\MainApp\\dic.txt");
        Dictionary.setMainDictionary(mainList);
    }

    //===================================================================================================================================

    public static int lookUp(String word_target) {
        Support.mergeSort(Dictionary.mainDictionary, 0, Dictionary.mainDictionary.size() - 1);
        return Support.binarySearch(word_target, Dictionary.mainDictionary);
    }

    public static ArrayList<String> searcher(String word_target) {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < Dictionary.mainDictionary.size(); i++) {
            if (Dictionary.mainDictionary.get(i).getWordTarget().startsWith(word_target)) {
                res.add(Dictionary.mainDictionary.get(i).getWordTarget());
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
