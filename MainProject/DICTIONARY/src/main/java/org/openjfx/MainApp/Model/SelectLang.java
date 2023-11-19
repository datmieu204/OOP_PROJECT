package org.openjfx.MainApp.Model;

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

    private static void getLanguageList() throws FileNotFoundException {
       
        FileInputStream fis = new FileInputStream("src\\main\\resources\\org\\openjfx\\MainApp\\languages.txt");

        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));

        BufferedReader br = new BufferedReader(isr);

        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+", 2);
                if(parts.length >= 2) {  

                    String language_code = parts[0];
                    String language_name = parts[1];

                    LanguageCode.language_list_code.add(language_code);
                    LanguageName.language_list.add(language_name);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLanguageName() throws IOException {
        getLanguageList();
        return LanguageName.language_list;
    }

    public ArrayList<String> getLanguageCode() throws IOException {
        getLanguageList();
        return LanguageCode.language_list_code;
    }

    public String translator(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbz29fO5jCKw8Y-W0TePZLFM9jyI8urgaON2R9kMtC35l8dIl2aWQ9tqqYFnIkfERCI/exec"
                +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    // public static void main(String[] args) throws IOException {
    //     SelectLang selectLang = new SelectLang();
    //     ArrayList<String> languages = selectLang.getLanguageList();
    //     for(String s : languages) {
    //         System.out.println(s);
    //     }

    //     for(String s : selectLang.getLanguage()) {
    //         System.out.println(s);
    //     }
    // }

}


/* 1 tuy chon se hien ra list ngon ngu */