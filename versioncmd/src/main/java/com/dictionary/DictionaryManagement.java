package com.dictionary;

import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    public Dictionary getDictionary() {
        return this.dictionary;
    }

    public void insertFromCommandline(Scanner scanner) {
        System.out.print("Number of words to insert: ");

        int numOfWords = scanner.nextInt();
        scanner.nextLine();

        while (numOfWords > 0) {
            System.out.print("Your word in English: ");
            String word_target = scanner.nextLine();
            System.out.print("Your word in Vietnamese: ");
            String word_explain = scanner.nextLine();
            Word newWord = new Word(word_target, word_explain);
            dictionary.put(newWord);
            numOfWords--;
        }
    }
}
