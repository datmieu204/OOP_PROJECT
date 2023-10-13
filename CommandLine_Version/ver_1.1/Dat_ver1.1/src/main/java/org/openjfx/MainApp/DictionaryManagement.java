package org.openjfx.MainApp;

import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    public DictionaryManagement() {
    }

    public DictionaryManagement(Word[] words, int size_of_dic) {
        super(words, size_of_dic);
    }

    public void insertFromcommandline() {
        System.out.println("Nhap so luong tu: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < number; i++) {
            System.out.println("Nhap tu tieng Anh: ");
            String word_target = sc.nextLine();

            System.out.println("Nhap nghia tieng Viet: ");
            String word_explain = sc.nextLine();

            Word word = new Word(word_target, word_explain);
            super.addWord(word);
        }

        super.sortAlphabet();

    }
}
