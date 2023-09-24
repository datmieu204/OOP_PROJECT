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
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Look up");
            System.out.println("[6] Search");
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
                    service.removeFromCommandline(scanner);
                    break;
                case 3:
                    service.updateFromCommandline(scanner);
                    break;
                case 4:
                    service.showAllWords();
                    break;
                case 5:
                    service.dictionaryLookup(scanner);
                    break;
                case 6:
                    service.dictionarySearcher(scanner);
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

    public static void main(String[] args) {
        DictionaryCommandline cmdline = new DictionaryCommandline();
        cmdline.dictionaryBasic();
    }
}
