package MainApp.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import MainApp.Game.MemoryGame.MemStartScene;
import MainApp.Game.Wordle.WordleStartScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class StartScene implements Initializable {
    static MemStartScene memStartScene;
    static WordleStartScene wordleStartScene;

    public static void setMemStartScene(MemStartScene memStartScene) {
        StartScene.memStartScene = memStartScene;
    }

    public static void setWordleStartScene(WordleStartScene wordleStartScene) {
        StartScene.wordleStartScene = wordleStartScene;
    }

    @FXML
    private Pane rootPane;

    @FXML
    private AnchorPane chooseGamePane;

    @FXML
    private Pane wordlePane;

    @FXML
    private Pane memoryPane;

    @FXML
    private Button wordleButton;

    @FXML
    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    public void hideWordlePane() {
        chooseGamePane.setVisible(true);
        wordlePane.setVisible(false);
        wordlePane.toBack();
        makeClearTransition();
    }

    public void hideMemoryPane() {
        chooseGamePane.setVisible(true);
        memoryPane.setVisible(false);
        memoryPane.toBack();
        makeClearTransition();

    }

    public void showWordlePane() {
        chooseGamePane.setVisible(false);
        wordlePane.setVisible(true);
        wordlePane.toFront();
        wordleStartScene.makeClearTransition();

    }

    public void showMemoryPane() {
        chooseGamePane.setVisible(false);
        memoryPane.setVisible(true);
        memoryPane.toFront();
        memStartScene.makeClearTransition();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.setOpacity(0);
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(
                    WordleStartScene.class.getResource("/fxml/Wordle/WordleStartScene.fxml"));
            String css1 = this.getClass().getResource("/css/Wordle/Start.css").toExternalForm();
            Pane rootWordle = fxmlLoader1.load();
            rootWordle.getStylesheets().add(css1);
            wordlePane.getChildren().add(rootWordle);
            hideWordlePane();

            FXMLLoader fxmlLoader2 = new FXMLLoader(
                    WordleStartScene.class.getResource("/fxml/MemoryGame/MemStartScene.fxml"));
            String css2 = this.getClass().getResource("/css/MemoryGame/Start.css").toExternalForm();

            Pane rootMemory = fxmlLoader2.load();
            rootMemory.getStylesheets().add(css2);

            memoryPane.getChildren().add(rootMemory);
            hideMemoryPane();
            MemStartScene.setStartScene(this);
            WordleStartScene.setStartScene(this);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        optionSound.play();
        optionSound.stop();
        TextOutput textOutput = new TextOutput() {
            @Override
            public void writeText(String textOut) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(textOut);
                    }
                });
            }
        };

        textAnimator = new TextAnimator("Welcome To My Show!",
                100, textOutput);

        Thread thread = new Thread(textAnimator);
        thread.start();

    }

    // LOAD WORDLE
    @FXML
    private void nextToMemoryGame(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        showMemoryPane();
    }

    // LOAD MemoryGame
    @FXML
    private void nextToWordle(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        showWordlePane();
    }

    private void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}