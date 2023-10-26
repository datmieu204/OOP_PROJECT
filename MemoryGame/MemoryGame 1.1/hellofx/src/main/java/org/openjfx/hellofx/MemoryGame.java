package org.openjfx.hellofx;

import java.util.*;

public class MemoryGame {

    private final int boardLength = 3;
    private final int boardSize = boardLength * boardLength;
    private final Random random = new Random();

    private final ArrayList<String> memoryBoard = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", ""));
    private final ArrayList<String> memoryOptions = new ArrayList<>(Arrays.asList("a", "a", "b", "b", "c", "c", "d", "d", "e"));

    public void setupGame(){
        setupMemoryBoard();
        System.out.println(memoryBoard);
    }

    public String getOptionAtIndex(int index){
        return memoryBoard.get(index);
    }

    public void setupMemoryBoard(){
        for (int i = 0; i < boardSize; i++) {
            String memoryOption = memoryOptions.get(i);
            int position = random.nextInt(boardSize);

            while (!Objects.equals(memoryBoard.get(position), "")){
                position = random.nextInt(boardSize);
            }
            memoryBoard.set(position, memoryOption);
        }
    }

    public boolean checkTwoPositions(int firstIndex, int secondIndex){
        if(memoryBoard.get(firstIndex).equals(memoryBoard.get(secondIndex))){
            return true;
        }
        return false;
    }

    public void reset() {
        memoryBoard.clear();
        memoryOptions.clear();
        memoryBoard.addAll(Arrays.asList("", "", "", "", "", "", "", "", ""));
        memoryOptions.addAll(Arrays.asList("a", "a", "b", "b", "c", "c", "d", "d", "e"));
        // Thực hiện các bước khởi tạo lại trò chơi
        setupGame();
    }

}