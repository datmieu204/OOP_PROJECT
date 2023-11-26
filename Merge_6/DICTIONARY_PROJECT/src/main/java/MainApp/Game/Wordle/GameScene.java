package MainApp.Game.Wordle;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import MainApp.Game.Game;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GameScene extends Game implements Initializable {
    static WordleStartScene wordleStartScene;

    public static void setWordleStartScene(WordleStartScene wordleStartScene) {
        GameScene.wordleStartScene = wordleStartScene;
    }

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
    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;
    @FXML
    private Button instructionButton;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane stackPane;
    public static boolean correctAnswer = false;
    // BIẾN THỜI GIAN
    public static int timeCount = 0;

    private Timeline gameTimer;
    public static String answer = "";
    private static int rows = 0;
    public static int turns = 0;
    public static int points = 0;
    private Label[] boxes1;
    private String wordTarget;

    // private ArrayList<String> words1 = Word.getArray();
    private ArrayList<String> words1 = new ArrayList<>();

    private boolean[] checkCorrect = { false, false, false, false, false };

    private boolean soundOn = true;

    String almostCorrect_sound = getClass().getResource("/sound/almostCorrect.mp3").toExternalForm();
    String flip_sound = getClass().getResource("/sound/flip.mp3").toExternalForm();
    String check_sound = getClass().getResource("/sound/check.mp3").toExternalForm();

    Media almostCorrect_media = new Media(almostCorrect_sound);
    Media flip_media = new Media(flip_sound);
    Media check_media = new Media(check_sound);

    MediaPlayer almostCorrectSound = new MediaPlayer(almostCorrect_media);
    MediaPlayer flipSound = new MediaPlayer(flip_media);
    MediaPlayer checkSound = new MediaPlayer(check_media);

    public void randomizeWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words1.size());
        wordTarget = words1.get(randomIndex);
        // UPPERCASE
        wordTarget = wordTarget.toUpperCase();
    }

    public String getWord() {
        return wordTarget;
    }

    public void startGame() {
        noticeLabel.setText("");
        randomizeWord();

        for (Label label : boxes1) {
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }
        correctAnswer = false;
        rows = 0;
        turns = 0;
        points = 0;
        timeCount = 0;
        turn.setText("Turns: " + turns);
        point.setText("Points: " + points);
        time.setText("Time: " + timeCount);
        noticeLabel.setText("");
        guessWord.setText("");
        guessInput.setText("");

        for (int i = 0; i < checkCorrect.length; i++) {
            checkCorrect[i] = false;
        }
        randomizeWord();

        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeCount++;
            time.setText("Time: " + String.valueOf(timeCount));
        }));
        gameTimer.setCycleCount(Animation.INDEFINITE);
        gameTimer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WordleStartScene.setGameScene(this);
        setup();
        try {
            Word.importFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        words1 = Word.getArray();
        rootPane.setOpacity(0);
        boxes1 = new Label[] { box00, box01, box02, box03, box04,
                box05, box06, box07, box08, box09,
                box10, box11, box12, box13, box14,
                box15, box16, box17, box18, box19,
                box20, box21, box22, box23, box24 };

        for (Label label : boxes1) {
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }

        almostCorrectSound.play();
        almostCorrectSound.stop();
        almostCorrectSound.seek(Duration.ZERO);
        flipSound.play();
        flipSound.stop();
        flipSound.seek(Duration.ZERO);

        soundOn = false;
        instructionButton.setGraphic(instructionImage);
        soundButton.setGraphic(soundOffImage);
    }

    @FXML
    protected void checkGuess() throws InterruptedException {
        if (correctAnswer == true) {
            guessWord.setText("You're correct, next round");
        } else if (correctAnswer == false) {
            checkSound.setVolume(0.7);
            checkSound.seek(Duration.ZERO);
            checkSound.play();

            String ans = getWord();
            answer = ans;
            Map<String, Integer> mapAnswer = new HashMap<String, Integer>();
            String guess = guessInput.getText();
            guess = guess.toUpperCase();

            for (int i = 0; i < 5; i++) {
                String chac = String.valueOf(ans.charAt(i));
                if (!mapAnswer.containsKey(chac)) {
                    mapAnswer.put(chac, 0);
                }
                mapAnswer.put(chac, mapAnswer.get(chac) + 1);
            }

            if (guess.length() != 5) {
                guessWord.setText("Please enter another guess");
            } else if (turns == 5) {
                noticeLabel.setText("You have no tries");
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
                                    // APPLE
                                    // APLPP
                                    if (ans.contains(letter) && mapAnswer.get(letter) > 0) {
                                        mapAnswer.put(letter, mapAnswer.get(letter) - 1);

                                        if (letter.equals(ans.substring(finalII, finalII + 1))) {
                                            boxes1[finalI].getStyleClass().add("true");
                                        } else {
                                            boxes1[finalI].getStyleClass().add("having");
                                        }
                                    }

                            else {
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

                if (countMatchedLetter == 5) {
                    correctSound.setVolume(1.0);
                    correctSound.seek(Duration.ZERO);
                    correctSound.play();
                    points++;
                    correctAnswer = true;
                } else if (letterIndex(ans, guess)) {
                    almostCorrectSound.setVolume(1.0);
                    almostCorrectSound.seek(Duration.ZERO);
                    almostCorrectSound.play();
                } else if (countMatchedLetter == 0) {
                    wrongSound.setVolume(1.0);
                    wrongSound.seek(Duration.ZERO);
                    wrongSound.play();
                }
                guessInput.setText("");
                point.setText("Points: " + points);
                rows++;
                turns++;

                if (turns == 5) {
                    try {
                        showResult();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                turn.setText("Turns: " + turns);
                timeline.play();
                gameTimer.play();
            }

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

    // Back lại StartScene
    @FXML
    private void backToStartScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        // wordleStartScene.hideWordleGame();
        gameTimer.stop();
        backgroundSound.stop();
        soundOn = false;
        soundButton.setGraphic(soundOffImage);
        wordleStartScene.hideWordleGame();
    }

    public void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.millis(TimeUnit.SECONDS.toMillis(1)));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    public void restartGame() {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        for (Label label : boxes1) {
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }
        correctAnswer = false;
        rows = 0;
        turns = 0;
        points = 0;
        timeCount = 0;
        turn.setText("Turns: " + turns);
        point.setText("Points: " + points);
        time.setText("Time: " + timeCount);
        noticeLabel.setText("");
        guessWord.setText("");
        guessInput.setText("");

        for (int i = 0; i < checkCorrect.length; i++) {
            checkCorrect[i] = false;
        }
        randomizeWord();
        gameTimer.play();
    }

    @FXML
    public void nextRound() throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        correctAnswer = false;
        rows = 0;
        turns = 0;
        randomizeWord();
        for (Label label : boxes1) {
            label.setText("");
            label.getStyleClass().clear();
            label.getStyleClass().add("gameLabel");
        }

        time.setText("Time: " + timeCount);
        turn.setText("Turns: " + turns);
        point.setText("Points: " + points);

        for (int i = 0; i < checkCorrect.length; i++) {
            checkCorrect[i] = false;
        }

        noticeLabel.setText("");
        guessWord.setText("");
        guessInput.setText("");
    }

    @FXML
    private void nextToInstruction(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Wordle/Instruction.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);

        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/Wordle/Instruction.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showResult() throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Wordle/Result.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/Wordle/Result.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        gameTimer.stop();

    }
}