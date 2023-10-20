// import java.util.ArrayList;
// import java.util.List;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;

// class Word {
//     private String word_target;
//     private String word_explain;

//     public Word(String word_target, String word_explain) {
//         this.word_target = word_target;
//         this.word_explain = word_explain;
//     }

//     public String getWordTarget() {
//         return word_target;
//     }

//     public String getWordExplain() {
//         return word_explain;
//     }
// }

// class Dictionary {
//     private ArrayList<Word> words = new ArrayList<>();

//     public Dictionary(List<Word> list_word) {
//     }

//     public void addWord(Word word) {
//         words.add(word);
//     }

//     public ArrayList<Word> getWords() {
//         return words;
//     }
// }

// class DictionaryManagement {
//     private Dictionary dictionary;

//     public DictionaryManagement(Dictionary dictionary) {
//         this.dictionary = dictionary;
//     }

//     public void insertFromCommandLine() {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         try {
//             System.out.print("Enter the number of words: ");
//             int numWords = Integer.parseInt(br.readLine());

//             for (int i = 0; i < numWords; i++) {
//                 System.out.print("English: ");
//                 String word_target = br.readLine();
//                 System.out.print("Vietnamese: ");
//                 String word_explain = br.readLine();
//                 Word word = new Word(word_target, word_explain);
//                 dictionary.addWord(word);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public void insertFromFile(String fileName) {
//         try {
//             BufferedReader br = new BufferedReader(new FileReader(fileName));
//             String line;
//             while ((line = br.readLine()) != null) {
//                 String[] parts = line.split("\t");
//                 if (parts.length == 2) {
//                     Word word = new Word(parts[0], parts[1]);
//                     dictionary.addWord(word);
//                 }
//             }
//             br.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public Word dictionaryLookup(String word) {
//         for (Word w : dictionary.getWords()) {
//             if (w.getWordTarget().equalsIgnoreCase(word)) {
//                 return w;
//             }
//         }
//         return null;
//     }

//     public void dictionarySearcher(String prefix) {
//         System.out.println("Words starting with '" + prefix + "':");
//         for (Word w : dictionary.getWords()) {
//             if (w.getWordTarget().startsWith(prefix)) {
//                 System.out.println(w.getWordTarget());
//             }
//         }
//     }

//     public void dictionaryExportToFile(String fileName) {
//         try {
//             BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//             for (Word w : dictionary.getWords()) {
//                 bw.write(w.getWordTarget() + "\t" + w.getWordExplain() + "\n");
//             }
//             bw.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// class DictionaryCommandLine {
//     private DictionaryManagement dictionaryManagement;

//     public DictionaryCommandLine(DictionaryManagement dictionaryManagement) {
//         this.dictionaryManagement = dictionaryManagement;
//     }

//     public void showAllWords() {
//         System.out.println("No | English | Vietnamese");
//         ArrayList<Word> words = dictionaryManagement.getDictionary().getWords();
//         for (int i = 0; i < words.size(); i++) {
//             Word word = words.get(i);
//             System.out.println((i + 1) + " | " + word.getWordTarget() + " | " + word.getWordExplain());
//         }
//     }

//     public void dictionaryBasic() {
//         dictionaryManagement.insertFromCommandLine();
//         showAllWords();
//     }

//     public void dictionaryAdvanced() {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         while (true) {
//             System.out.println("Welcome to My Application!");
//             System.out.println("[0] Exit");
//             System.out.println("[1] Add");
//             System.out.println("[2] Remove");
//             System.out.println("[3] Update");
//             System.out.println("[4] Display");
//             System.out.println("[5] Lookup");
//             System.out.println("[6] Search");
//             System.out.println("[7] Game");
//             System.out.println("[8] Import from file");
//             System.out.println("[9] Export to file");
//             System.out.print("Your action: ");
//             try {
//                 int action = Integer.parseInt(br.readLine());
//                 switch (action) {
//                     case 0:
//                         return;
//                     case 1:
//                         dictionaryManagement.insertFromCommandLine();
//                         break;
//                     case 2:
//                         // Implement remove word functionality
//                         break;
//                     case 3:
//                         // Implement update word functionality
//                         break;
//                     case 4:
//                         showAllWords();
//                         break;
//                     case 5:
//                         System.out.print("Enter a word to lookup: ");
//                         String lookupWord = br.readLine();
//                         Word result = dictionaryManagement.dictionaryLookup(lookupWord);
//                         if (result != null) {
//                             System.out.println(result.getWordTarget() + " | " + result.getWordExplain());
//                         } else {
//                             System.out.println("Word not found.");
//                         }
//                         break;
//                     case 6:
//                         System.out.print("Enter a prefix to search: ");
//                         String prefix = br.readLine();
//                         dictionaryManagement.dictionarySearcher(prefix);
//                         break;
//                     case 7:
//                         // Implement the game
//                         break;
//                     case 8:
//                         System.out.print("Enter the file path to import from: ");
//                         String importFilePath = br.readLine();
//                         dictionaryManagement.insertFromFile(importFilePath);
//                         break;
//                     case 9:
//                         System.out.print("Enter the file path to export to: ");
//                         String exportFilePath = br.readLine();
//                         dictionaryManagement.dictionaryExportToFile(exportFilePath);
//                         break;
//                     default:
//                         System.out.println("Action not supported");
//                         break;
//                 }
//             } catch (NumberFormatException | IOException e) {
//                 System.out.println("Invalid input.");
//             }
//         }
//     }

//     public static void main(String[] args) {
//         Dictionary dictionary = new Dictionary();
//         DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);
//         DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine(dictionaryManagement);
//         dictionaryCommandLine.dictionaryAdvanced();
//     }
// }
