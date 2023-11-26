package MainApp.Game.Wordle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Word {
    private static ArrayList<String> list = new ArrayList<String>();
    public static String topic;

    public static void setArray(ArrayList<String> list) {
        Word.list = list;
    }

    public static ArrayList<String> getArray() {
        return Word.list;
    }

    public static void setTopic(String topic) {
        Word.topic = topic;
    }

    public static void importFile() throws IOException {
        Word.topic = "Animal";
        String filePath = "D:/DICTIONARY_PROJECT/src/main/resources/topics/Wordle/"
                + Word.topic + ".txt";
        System.out.println(Word.topic);
        FileInputStream in = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(in);
        try (BufferedReader bufferReader = new BufferedReader(reader)) {
            String line;

            while ((line = bufferReader.readLine()) != null) {
                list.add(line);
            }
        }
    }
}
