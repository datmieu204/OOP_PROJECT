package org.openjfx.hellofx;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneController implements Initializable{

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private StackPane rootPane;

    private String[] level = {"3x3", "4x4", "5x5"};



    private int boardLength = 3;

    TextAnimator textAnimator;

    String option_sound = getClass().getResource("option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        optionSound.play();
        optionSound.stop();
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

        choiceBox.getItems().addAll(level);
        choiceBox.setOnAction(this::getLevel);
    }

    public void getLevel(ActionEvent event){
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        String myLevel = choiceBox.getValue();
        if(myLevel.equals("3x3")){
            MemoryGame.setBoardLength(3);
            boardLength = 3;
        }
        else if(myLevel.equals("4x4")){
            MemoryGame.setBoardLength(4);
            boardLength = 4;
        }
        else if(myLevel.equals("5x5")){
            MemoryGame.setBoardLength(5);
            boardLength = 5;
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        makeFadeOut();
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            loadNextScene();
        });
        fadeTransition.play();
    }

    public void loadNextScene(){
        String matrix = "GameScene3x3.fxml";
        if(boardLength == 3){
            matrix = "GameScene3x3.fxml";
        }
        else if(boardLength == 4){
            matrix = "GameScene4x4.fxml";
        }
        else if(boardLength == 5){
            matrix = "GameScene5x5.fxml";
        }

        try {
            Parent secondView;
            secondView = (StackPane) FXMLLoader.load(getClass().getResource(matrix));
            String css = this.getClass().getResource("Style.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
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