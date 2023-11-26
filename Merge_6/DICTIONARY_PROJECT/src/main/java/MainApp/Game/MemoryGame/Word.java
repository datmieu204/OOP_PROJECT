package MainApp.Game.MemoryGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Word {
    public static String topic;
    public static ArrayList<String> engwords = new ArrayList<>();
    public static ArrayList<String> vietwords = new ArrayList<>();

    public static void setTopic(String x) {
        topic = x;
    }

    public static String getTopic() {
        return topic;
    }

    public static void generateWord() {
        if (engwords != null && vietwords != null) {
            engwords.clear();
            vietwords.clear();
        }

        String filePath = "D:/DICTIONARY_PROJECT/src/main/resources/topics/MemoryGame/Animal.txt";
        if (topic != null && topic.equals("Animal")) {
            filePath = "D:/DICTIONARY_PROJECT/src/main/resources/topics/MemoryGame/Animal.txt";
        } else if (topic != null && topic.equals("Body")) {
            filePath = "D:/DICTIONARY_PROJECT/src/main/resources/topics/MemoryGame/Body.txt";
        } else if (topic != null && topic.equals("Family")) {
            filePath = "D:/DICTIONARY_PROJECT/src/main/resources/topics/MemoryGame/Family.txt";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            HashMap<String, String> wordMap = new HashMap<>();

            // Đọc từng dòng trong file và tạo ánh xạ từ tiếng Anh sang tiếng Việt
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String engWord = parts[0].trim();
                    String vietWord = parts[1].trim();
                    wordMap.put(engWord, vietWord);
                }
            }
            // Xử lý từng ánh xạ và đưa từ vào danh sách tương ứng
            for (String engWord : wordMap.keySet()) {
                engwords.add(engWord);
                vietwords.add(wordMap.get(engWord));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
