package MainApp.Game.MemoryGame;

import java.util.*;

public class MemoryGame {

    private static int boardLength = 3;

    private int boardSize = boardLength * boardLength;

    private final Random random = new Random();
    Word word = new Word();

    ArrayList<String> englishWords = new ArrayList<>();
    ArrayList<String> vietnameseWords = new ArrayList<>();

    private ArrayList<String> memoryBoard = new ArrayList<>();
    private ArrayList<String> memoryOptions = new ArrayList<>();

    public ArrayList<Boolean> checkClicked = new ArrayList<>();

    public static void setBoardLength(int x) {
        boardLength = x;
    }

    public static int getBoardLength() {
        return boardLength;
    }

    public int findPositionInEngWords(String word) {
        int pos = englishWords.indexOf(word);
        return pos;
    }

    public int findPositionInVietWords(String word) {
        int pos = vietnameseWords.indexOf(word);
        return pos;
    }

    public int countClicked(ArrayList<Boolean> checkClicked) {
        int count = 0;
        for (boolean clicked : checkClicked) {
            if (clicked == true) {
                count++;
            }
        }
        return count;
    }

    public void generateWordData() {
        if (englishWords != null && vietnameseWords != null) {
            englishWords.clear();
            vietnameseWords.clear();
        }
        Word.generateWord();

        englishWords.addAll(Word.engwords);
        vietnameseWords.addAll(Word.vietwords);
    }

    public void setupGame() {
        // generateWordData();
        setupMemoryBoard();
    }

    public String getOptionAtIndex(int index) {
        return memoryBoard.get(index);
    }

    public String getKeyAtIndex(int index) {
        return memoryOptions.get(index);
    }

    public void generateMemorySource() {
        generateWordData();

        memoryBoard = new ArrayList<>(Collections.nCopies(boardSize, ""));
        checkClicked = new ArrayList<>(Collections.nCopies(boardSize, false));

        Random random = new Random();

        if (!memoryOptions.isEmpty()) {
            memoryOptions.clear();
        }
        if (boardSize % 2 == 1) {
            for (int i = 0; i <= boardSize / 2; i++) {
                int randomIndex = random.nextInt(englishWords.size());
                memoryOptions.add(englishWords.get(randomIndex));
                if (memoryOptions.size() != boardSize) {
                    memoryOptions.add(vietnameseWords.get(randomIndex));
                } else {
                    break;
                }
            }
        } else {
            for (int i = 0; i < boardSize / 2; i++) {
                int randomIndex = random.nextInt(englishWords.size());
                memoryOptions.add(englishWords.get(randomIndex));
                memoryOptions.add(vietnameseWords.get(randomIndex));
            }
        }
    }

    public void setupMemoryBoard() {
        boardSize = boardLength * boardLength;
        generateMemorySource();
        System.out.println("setupMemoryBoard length = " + boardLength);
        System.out.println("setupMemoryBoard size = " + boardSize);
        List<Integer> posAppear = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            String memoryOption = memoryOptions.get(i);
            int position = random.nextInt(boardSize);

            while ((!Objects.equals(memoryBoard.get(position), ""))
                    && posAppear.contains(position)) {
                position = random.nextInt(boardSize);
                // System.out.println("position " + position);
            }
            memoryBoard.set(position, memoryOption);
            System.out.println("position " + position);
            System.out.println("memoryOption = " + memoryOption + "\n");
            posAppear.add(position);
        }
    }

    public boolean checkTwoPositions(int firstIndex, int secondIndex) {
        String first = memoryBoard.get(firstIndex);
        String second = memoryBoard.get(secondIndex);
        boolean check = false;
        // Nếu first trong englishWords
        if (englishWords.indexOf(first) != -1) {
            int pos1 = findPositionInEngWords(first);
            int pos2 = findPositionInVietWords(second);
            if (pos1 == pos2) {
                check = true;
            }
        }

        // Nếu second trong englishWords
        else {
            int pos1 = findPositionInEngWords(second);
            int pos2 = findPositionInVietWords(first);
            if (pos1 == pos2) {
                check = true;
            }
        }
        return check;
    }

    public void reset() {
        memoryBoard.clear();
        memoryOptions.clear();
        checkClicked.clear();
        // Thực hiện các bước khởi tạo lại trò chơi
        setupGame();
    }

}