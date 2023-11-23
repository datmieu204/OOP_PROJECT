package MainDictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DictionaryFile {
    private ArrayList<Word> list_word = new ArrayList<>();

    public ArrayList<Word> read(String path) {
        try {
            FileInputStream file = new FileInputStream(path);

            InputStreamReader inputStream = new InputStreamReader(file, "UTF-8");

            BufferedReader br = new BufferedReader(inputStream);

            String line ;
            String s1 = "";
            String s2 = "";
            int count = 0;

            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()) {
                    if(line.charAt(0) == '@') {
                        
                        if(!s1.isEmpty() && !s2.isEmpty()){
                            s1 = s1.trim();
                            s2 = s2.trim();
                            Word word = new Word(s1, s2);
                            this.list_word.add(word);
                        }
    
                        s1 = "";
                        s2 = "";
                        count = line.length();

                        for(int i = 1; i < count; i++) {
                            if(line.charAt(i) != '/') {
                                s1 += line.charAt(i);
                            } else {
                                count = i;
                                break;
                            }
                        } 
                        s2 = s2 + line.substring(count);
                        s2 += "\n";
             
                    } else {
                        s2 = s2 + line + "\n";
                    }
                }
            }

            if (!s1.isEmpty() && !s2.isEmpty()) {
                s1 = s1.trim();
                s2 = s2.trim();
                Word word = new Word(s1, s2);
                this.list_word.add(word);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.list_word;
    }

    public void write(String path, ArrayList<Word> update) {
        try {
            File pathOutput = new File(path);

            FileOutputStream file = new FileOutputStream(pathOutput);

            OutputStreamWriter outputStream = new OutputStreamWriter(file, "UTF-8");

            BufferedWriter bw = new BufferedWriter(outputStream);

            for (int i = 0; i < update.size(); i++) {
                bw.write("@" + update.get(i).getWordTarget() + "\n");
                bw.write( update.get(i).getWordExplain());
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("Can't write file!");
        }
    }
}
