package org.openjfx.MainApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DictionaryManagement extends Dictionary {

    // ------ ------- -------- --------

    public DictionaryManagement() {
    }

    public DictionaryManagement(List<Word> list_word) {
        super(list_word);
    }

    /**
     * Thêm từ trong CommandLine.
     */
    public void insertFromcommandline() throws NumberFormatException, IOException {
        System.out.println("Your number of words: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        for (int i = 0; i < number; i++) {
            System.out.println("Your English word: ");
            String word_target = br.readLine();

            System.out.println("Vietnamese meaning: ");
            String word_explain = br.readLine();

            Word word = new Word(word_target, word_explain);
            super.addWord(word);

        }
        super.sortAlphabet();
    }


    /**
     * Xóa từ trong danh sách từ điển.
     */
    void deleteWord() throws IOException {
        System.out.println("Your word to delete:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String user_word_target = br.readLine();
        user_word_target = user_word_target.toLowerCase();

        int index = super.binarySearch(user_word_target);

        if (index == -1) {
            System.out.println("Can't find your word!");
            return;
        } else {
            super.removeWord(index);
            System.out.println("Successfully deleted");
        }
    }

    /**
     * Sửa từ trong danh sách từ điển.
     */
    void fixWord() throws IOException {
        System.out.println("Your word to fix: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String user_word_target = br.readLine();
        user_word_target = user_word_target.toLowerCase();

        int index = super.binarySearch(user_word_target);

        if (index == -1) {
            System.out.println("Can't find your word!");
            return;
        } else {
            System.out.println("New word: ");
            String new_word_target = br.readLine();
            System.out.println("New meaning: ");
            String new_word_explain = br.readLine();

            Word newWord = new Word(new_word_target, new_word_explain);
            super.updateWord(index, newWord);
            super.sortAlphabet();
            System.out.println("Successfully updated");
        }
    }


    /**
     * Xuất ra danh sách các từ trong từ điển từ file dictionaries.txt.
     */
    public void insertFromFile() throws IOException {

        try {
            String filepath = "src\\main\\java\\org\\openjfx\\MainApp\\dictionaries.txt"; //path to file in your computer

            BufferedReader br = new BufferedReader( new FileReader(filepath) );

            String line;
            while ((line = br.readLine()) != null) {
                String[] word = line.split("\t");

                Word newWord = new Word(word[0], word[1]);
                super.addWord(newWord);
                super.sortAlphabet();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Nhập vào danh sách các từ trong từ điển đến file dictionaries2.txt.
     * Ở đây dùng 1 file khác để nhập dữ liệu vào, khác với file dictionaries.txt.
     */
    public void dictionaryExportToFile() {
        FileWriter fileWriter = null;

        try {
            String filepath = "src\\main\\java\\org\\openjfx\\MainApp\\dictionaries2.txt"; //path to file in your computer

            fileWriter = new FileWriter(filepath);
            for (int i = 0; i < super.getSize(); i++) {
                fileWriter.write(super.getWord(i).getWordTarget() + "\t" + super.getWord(i).getWordExplain() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Tìm kiếm từ trong danh sách từ điển, 1 từ hoàn chỉnh.
     */
    public void dictionaryLookup() throws IOException {
        System.out.println("Your word to lookup: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word_target = br.readLine();

        word_target = word_target.toLowerCase();
        int index = super.binarySearch(word_target);

        if (index == -1) {
            System.out.println("Can't find your word!");

            return;
        } else {
            System.out.println("Meaning " + "\n" + super.getWord(index).getWordExplain());
        }
    }
}
