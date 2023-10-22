package org.openjfx.MainApp;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private List<Word> list_word = new ArrayList<Word>();

    public Dictionary() {
    }

    public Dictionary(List<Word> list_word) {
        this.list_word = list_word;
    }

    public void addWord(Word word) {
        this.list_word.add(word);
    }

    public void removeWord(int index) {
        this.list_word.remove(index);
    }

    public void updateWord(int index, Word word) {
        this.list_word.set(index, word);
    }

    public Word getWord() {
        return this.list_word.get(this.list_word.size() - 1);
    }

    public int getSize() {
        return this.list_word.size();
    }

    public void setWords(List<Word> list_word) {
        this.list_word = list_word;
    }

    public Word getWord(int index) {
        return this.list_word.get(index);
    }

    public List<Word> getListWord() {
        return this.list_word;
    }

    public void setListWord(List<Word> list_word) {
        this.list_word = list_word;
    }

    // public List<Word> copyDictionary(List<Word> copy){
    // copy = new ArrayList<Word>();
    // for (int i = 0; i < this.list_word.size(); i++) {
    // copy.add(this.list_word.get(i));
    // }
    // return copy;
    // }

    private int partition(int l, int r) {
        Word pivot = this.list_word.get(r);
        int i = l - 1;

        for (int j = l; j < r; j++) {
            if (this.list_word.get(j).getWordTarget().compareTo(pivot.getWordTarget()) < 0) {
                i++;
                Word temp = this.list_word.get(i);
                this.list_word.set(i, this.list_word.get(j));
                this.list_word.set(j, temp);
            }
        }

        Word temp = this.list_word.get(i + 1);
        this.list_word.set(i + 1, this.list_word.get(r));
        this.list_word.set(r, temp);
        return i + 1;
    }

    private void quickSort(int l, int r) {
        if (l >= r)
            return;
        int pivot = partition(l, r);
        quickSort(l, pivot - 1);
        quickSort(pivot + 1, r);
    }


    /**
     * Hàm sắp xếp từ theo bảng chữ cái.
     * Hàm gồm 2 hàm con partition và quickSort.
     */
    public void sortAlphabet() {
        quickSort(0, this.list_word.size() - 1);
    }

    /**
     * Hàm tìm kiếm từ nhị phân.
     */
    public int binarySearch(String word_target) {
        int l = 0;
        int r = this.list_word.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (this.list_word.get(mid).getWordTarget().compareTo(word_target) == 0) {
                return mid;
            }
            if (this.list_word.get(mid).getWordTarget().compareTo(word_target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

}
