package org.openjfx.MainDictionary;

import java.util.ArrayList;

public class DictionaryMain {
    public static ArrayList<Word> mainDictionary = new ArrayList<>();

    public static void setMainDictionary( ArrayList<Word> mainDictionary) {
        DictionaryMain.mainDictionary = mainDictionary;
    }
}
