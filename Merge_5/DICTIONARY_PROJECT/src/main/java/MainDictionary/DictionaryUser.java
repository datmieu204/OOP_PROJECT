package MainDictionary;

import java.util.ArrayList;

public class DictionaryUser {
    public static ArrayList<Word> list_user = new ArrayList<Word>();

    public static void setList_user(ArrayList<Word> list_user) {
        DictionaryUser.list_user = list_user;
    }

}
