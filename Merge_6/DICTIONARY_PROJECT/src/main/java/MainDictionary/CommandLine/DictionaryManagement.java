package MainDictionary.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import MainDictionary.DictionaryFile;
import MainDictionary.Support;
import MainDictionary.Word;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {

    }

    public DictionaryManagement(ArrayList<Word> dictionary) {
        super(dictionary);
    }

    protected void insertFromCommandline()
            throws NumberFormatException, IOException {
        System.out.println("Your number of words: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        for (int i = 0; i < number; i++) {
            System.out.println("Word target: ");
            String word_target = br.readLine();

            System.out.println("Word explain: ");
            String word_explain = br.readLine();

            Word word = new Word(word_target, word_explain);
            super.addWord(word);

            Support.mergeSort(super.getList_word(), 0, super.getList_word().size() - 1);
        }
    }

    protected void insertFromFile() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        ArrayList<Word> dictionary = dictionaryFile
                .read("src\\main\\resources\\data\\dictionaryCmd.txt");
        super.setList_word(dictionary);
        Support.mergeSort(super.getList_word(), 0, super.getList_word().size() - 1);
    }

    protected void exportToFile() {
        DictionaryFile dictionaryFile = new DictionaryFile();
        dictionaryFile.write("src\\main\\resources\\data\\dictionaryCmdSave.txt", super.getList_word());
    }

    protected void dictionaryLookup() throws IOException {
        System.out.println("Your word to lookup: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word_target = br.readLine();

        int index = Support.binarySearch(word_target.toLowerCase(), super.getList_word());

        if (index == -1) {
            System.out.println("This word is not in the dictionary");
            return;
        } else {
            System.out.println("Meaning:" + "\n" + super.getList_word().get(index).getWordExplain());
        }
    }

    protected void addNewWord() {

    }

    protected void deleteWord() throws IOException {
        System.out.println("Your word to delete: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String user_word_target = br.readLine();

        int index = Support.binarySearch(user_word_target.toLowerCase(), super.getList_word());

        if (index == -1) {
            System.out.println("Can't find your word!");
            return;
        } else {
            super.getList_word().remove(index);
            System.out.println("Successfully deleted");
        }
    }

    protected void fixWord() throws IOException {
        System.out.println("Your word to fix: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String user_word_target = br.readLine();

        int index = Support.binarySearch(user_word_target.toLowerCase(), super.getList_word());

        if (index == -1) {
            System.out.println("Can't find your word!");
            return;
        } else {
            System.out.println("New word: ");
            String new_word_target = br.readLine();

            System.out.println("New meaning: ");
            String new_word_explain = br.readLine();

            Word newWord = new Word(new_word_target, new_word_explain);
            super.getList_word().set(index, newWord);
            Support.mergeSort(super.getList_word(), 0, super.getList_word().size() - 1);
            System.out.println("Successfully updated");
        }
    }

    protected void playGame() {
        Random rand = new Random();
        int index = rand.nextInt(super.getList_word().size());

        System.out.println("Your word: " + super.getList_word().get(index).getWordTarget());
        System.out.println("Enter your answer: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        String answer = "";

        while(true) {
            try {
                answer = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (answer.toLowerCase().equals(super.getList_word().get(index).getWordExplain())) {
                System.out.println("Correct!");
                break;
            } else {
                System.out.println("Wrong!");
                System.out.println("Enter your answer again:");
            }
        }
    }
}
