import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    @FXML
    private Button soundButton;
    @FXML
    private Label noticeLabel;
    @FXML
    private Button restart;
    private static int rows = 0;
    private static int turns = 0;
    private Label[] boxes1;
    
    private String word;

    private String[] words1 = {
            "apple", "bunny", "cloud", "daisy", "eagle", "fairy", "grape", "honey", "ivory", "jolly",
    };

    private boolean soundOn = true;
    private ImageView soundOnImage;
    private ImageView soundOffImage;

    Image soundOnImg = new Image("/image/soundOn.png");
    Image soundOffImg = new Image("/image/soundOff.png");
    
    String correct_sound = getClass().getResource("/sound/correct.mp3").toExternalForm();
    String almostCorrect_sound = getClass().getResource("/sound/almostCorrect.mp3").toExternalForm();
    String wrong_sound = getClass().getResource("/sound/wrong.mp3").toExternalForm();
    String background_sound = getClass().getResource("/sound/background.mp3").toExternalForm();
    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    String flip_sound = getClass().getResource("/sound/flip.mp3").toExternalForm();

    Media correct_media = new Media(correct_sound);
    Media almostCorrect_media = new Media(almostCorrect_sound);
    Media wrong_media = new Media(wrong_sound);
    Media background_media = new Media(background_sound);
    Media option_media = new Media(option_sound);
    Media flip_media = new Media(flip_sound);

    MediaPlayer correctSound = new MediaPlayer(correct_media);
    MediaPlayer almostCorrectSound = new MediaPlayer(almostCorrect_media);
    MediaPlayer wrongSound = new MediaPlayer(wrong_media);
    MediaPlayer backgroundSound = new MediaPlayer(background_media);
    MediaPlayer optionSound = new MediaPlayer(option_media);
    MediaPlayer flipSound = new MediaPlayer(flip_media);

    public void randomizeWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words1.length);
        word = words1[randomIndex];

        //UPPERCASE
        word = word.toUpperCase();
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

        for(Label label : boxes1){
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }
        noticeLabel.setText("");
        rows = 0;
        turns = 0;
        randomizeWord();

        soundOnImage = new ImageView(soundOnImg);
        soundOffImage = new ImageView(soundOffImg);
        soundButton.setGraphic(soundOffImage);


        correctSound.play();
        correctSound.stop();
        correctSound.seek(Duration.ZERO);

        almostCorrectSound.play();
        almostCorrectSound.stop();
        almostCorrectSound.seek(Duration.ZERO);

        wrongSound.play();
        wrongSound.stop();
        wrongSound.seek(Duration.ZERO);

        optionSound.play();
        optionSound.stop();
        optionSound.seek(Duration.ZERO);

        flipSound.play();
        flipSound.stop();
        flipSound.seek(Duration.ZERO);

        backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundSound.setVolume(0.5);
        backgroundSound.play();
        backgroundSound.stop();
        soundOn = false;    
    }

    @FXML
    protected void checkGuess() {

        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  

        String ans = getWord();
        String guess = guessInput.getText();
        guess = guess.toUpperCase();
        if (guess.length() != 5) {
            guessWord.setText("Please enter another guess");
        }
        else if(turns == 5){
            noticeLabel.setText("You have no tries, please restart");
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
                                } else if (ans.contains(letter) && countWord != 0) {
                                    boxes1[finalI].getStyleClass().add("having");
                                } else {
                                    boxes1[finalI].getStyleClass().add("false");
                                }
                            }
                        });
                timeline.getKeyFrames().add(keyFrame);
            }

            int countMatchedLetter = 0;
            for (int i = 0; i < 5; i++) {
                if (guess.charAt(i) == ans.charAt(i)) {
                    countMatchedLetter++;
                }
            }

            if(countMatchedLetter == 5){
                correctSound.setVolume(1.0);
                correctSound.seek(Duration.ZERO);
                correctSound.play();    
            }
            else if (letterIndex(ans, guess)){
                almostCorrectSound.setVolume(1.0);
                almostCorrectSound.seek(Duration.ZERO);
                almostCorrectSound.play();   
            } 
            else if (countMatchedLetter == 0){
                wrongSound.setVolume(1.0);
                wrongSound.seek(Duration.ZERO);
                wrongSound.play();
            }
            rows++;
            turns++;
            timeline.play();
        }
    }

    public boolean letterIndex(String ans, String guess) {
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            if (ans.contains(String.valueOf(letter))) {
                return true;
            }
        }
        return false;
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

    public void onOffSound(ActionEvent event){
        if(soundOn == true){
            soundOn = false;
            soundButton.setGraphic(soundOffImage);
            backgroundSound.pause();
        }
        else{
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
        makeFadeOut();
    }

    public void backToScene1(){
        backgroundSound.stop();
        soundOn = false; 
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

    @FXML
    public void restartGame(){
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  
        for(Label label : boxes1){
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }
        noticeLabel.setText("");
        rows = 0;
        turns = 0;
        randomizeWord();

    }

    public void nextRound(){
        rows = 0;
        turns = 0;
        randomizeWord();
        for(Label label : boxes1){
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }
        noticeLabel.setText("");
    }
}