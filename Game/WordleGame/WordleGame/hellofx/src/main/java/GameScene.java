import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScene implements Initializable {
    @FXML
    private TextField guessInput;
    @FXML
    private Label box00;
    @FXML
    private Label box01;
    @FXML
    private Label box02;
    @FXML
    private Label box03;
    @FXML
    private Label box04;
    @FXML
    private Label box05;
    @FXML
    private Label box06;
    @FXML
    private Label box07;
    @FXML
    private Label box08;
    @FXML
    private Label box09;
    @FXML
    private Label box10;
    @FXML
    private Label box11;
    @FXML
    private Label box12;
    @FXML
    private Label box13;
    @FXML
    private Label box14;
    @FXML
    private Label box15;
    @FXML
    private Label box16;
    @FXML
    private Label box17;
    @FXML
    private Label box18;
    @FXML
    private Label box19;
    @FXML
    private Label box20;
    @FXML
    private Label box21;
    @FXML
    private Label box22;
    @FXML
    private Label box23;
    @FXML
    private Label box24;
    @FXML
    private Label guessWord;
    @FXML
    private Button checkButton;
    @FXML
    private StackPane rootPane;

    public int countCorrect = 0;
    private static int rows = 0;
    private Label[] boxes1;
    
    private String word;
    private String[] words1 = {
            "apple", "bunny", "cloud", "daisy", "eagle", "fairy", "grape", "honey", "ivory", "jolly",
    };

    
    public void randomizeWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words1.length);
        word = words1[randomIndex];
    }

    public String getWord() {
        return word;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootPane.setOpacity(0);
        makeClearTransition();         
        boxes1 = new Label[] { box00, box01, box02, box03, box04, 
                            box05, box06, box07, box08, box09, 
                            box10, box11, box12, box13, box14, 
                            box15, box16, box17, box18, box19, 
                            box20, box21, box22, box23, box24};

        randomizeWord();
        if (countCorrect % 5 != 0) {
            randomizeWord();
        }
    }

    @FXML
    protected void checkGuess() {
        String ans = getWord();
        String guess = guessInput.getText();
        if (guess.length() != 5) {
            guessWord.setText("Please enter another guess");
        } else {
            guessWord.setText(guess);

            Timeline timeline = new Timeline();
            for (int i = rows * 5; i < rows * guess.length() + 5; i++) {
                String letter = guess.substring(i - rows * 5, i - rows * 5 + 1);
                boxes1[i].setText("");
                int finalI = i;
                int finalII = i - rows * 5;
                KeyFrame keyFrame = new KeyFrame(
                        javafx.util.Duration.millis(TimeUnit.SECONDS.toMillis(i - rows * 5 + 1) / 5),
                        new javafx.event.EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                boxes1[finalI].setText(boxes1[finalI].getText() + letter);
                                int countWord = countLeft(ans, letter.charAt(0));
                                if (letter.equals(ans.substring(finalII, finalII + 1))) {
                                    boxes1[finalI].getStyleClass().add("true");
                                    countWord--;
                                    countCorrect++;
                                } else if (ans.contains(letter) && countWord != 0) {
                                    boxes1[finalI].getStyleClass().add("having");
                                } else {
                                    boxes1[finalI].getStyleClass().add("false");
                                }
                            }
                        });
                timeline.getKeyFrames().add(keyFrame);
            }
            rows++;
            timeline.play();
        }
    }

    public int countLeft(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }



        @FXML
    private void backToStartScene(ActionEvent event) throws IOException {
        makeFadeOut();
    }

    public void backToScene1(){
        try {
            Parent secondView;
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
            String css = this.getClass().getResource("/css/style.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(GameScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.millis(TimeUnit.SECONDS.toMillis(1 )));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            backToScene1();
        });
        fadeTransition.play();
    }
    private void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.millis(TimeUnit.SECONDS.toMillis(1)));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}