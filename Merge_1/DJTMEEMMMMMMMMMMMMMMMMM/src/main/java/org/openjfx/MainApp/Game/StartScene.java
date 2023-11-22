package org.openjfx.MainApp.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartScene implements Initializable{
    @FXML
    private StackPane rootPane;
    @FXML
    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void nextToWordle(ActionEvent event) throws IOException {
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        makeFadeOutToWordle();
    }

    private void makeFadeOutToWordle() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            loadWordle();
        });
        fadeTransition.play();
    }

    public void loadWordle(){
        try {
            Parent secondView;
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/Wordle/WordleStartScene.fxml"));
            String css = this.getClass().getResource("/css/Wordle/Start.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //LOAD WORDLE
    @FXML
    private void nextToMemoryGame(ActionEvent event) throws IOException {
        optionSound.setVolume(1.0);
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
            loadMemoryGame();
        });
        fadeTransition.play();
    }

    public void loadMemoryGame(){
        try {
            Parent secondView;
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/MemStartScene.fxml"));
            String css = this.getClass().getResource("/css/MemoryGame/Start.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
        }
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