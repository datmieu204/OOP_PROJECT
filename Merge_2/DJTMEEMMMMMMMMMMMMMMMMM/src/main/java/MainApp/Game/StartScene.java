package MainApp.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import MainApp.Game.MemoryGame.MemStartScene;
import MainApp.Game.Wordle.WordleStartScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartScene implements Initializable{   

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

    public void hideWordlePane(){
        chooseGamePane.setVisible(true);
        wordlePane.setVisible(false);
        wordlePane.toBack();
    }
    public void hideMemoryPane(){
        chooseGamePane.setVisible(true);
        memoryPane.setVisible(false);
        memoryPane.toBack();
    }
    public void showWordlePane(){
        chooseGamePane.setVisible(false);
        wordlePane.setVisible(true);
        wordlePane.toFront();
    }
    public void showMemoryPane(){
        chooseGamePane.setVisible(false);
        memoryPane.setVisible(true);
        memoryPane.toFront();
    }
    //LOAD MemoryGame
    @FXML
    private void nextToWordle(ActionEvent event) throws IOException {
        // optionSound.setVolume(0.7);
        // optionSound.seek(Duration.ZERO);
        // optionSound.play();
        // FXMLLoader fxmlLoader = new FXMLLoader(WordleStartScene.class.getResource("/fxml/Wordle/WordleStartScene.fxml"));
        // Parent root = fxmlLoader.load();
        // Scene scene = new Scene(root);
        // Stage stage = (Stage) wordleButton.getScene().getWindow();
        // stage.setScene(scene);
        wordlePane.setVisible(true);
        wordlePane.toFront();
        chooseGamePane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(WordleStartScene.class.getResource("/fxml/Wordle/WordleStartScene.fxml"));
            String css1 = this.getClass().getResource("/css/Wordle/Start.css").toExternalForm();
            Pane rootWordle = fxmlLoader1.load();
            rootWordle.getStylesheets().add(css1);
            wordlePane.getChildren().add(rootWordle);
            hideWordlePane();

            FXMLLoader fxmlLoader2 = new FXMLLoader(WordleStartScene.class.getResource("/fxml/MemoryGame/MemStartScene.fxml"));
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
        rootPane.setOpacity(0);
        makeClearTransition(); 
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

    //LOAD WORDLE
    @FXML
    private void nextToMemoryGame(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        makeFadeOutToMemoryGame();
    }

    private void makeFadeOutToMemoryGame() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
                rootPane.setOpacity(1);
                showMemoryPane();
        });
        fadeTransition.play();
    }

    // public void loadMemoryGame(){
    //     try {
    //         Parent secondView;
    //         secondView = (Pane) FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/MemStartScene.fxml"));
    //         String css = this.getClass().getResource("/css/MemoryGame/Start.css").toExternalForm();
    //         secondView.getStylesheets().add(css);
    //         Scene newScene = new Scene(secondView);
    //         Stage curStage = (Stage) rootPane.getScene().getWindow();
    //         curStage.setScene(newScene);
    //     } catch (IOException ex) {
    //         Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    // }

    private void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }   
}