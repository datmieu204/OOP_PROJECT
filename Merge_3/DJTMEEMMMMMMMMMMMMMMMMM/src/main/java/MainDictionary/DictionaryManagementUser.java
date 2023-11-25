package MainDictionary;

import java.util.ArrayList;

public class DictionaryManagementUser {

    public static void insertFromFileUser() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        ArrayList<Word> userList = dictionaryFile
                .read("src\\main\\resources\\data\\dictionaryUser.txt");
        DictionaryUser.setList_user(userList);
    }

    public static void exportToFileUser() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        dictionaryFile.write("src\\main\\resources\\data\\dictionaryUser.txt", DictionaryUser.list_user);
    }
    // ===================================================================================================================================

    public static int lookUpUser(String word_target) {
        Support.mergeSort(DictionaryUser.list_user, 0, DictionaryUser.list_user.size() - 1);
        return Support.binarySearch(word_target, DictionaryUser.list_user);
    }

    public static ArrayList<String> searcherUser(String word_target) {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < DictionaryUser.list_user.size(); i++) {
            if (DictionaryUser.list_user.get(i).getWordTarget().startsWith(word_target)) {
                res.add(DictionaryUser.list_user.get(i).getWordTarget());
            }
        }
        return res;
    }

    // ===================================================================================================================================
    public static void addWordUser(String word_target, String word_explain) {
        Word word = new Word(word_target, word_explain);
        DictionaryUser.list_user.add(word);
        Support.mergeSort(DictionaryUser.list_user, 0, DictionaryUser.list_user.size() - 1);
        DictionaryManagementUser.exportToFileUser();
    }

    public static void deleteWordUser(int indexWord) {
        DictionaryUser.list_user.remove(indexWord);
        DictionaryManagementUser.exportToFileUser();
    }

    public static String fixUserWord(String fix_target, String fix_explain) {
        Word word = new Word(fix_target, fix_explain);
        for (int i = 0; i < DictionaryUser.list_user.size(); i++) {
            if (DictionaryUser.list_user.get(i).getWordTarget().equals(fix_target.toLowerCase())) {
                DictionaryUser.list_user.set(i, word);
            }
        }
        DictionaryManagementUser.exportToFileUser();
        return fix_explain;
    }
}
