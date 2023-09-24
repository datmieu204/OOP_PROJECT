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

    public void remove(Word removedWord) {
        int i;
        for (i = 0; i < numOfWords; i++) {
            if (words[i] == removedWord) {
                break;
            }
        }
        if (i == numOfWords) {
            return;
        }
        while (i < numOfWords - 1) {
            Word temp = words[i];
            words[i] = words[i + 1];
            words[i + 1] = temp;
            i++;
        }
        words[numOfWords - 1] = null;
        numOfWords--;
    }

    public void update(Word target, String word_explain) {
        target.setWord_explain(word_explain);
    }
}
