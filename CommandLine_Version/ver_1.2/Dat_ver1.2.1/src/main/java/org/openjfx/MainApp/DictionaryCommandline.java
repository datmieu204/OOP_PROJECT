package org.openjfx.MainApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;
import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement(); // ban chinh
    private DictionaryManagement userDicionary = (DictionaryManagement) dictionaryManagement; // ban sao

    public DictionaryCommandline() {
    }

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("");
        System.out.println("No\t|English\t|Vietnamese");

        // for (int i = 0; i < dictionaryManagement.getSize(); i++) {
        //     System.out.println(i + 1 + "\t|"
        //             + dictionaryManagement.getWord(i).getWordTarget()
        //             + "\t\t|"
        //             + dictionaryManagement.getWord(i).getWordExplain());
        // }

        for(int i = 0; i < userDicionary.getSize(); i++) {
            System.out.println(i + 1 + "\t|"
                    + userDicionary.getWord(i).getWordTarget()
                    + "\t\t|"
                    + userDicionary.getWord(i).getWordExplain());
        }

    }

    public void dictionaryBasic() throws NumberFormatException, IOException {
        dictionaryManagement.insertFromcommandline();
        showAllWords();
    }

    public void dictionarySearcher() throws IOException {
        //Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhap tu can tim: ");
        //String word_search = scanner.nextLine();
        String word_search = br.readLine();

        System.out.println("No\t|English\t\t|Vietnamese");

        for (int i = 0; i < dictionaryManagement.getSize(); i++) {
            if (dictionaryManagement.getWord(i).getWordTarget().startsWith(word_search)) {
                System.out.println(i + 1 + "\t\t|"
                        + dictionaryManagement.getWord(i).getWordTarget()
                        + "\t\t\t\t|"
                        + dictionaryManagement.getWord(i).getWordExplain());
            }
        }
    }

    public void dictionaryAdvanced() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.println("Enter your choice: ");

            try {
                int user_choice = Integer.parseInt(br.readLine());

                switch (user_choice) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        dictionaryManagement.insertFromcommandline();
                        System.out.println();
                        break;
                    case 2:
                        dictionaryManagement.removeWord(user_choice);
                        break;
                    case 3:
                        dictionaryManagement.updateWord(user_choice, null);
                        break;
                    case 4:
                        showAllWords();
                        new Scanner(System.in).nextLine();
                        break;
                    case 5:
                        dictionaryManagement.dictionaryLookup();
                        new Scanner(System.in).nextLine();
                        break;
                    case 6:
                        dictionarySearcher();
                        new Scanner(System.in).nextLine();
                        break;
                    case 7:
                        // dictionaryManagement.dictionaryGame();
                        break;
                    case 8:
                        dictionaryManagement.insertFromFile();
                        new Scanner(System.in).nextLine();
                        break;
                    case 9:
                        dictionaryManagement.dictionaryExportToFile();
                        System.out.println();
                        break;
                    default:
                        System.out.println("Action not supported");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number from 0 to 9");
            }
            //br.readLine(); // enter de tiep tuc
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        // dictionaryCommandline.dictionaryBasic();
        dictionaryCommandline.dictionaryAdvanced();
    }
}
