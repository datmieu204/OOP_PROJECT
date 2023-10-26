package org.openjfx.hellofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //Biến thời gian
    int i = 0;
    
    ArrayList<Button> buttons = new ArrayList<>();

    MemoryGame memoryGame = new MemoryGame();

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML 
    private Button button8;

    @FXML
    private Button restart;
    
    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> hideButtons()));

    private boolean firstButtonClicked = false;

    private int firstButtonIndex;
    private int secondButtonIndex;
    private boolean match;

    public int turns = 0;
    public int points = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText("Time: " + String.valueOf(i));

        //Set time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
            i++;
            time.setText("Time: " + String.valueOf(i));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        memoryGame.setupGame();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4,
                button5, button6, button7, button8));
    }

    @FXML
    public void restartGame() {
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        match = false;
        memoryGame.reset();
        turns = 0;
        points = 0;
        i = 0;
        time.setText("Time: " + i);
        turn.setText("Turns = " + turns);
        point.setText("Points = " + points);
    }
    @FXML
    void buttonClicked(ActionEvent event) {
        if(!firstButtonClicked){
            //If next turn is started before old buttons are hidden
            if(!match){
                hideButtons();
                timeline.stop();
            }
            match = false;
            firstButtonClicked = true;

            //Get clicked button memory letter
            String buttonId = ((Control)event.getSource()).getId();
            firstButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));

            //Change clicked button text
            buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

            return;
        }

        //Get clicked button memory letter
        String buttonId = ((Control)event.getSource()).getId();
        secondButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));
        
        if (secondButtonIndex == firstButtonIndex) {
            return;
        }
        //Change clicked button text
        buttons.get(secondButtonIndex).setText(memoryGame.getOptionAtIndex(secondButtonIndex));

        firstButtonClicked = false;
        turns ++;
        turn.setText("Turns = " + turns);
        //Check if the two clicked button match
        if(memoryGame.checkTwoPositions(firstButtonIndex,secondButtonIndex)){
            System.out.println("Match");
            match = true;
            points++;
            point.setText("Points = " + points);
            return;
        }
        timeline.play();

    }

    private void hideButtons(){
        buttons.get(firstButtonIndex).setText("");
        buttons.get(secondButtonIndex).setText("");
    }

}