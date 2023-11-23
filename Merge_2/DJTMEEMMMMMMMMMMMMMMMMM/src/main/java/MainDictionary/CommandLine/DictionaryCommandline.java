package MainDictionary.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public DictionaryCommandline() {

    }

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("");
        System.out.printf("%-5s|%-20s|%-20s\n", "No", "English", "Vietnamese");

        for (int i = 0; i < dictionaryManagement.getList_word().size(); i++) {
            
            System.out.printf("%-5d|%-20s|%-20s\n", i + 1,
                    dictionaryManagement.getList_word().get(i).getWordTarget(),
                    dictionaryManagement.getList_word().get(i).getWordExplain());
        }
    }

    public void dictionaryBasic() throws NumberFormatException, IOException {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    protected void dictionarySearcher() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your word: ");
        String word_search = br.readLine();

        System.out.printf("%-5s|%-20s|%-20s\n", "No", "English", "Vietnamese");

        for(int i = 0; i < dictionaryManagement.getList_word().size(); i++) {
            if(dictionaryManagement.getList_word().get(i).getWordTarget().startsWith(word_search)) {
                System.out.printf("%-5d|%-20s|%-20s\n", i + 1,
                        dictionaryManagement.getList_word().get(i).getWordTarget(),
                        dictionaryManagement.getList_word().get(i).getWordExplain());
            }
        }
    }

    public void dictionaryAdvanced() throws NumberFormatException, IOException {
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
                        dictionaryManagement.insertFromCommandline();
                        System.out.println();
                        break;
                    case 2:
                        dictionaryManagement.deleteWord();
                        System.out.println();
                        break;
                    case 3:
                        dictionaryManagement.fixWord();
                        System.out.println();
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
                        dictionaryManagement.playGame();
                        new Scanner(System.in).nextLine();
                        break;
                    case 8:
                        dictionaryManagement.insertFromFile();
                        new Scanner(System.in).nextLine();
                        break;
                    case 9:
                        dictionaryManagement.exportToFile();
                        new Scanner(System.in).nextLine();
                        break;
                    default:
                        System.out.println("Action not supported");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again! Enter a number from 0 to 9");
            }
        }
    }
}
