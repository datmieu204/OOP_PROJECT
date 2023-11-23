package MainApp.Game.MemoryGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import MainApp.Game.StartScene;
import MainApp.Game.Wordle.GameScene;
import MainApp.Game.Wordle.WordleStartScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemStartScene implements Initializable{
    static StartScene startScene;
    public static void setStartScene(StartScene startScene) {
        MemStartScene.startScene = startScene;
    }
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Pane rootPane;
    @FXML
    private Pane chooseTopicPane;
    @FXML
    private AnchorPane memStartPane;

    private String[] level = {"3x3", "4x4", "5x5"};
    public static String matrixType;
    private int boardLength = 3;

    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    

    public void showChooseTopicPane(){
        memStartPane.setVisible(false);
        chooseTopicPane.setVisible(true);
        chooseTopicPane.toFront();
    }
    public void hideChooseTopicPane(){
        memStartPane.setVisible(true);
        chooseTopicPane.setVisible(false);
        chooseTopicPane.toBack();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            StackPane tmpPane = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/ChooseTopic.fxml"));
            String css = this.getClass().getResource("/css/MemoryGame/ChooseTopic.css").toExternalForm();
            tmpPane.getStylesheets().add(css);
            chooseTopicPane.getChildren().add(tmpPane);
            hideChooseTopicPane();

            ChooseTopic.setMemStartScene(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        choiceBox.getSelectionModel().select("3x3");
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
            rootPane.setOpacity(1);
            loadNextScene();
        });
        fadeTransition.play();
    }

    public void loadNextScene(){
        matrixType = "3x3";
        if(boardLength == 3){
            matrixType = "3x3";
        }
        else if(boardLength == 4){
            matrixType ="4x4";
        }
        else if(boardLength == 5){
            matrixType = "5x5";
        }
        showChooseTopicPane();

    }

    private void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }   
    
    @FXML
    private void backToStartScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  
        makeFadeOutToStart();
    }

    public void backToStart(){
        startScene.hideMemoryPane();
        // try {
        //     Parent secondView;
        //     secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
        //     String css = this.getClass().getResource("/css/style.css").toExternalForm();
        //     secondView.getStylesheets().add(css);
        //     Scene newScene = new Scene(secondView);
        //     Stage curStage = (Stage) rootPane.getScene().getWindow();
        //     curStage.setScene(newScene);
        // } catch (IOException ex) {
        //     Logger.getLogger(GameScene.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }

    private void makeFadeOutToStart() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.millis(TimeUnit.SECONDS.toMillis(1 )));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            rootPane.setOpacity(1);
            backToStart();
            
        });
        fadeTransition.play();
    }
}