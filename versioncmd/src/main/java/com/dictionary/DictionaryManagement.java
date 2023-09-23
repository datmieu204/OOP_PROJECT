package com.dictionary;

import java.io.File;
import java.io.IOException;
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

    public void insertFromFile(Scanner scanner) {
        String filePath = "versioncmd\\src\\main\\java\\com\\dictionary\\dictionaries.txt";

        try {
            File file = new File(filePath);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\t");
                if (words.length == 2) {
                    String word_target = words[0];
                    String word_explain = words[1];
                    Word newWord = new Word(word_target, word_explain);
                    dictionary.put(newWord);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
}
