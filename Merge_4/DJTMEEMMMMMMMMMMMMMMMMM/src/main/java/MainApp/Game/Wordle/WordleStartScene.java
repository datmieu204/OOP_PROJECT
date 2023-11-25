package MainApp.Game.Wordle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import MainApp.Game.StartScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WordleStartScene implements Initializable {
    static GameScene gameScene;

    public static void setGameScene(GameScene gameScene) {
        WordleStartScene.gameScene = gameScene;
    }

    static StartScene startScene;

    public static void setStartScene(StartScene startScene) {
        WordleStartScene.startScene = startScene;
    }

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Pane rootPane;

    @FXML
    private Button backButton;
    @FXML
    private StackPane wordleGame;
    @FXML
    private AnchorPane selectTopic;

    private String[] topic = { "Animal", "Body", "Environment" };
    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    public void showWordleGame() {
        selectTopic.setVisible(false);
        wordleGame.setVisible(true);
        wordleGame.toFront();
        gameScene.makeClearTransition();
    }

    public void hideWordleGame() {
        selectTopic.setVisible(true);
        wordleGame.setVisible(false);
        wordleGame.toBack();
        makeClearTransition();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        optionSound.play();
        optionSound.stop();
        rootPane.setOpacity(0);

        try {
            StackPane tmpPane = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/Wordle/GameScene.fxml"));
            String css = this.getClass().getResource("/css/Wordle/Game.css").toExternalForm();
            tmpPane.getStylesheets().add(css);
            wordleGame.getChildren().add(tmpPane);
            hideWordleGame();

            GameScene.setWordleStartScene(this);
            StartScene.setWordleStartScene(this);

        } catch (IOException ex) {
            Logger.getLogger(WordleStartScene.class.getName()).log(Level.SEVERE, null, ex);
        }

        choiceBox.getSelectionModel().select("Animal");
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

        choiceBox.getItems().addAll(topic);
        choiceBox.setOnAction(this::setTopic);
    }

    public void setTopic(ActionEvent event) {
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        String myTopic = choiceBox.getValue();
        Word.setTopic(myTopic);
    }

    @FXML
    private void nextToGame(ActionEvent event) throws IOException {
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        gameScene.startGame();
        showWordleGame();
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
    public void backToStartScene() throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        startScene.hideWordlePane();

    }
}