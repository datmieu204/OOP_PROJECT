package org.openjfx.MainApp;

import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    public DictionaryManagement() {
    }

    public DictionaryManagement(List<Word> list_word) {
        super(list_word);
    }


    public void insertFromcommandline() throws NumberFormatException, IOException {
        System.out.println("Nhap so luong tu: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        for (int i = 0; i < number; i++) {
            System.out.println("Nhap tu tieng Anh: ");
            String word_target = br.readLine();

            System.out.println("Nhap nghia tieng Viet: ");
            String word_explain = br.readLine();

            Word word = new Word(word_target, word_explain);
            super.addWord(word);

        }
        super.sortAlphabet();
        //sc.close();
    }

    // public void deleteUserWord(){
    //     for (int i = 0; i < user_delete.size(); i++) {
    //         super.removeWord(super.binarySearch(user_delete.get(i)));
    //     }
    // }
    

    public void insertFromFile() throws IOException {
       
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\org\\openjfx\\MainApp\\dictionaries.txt"));

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


    public void dictionaryExportToFile() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src\\main\\java\\org\\openjfx\\MainApp\\dictionaries.txt");
            for (int i = 0; i < super.getSize(); i++) {
                fileWriter.write(super.getWord(i).getWordTarget() + "\t" + super.getWord(i).getWordExplain() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public void dictionaryLookup() throws IOException {
        System.out.println("Nhap tu can tra: ");
        // Scanner sc = new Scanner(System.in);
        // String word_target = sc.nextLine();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word_target = br.readLine();

        word_target = word_target.toLowerCase();
        int index = super.binarySearch(word_target);

        if (index == -1) {
            System.out.println("Khong tim thay tu can tra!");

            return;
        } else {
            System.out.println("Nghia cua tu: " + "\n" + super.getWord(index).getWordExplain());
        }
    }
}

    // public Word dictionaryLookup(String word_target) {
    //     word_target = word_target.toLowerCase();
    //     int index = super.binarySearch(word_target);

    //     if (index == -1) {
    //         return null;
    //     } else {
    //         return super.getWord(index);
    //     }
    // }

    
    


