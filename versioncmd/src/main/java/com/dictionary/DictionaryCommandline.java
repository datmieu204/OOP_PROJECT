package com.dictionary;

import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement service;
    private Scanner scanner;

    public DictionaryCommandline() {
        service = new DictionaryManagement();
        scanner = new Scanner(System.in);
    }

    public void dictionaryBasic() {
        int request = -1;
        System.out.println("Welcome to My Application!");
        while (request != 0) {
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Show");
            System.out.println("[8] Import from file");
            System.out.print("Your request: ");

            request = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer

            switch (request) {
                case 0:
                    break;
                case 1:
                    service.insertFromCommandline(scanner);
                    break;
                case 2:
                    showAllWords();
                    break;
                case 8:
                    service.insertFromFile(scanner);
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
        }
        scanner.close();
    }

    private void showAllWords() {
        System.out.println("NO\t" + "| ENGLISH\t" + "| Vietnamese");
        for (int index = 0; index < service.getDictionary().getNumOfWords(); index++) {
            Word temp = service.getDictionary().getWordAt(index);
            System.out.println(
                    index + 1 + "\t" + "| " + temp.getWord_target() + "\t" + "\t" + "| " + temp.getWord_explain());
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline cmdline = new DictionaryCommandline();
        cmdline.dictionaryBasic();
    }
}
