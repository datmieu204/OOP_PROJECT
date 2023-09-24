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
            System.out.println("Import succeeded!");
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void dictionaryLookup(Scanner scanner) {
        System.out.print("The word you want to look up: ");
        String word_target = scanner.nextLine();
        Word target = dictionary.getWord(word_target);
        if (target != null) {
            System.out.println("The meaning: " + target.getWord_explain());
        } else {
            System.out.println("Can't find " + word_target + " in dictionary");
        }
    }

    public void removeFromCommandline(Scanner scanner) {
        System.out.print("The word you want to remove: ");
        String word_target = scanner.nextLine();
        Word target = dictionary.getWord(word_target);
        if (target != null) {
            dictionary.remove(target);
            System.out.println("Word removed!");
        } else {
            System.out.println("Can't find " + word_target + " in dictionary");
        }
    }

    public void updateFromCommandline(Scanner scanner) {
        System.out.print("The word you want to update: ");
        String word_target = scanner.nextLine();
        Word target = dictionary.getWord(word_target);
        if (target != null) {
            System.out.print("New explaination of the word: ");
            String word_explain = scanner.nextLine();
            dictionary.update(target, word_explain);
            System.out.println("Word updated!");
        } else {
            System.out.println("Can't find " + word_target + " in dictionary");
        }
    }

}
