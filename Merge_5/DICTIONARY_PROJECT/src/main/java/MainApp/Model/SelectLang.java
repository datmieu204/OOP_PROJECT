package MainApp.Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SelectLang {

    public SelectLang() {

    }

    private LanguageCode LanguageCodeAPI = new LanguageCode();
    private LanguageName LanguageNameAPI = new LanguageName();
    private SpeakCode SpeakCodeAPI = new SpeakCode();
    private SpeakName SpeakNameAPI = new SpeakName();

    private void getLanguageList() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("src\\main\\resources\\data\\languages.txt");

        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));

        BufferedReader br = new BufferedReader(isr);

        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+", 4);
                if (parts.length == 4) {

                    String language_code = parts[0];
                    String language_name = parts[1];
                    String speak_code = parts[2];
                    String speak_name = parts[3];

                    LanguageCodeAPI.addAPIString(language_code);
                    LanguageNameAPI.addAPIString(language_name);
                    SpeakCodeAPI.addAPIString(speak_code);
                    SpeakNameAPI.addAPIString(speak_name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLanguageName() throws IOException {
        getLanguageList();
        return LanguageNameAPI.getListAPI();
    }

    public ArrayList<String> getLanguageCode() throws IOException {
        getLanguageList();
        return LanguageCodeAPI.getListAPI();
    }

    public ArrayList<String> getSpeakName() throws IOException {
        getLanguageList();
        return SpeakNameAPI.getListAPI();
    }

    public ArrayList<String> getSpeakCode() throws IOException {
        getLanguageList();
        return SpeakCodeAPI.getListAPI();
    }

    public String translator(String langFrom, String langTo, String text) throws IOException {
        String urlApi = "https://script.google.com/macros/s/AKfycbz29fO5jCKw8Y-W0TePZLFM9jyI8urgaON2R9kMtC35l8dIl2aWQ9tqqYFnIkfERCI/exec"
                + "?q=" + URLEncoder.encode(text, "UTF-8")
                + "&target=" + langTo
                + "&source=" + langFrom;
                
        URL url = new URL(urlApi);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        String input;
        StringBuilder response = new StringBuilder();

        while ((input = in.readLine()) != null) {
            response.append(input);
        }
        in.close();
        return response.toString();
    }
}

/* 1 tuy chon se hien ra list ngon ngu */