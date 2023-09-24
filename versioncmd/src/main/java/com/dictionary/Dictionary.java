package com.dictionary;

public class Dictionary {
    private static final int MAX_WORD = 10000;
    private Word[] words = new Word[MAX_WORD];
    private int numOfWords;

    public Dictionary() {
        numOfWords = 0;
    }

    public int getNumOfWords() {
        return this.numOfWords;
    }

    public Word getWordAt(int index) {
        return this.words[index];
    }

    public void put(Word newWord) {
        words[numOfWords++] = newWord;
    }

    public Word getWord(String word_target) {
        for (int i = 0; i < numOfWords; i++) {
            if (words[i].getWord_target().equals(word_target))
                return words[i];
        }
        return null;
    }
}
