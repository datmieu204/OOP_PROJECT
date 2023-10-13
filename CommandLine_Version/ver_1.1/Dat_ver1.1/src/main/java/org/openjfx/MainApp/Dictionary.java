package org.openjfx.MainApp;

public class Dictionary {
    private Word[] words = new Word[100000];
    private int size_of_dic = 0;

    public Dictionary() {
        this.size_of_dic = 0;
    }

    public Dictionary(Word[] words, int size_of_dic) {
        this.words = words;
        this.size_of_dic = size_of_dic;
    }

    public void addWord(Word word) {
        this.words[this.size_of_dic] = word;
        this.size_of_dic++;
    }

    public void removeWord(int index) {
        for (int i = index; i < this.size_of_dic - 1; i++) {
            this.words[i] = this.words[i + 1];
        }
        this.size_of_dic--;
    }

    public void editWord(int index, Word word) {
        this.words[index] = word;
    }

    public Word getWord() {
        return this.words[this.size_of_dic - 1];
    }

    public int getSize() {
        return this.size_of_dic;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

    public Word getWord(int index) {
        return this.words[index];
    }

    private int partition(int l, int r) {
        Word pivot = this.words[r];
        int i = l - 1;

        for (int j = l; j < r; j++) {
            if (this.words[j].getWordTarget().compareTo(pivot.getWordTarget()) < 0) {
                i++;
                Word temp = this.words[i];
                this.words[i] = this.words[j];
                this.words[j] = temp;
            }
        }

        Word temp = this.words[i + 1];
        this.words[i + 1] = this.words[r];
        this.words[r] = temp;
        return i + 1;
    }

    private void quickSort(int l, int r) {
        if (l >= r)
            return;
        int pivot = partition(l, r);
        quickSort(l, pivot - 1);
        quickSort(pivot + 1, r);
    }

    public void sortAlphabet() {
        quickSort(0, this.size_of_dic - 1);
    }
}
