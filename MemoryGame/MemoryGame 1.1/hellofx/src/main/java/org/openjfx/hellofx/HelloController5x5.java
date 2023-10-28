package org.openjfx.hellofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController5x5 implements Initializable {
    // Biến thời gian
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
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    
    @FXML
    private Button restart;

    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;

    @FXML
    private Button switchButton;

    Timeline timelineHide = new Timeline(new KeyFrame(Duration.seconds(0.75), e -> hideButtons()));

    private boolean firstButtonClicked = false;

    private int firstButtonIndex;
    private int secondButtonIndex;
    private boolean match;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public int turns = 0;
    public int points = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText("Time: " + String.valueOf(i));

        // Set time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            i++;
            time.setText("Time: " + String.valueOf(i));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, button10, button11, button12, button13, button14, button15,
                button16, button17, button18, button19, button20, button21, button22, button23, button24));
        memoryGame.setupGame();

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
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button14.setText("");
        button15.setText("");
        button16.setText("");
        button17.setText("");
        button18.setText("");
        button19.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button24.setText("");
        match = false;
        turns = 0;
        points = 0;
        i = 0;
        memoryGame.reset();
        time.setText("Time: " + i);
        turn.setText("Turns = " + turns);
        point.setText("Points = " + points);
    }

    public void nextRound() {
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button14.setText("");
        button15.setText("");
        button16.setText("");
        button17.setText("");
        button18.setText("");
        button19.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button24.setText("");
        match = false;
        memoryGame.reset();
        time.setText("Time: " + i);
        turn.setText("Turns = " + turns);
        point.setText("Points = " + points);
    }

    @FXML
    void buttonClicked(ActionEvent event) {
            String buttonId1 = ((Control) event.getSource()).getId();
            String numberStr1 = buttonId1.replaceAll("\\D+", "");
            int check = Integer.parseInt(numberStr1);

            if ((memoryGame.checkClicked.get(check) == true)) {
                return;
            }

        if (!firstButtonClicked) {
            // If next turn is started before old buttons are hidden
            if (!match) {
                hideButtons();
                timelineHide.stop();
            }
            match = false;
            firstButtonClicked = true;
            // Get clicked button memory letter
            String buttonId = ((Control) event.getSource()).getId();
            String numberStr = buttonId.replaceAll("\\D+", "");
            firstButtonIndex = Integer.parseInt(numberStr);
        
            System.out.println("firstButtonIndex: " + firstButtonIndex);


            // Change clicked button text
            buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

            return;
        }
            if ((memoryGame.checkClicked.get(firstButtonIndex) == true)) {
                return;
            }
        // Get clicked button memory letter
        // String buttonId = ((Control) event.getSource()).getId();
        // secondButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));
        
        String buttonId = ((Control) event.getSource()).getId();
        String numberStr = buttonId.replaceAll("\\D+", "");
        secondButtonIndex = Integer.parseInt(numberStr);
        
            System.out.println("secondButtonIndex: " + secondButtonIndex);

        // Nếu mà nhấn ô 2 trùng ô 1 thì return
        if ((secondButtonIndex == firstButtonIndex)) {
            firstButtonClicked = true;
            return;
        }
        // Change clicked button text
        buttons.get(secondButtonIndex).setText(memoryGame.getOptionAtIndex(secondButtonIndex));

        firstButtonClicked = false;

        turns++;
        turn.setText("Turns = " + turns);
        // Check if the two clicked button match
        if (memoryGame.checkTwoPositions(firstButtonIndex, secondButtonIndex)) {
            memoryGame.checkClicked.set(secondButtonIndex, true);
            memoryGame.checkClicked.set(firstButtonIndex, true);
            System.out.println("Match");
            match = true;
            points++;
            point.setText("Points = " + points);

            if (points % 12 == 0 && (memoryGame.countClicked(memoryGame.checkClicked) == 24)) {
                nextRound();
            }
            return;

        } else {
            memoryGame.checkClicked.set(firstButtonIndex, false);
            memoryGame.checkClicked.set(secondButtonIndex, false);
        }
        timelineHide.play();
    }

    private void hideButtons() {
        buttons.get(firstButtonIndex).setText("");
        buttons.get(secondButtonIndex).setText("");
    }

    public void backToStartScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}