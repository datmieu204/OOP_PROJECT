package MainApp.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private AnchorPane gamePane;

    @FXML
    private Pane wordlePane;

    @FXML
    private Button wordleButton;

    @FXML
    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WordleStartScene.class.getResource("/fxml/Wordle/WordleStartScene.fxml"));
            Pane rootWordle = fxmlLoader.load();

            wordlePane.getChildren().add(rootWordle);
            wordlePane.setVisible(false);
            wordlePane.toBack();
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
        gamePane.setVisible(false);
    }

    //LOAD WORDLE
    @FXML
    private void nextToMemoryGame(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        FXMLLoader fxmlLoader = new FXMLLoader(WordleStartScene.class.getResource("/fxml/Start.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) wordleButton.getScene().getWindow();
        stage.setScene(scene);
    }

    private void makeFadeOutToMemoryGame() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            try {
                nextToMemoryGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
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