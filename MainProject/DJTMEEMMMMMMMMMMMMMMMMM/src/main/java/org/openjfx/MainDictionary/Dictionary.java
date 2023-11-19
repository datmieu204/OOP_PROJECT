package org.openjfx.MainDictionary;

import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> mainDictionary = new ArrayList<>();

    public static void setMainDictionary( ArrayList<Word> mainDictionary) {
        Dictionary.mainDictionary = mainDictionary;
    }
}
