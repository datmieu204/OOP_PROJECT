package com.dictionary;

import java.io.File;
import java.io.FileWriter;
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

    public void showAllWords() {
        String table[][] = new String[dictionary.getNumOfWords() + 1][3];
        table[0][0] = "NO";
        table[0][1] = "English";
        table[0][2] = "Vietnamese";
        for (int index = 0; index < this.getDictionary().getNumOfWords(); index++) {
            Word temp = this.getDictionary().getWordAt(index);
            table[index + 1][0] = Integer.toString(index);
            table[index + 1][1] = temp.getWord_target();
            table[index + 1][2] = temp.getWord_explain();
        }

        // Define column widths
        int[] columnWidths = { 5, 20, 15 };

        // Print top border
        printBorder(columnWidths);

        // Print table header
        printRow(table[0], columnWidths);

        // Print header-row separator
        printSeparator(columnWidths);

        // Print table rows
        for (int i = 1; i < table.length; i++) {
            printRow(table[i], columnWidths);
        }

        // Print bottom border
        printBorder(columnWidths);

    }

    private static void printBorder(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print("+");
            for (int j = 0; j < columnWidths[i] + 2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private static void printRow(String[] rowData, int[] columnWidths) {
        for (int i = 0; i < rowData.length; i++) {
            System.out.format("| %-" + columnWidths[i] + "s ", rowData[i]);
        }
        System.out.println("|");
    }

    private static void printSeparator(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print("+");
            for (int j = 0; j < columnWidths[i] + 2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    public void dictionarySearcher(Scanner scanner) {
        System.out.print("Type your suggestion: ");
        System.out.println("Result from your suggestion: ");
        String suggestion = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < dictionary.getNumOfWords(); i++) {
            Word word = dictionary.getWordAt(i);
            if (word.getWord_target().substring(0, suggestion.length()).equals(suggestion)) {
                System.out.println(word.getWord_target());
                found = true;
            }
        }

        if (!found)
            System.out.println("No words found with your suggestion");
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

    public void exportToFile(Scanner scanner) {
        System.out.print("Your file' s name: ");
        String filename = scanner.nextLine();
        String filepath = "versioncmd/src/main/java/com/dictionary/" + filename +  ".txt";

        try {
            File file = new File(filepath);

            if (file.createNewFile()) {
                System.out.println("New file created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        String newText = "";
        for (int i = 0; i < dictionary.getNumOfWords(); i++) {
            Word word = dictionary.getWordAt(i);
            newText += word.getWord_target() + "\t" + word.getWord_explain() + "\n";
        }

        try (FileWriter writer = new FileWriter(filepath, false)) {
            writer.write(newText);
            System.out.println("Successfully export the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while rewriting the file.");
            e.printStackTrace();
        }
    }

}
