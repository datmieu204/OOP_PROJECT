package MainApp.Game.MemoryGame;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import MainApp.Game.Game;

public class GameController5x5 extends Game implements Initializable {
    static ChooseTopic chooseTopicScene;

    public static void setChooseTopicScene(ChooseTopic ct) {
        GameController5x5.chooseTopicScene = ct;
    }

    // Biến thời gian
    public int timeCount = 0;
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
    private Button switchButton;
    @FXML
    private Button soundButton;

    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private Button instructionButton;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75), e -> hideButtons()));
    private Timeline gameTimer;

    private boolean firstButtonClicked = false;
    private boolean soundOn = true;

    private int firstButtonIndex;
    private int secondButtonIndex;
    private boolean match;

    public void startGame() {
        rootPane.setOpacity(0);
        makeClearTransition();
        time.setText("Time: " + String.valueOf(timeCount));

        // Set time
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeCount++;
            time.setText("Time: " + String.valueOf(timeCount));
            if (timeCount == GameData.TIMELIMIT) {
                gameTimer.stop();
                try {
                    showResult();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }));
        gameTimer.setCycleCount(Animation.INDEFINITE);
        gameTimer.play();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, button10, button11, button12, button13, button14, button15,
                button16, button17, button18, button19, button20, button21, button22, button23, button24));
        memoryGame.setupGame();
        restartGame();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        ChooseTopic.setGame5x5(this);

        GameData.gameMatrix = "5x5";

        soundButton.setGraphic(soundOffImage);
        instructionButton.setGraphic(instructionImage);
        soundOn = false;
    }

    @FXML
    public void restartGame() {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        for (Button button : buttons) {
            button.getStyleClass().remove("opened");
            button.getStyleClass().add("gameButton");
        }

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
        GameData.turns5x5 = 0;
        GameData.points5x5 = 0;
        timeCount = 0;
        memoryGame.reset();
        time.setText("Time: " + timeCount);
        turn.setText("Turns = " + GameData.turns5x5);
        point.setText("Points = " + GameData.points5x5);
    }

    public void nextRound() {
        for (Button button : buttons) {
            button.getStyleClass().remove("opened");
            button.getStyleClass().add("gameButton");
        }

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
        time.setText("Time: " + timeCount);
        turn.setText("Turns = " + GameData.turns5x5);
        point.setText("Points = " + GameData.points5x5);
    }

    @FXML
    void buttonClicked(ActionEvent event) {
        gameSelectSound.setVolume(0.7);
        gameSelectSound.seek(Duration.ZERO);
        gameSelectSound.play();

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
                timeline.stop();
            }
            match = false;
            firstButtonClicked = true;
            // Get clicked button memory letter
            // Get clicked button memory letter
            String buttonId = ((Control) event.getSource()).getId();
            String numberStr = buttonId.replaceAll("\\D+", "");
            firstButtonIndex = Integer.parseInt(numberStr);

            if ((memoryGame.checkClicked.get(firstButtonIndex) == true)) {
                return;
            }

            // Change clicked button text
            buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

            return;
        }

        // Get clicked button memory letter
        String buttonId = ((Control) event.getSource()).getId();
        String numberStr = buttonId.replaceAll("\\D+", "");
        secondButtonIndex = Integer.parseInt(numberStr);

        // Nếu mà nhấn ô 2 trùng ô 1 thì return
        if ((secondButtonIndex == firstButtonIndex)) {
            firstButtonClicked = true;
            return;
        }
        // Change clicked button text
        buttons.get(secondButtonIndex).setText(memoryGame.getOptionAtIndex(secondButtonIndex));

        firstButtonClicked = false;

        GameData.turns5x5++;
        turn.setText("Turns = " + GameData.turns5x5);
        // Check if the two clicked button match
        if (memoryGame.checkTwoPositions(firstButtonIndex, secondButtonIndex)) {
            memoryGame.checkClicked.set(secondButtonIndex, true);
            memoryGame.checkClicked.set(firstButtonIndex, true);

            buttons.get(firstButtonIndex).getStyleClass().add("opened");
            buttons.get(secondButtonIndex).getStyleClass().add("opened");

            correctSound.setVolume(1.0);
            correctSound.seek(Duration.ZERO);
            correctSound.play();

            System.out.println("Match");
            match = true;
            GameData.points5x5++;
            point.setText("Points = " + GameData.points5x5);

            if (GameData.points5x5 % 12 == 0 && (memoryGame.countClicked(memoryGame.checkClicked) == 24)) {
                nextRound();
            }
            return;

        } else {
            wrongSound.setVolume(1.0);
            wrongSound.seek(Duration.ZERO);
            wrongSound.play();
            memoryGame.checkClicked.set(firstButtonIndex, false);
            memoryGame.checkClicked.set(secondButtonIndex, false);
        }
        timeline.play();
    }

    private void hideButtons() {
        buttons.get(firstButtonIndex).setText("");
        buttons.get(secondButtonIndex).setText("");
    }

    @FXML
    public void onOffSound(ActionEvent event) {
        if (soundOn == true) {
            soundOn = false;
            soundButton.setGraphic(soundOffImage);
            backgroundSound.pause();
        } else {
            soundOn = true;
            soundButton.setGraphic(soundOnImage);
            backgroundSound.play();
        }
    }

    @FXML
    private void backToStartScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        backgroundSound.stop();
        soundOn = false;
        soundButton.setGraphic(soundOffImage);
        chooseTopicScene.hide5x5Pane();
        gameTimer.stop();

    }

    public void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    private void nextToInstruction(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/Instruction.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);

        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/MemoryGame/Instruction.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showResult() throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        gameTimer.stop();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/Result.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);

        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/MemoryGame/Result.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}