package MainDictionary.CommandLine;

import java.util.ArrayList;

import MainDictionary.Support;
import MainDictionary.Word;

public class Dictionary {
    private ArrayList<Word> list_word = new ArrayList<Word>();
    
    public Dictionary(){

    }

    public Dictionary(ArrayList<Word> list_word){
        this.list_word = list_word;
    }

    public ArrayList<Word> getList_word() {
        return list_word;
    }

    public void setList_word(ArrayList<Word> dictionary) {
        this.list_word = dictionary;
    }

    protected void addWord(Word word) {
        int index = Support.binarySearch(word.getWordTarget(), list_word);
        if(index == -1) {
            list_word.add(word);
        }
    }
}
