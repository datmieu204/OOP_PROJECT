package org.openjfx.MainApp.Game.MemoryGame;

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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HelloController4x4 implements Initializable {
    // Biến thời gian
    public int timeCount = 0;
    ArrayList<Button> buttons = new ArrayList<>();

    MemoryGame memoryGame = new MemoryGame();

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;

    @FXML
    private Button restart;

    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;

    @FXML
    private Button switchButton;
    @FXML
    private Button soundButton;

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private Button instructionButton;
    private Timeline gameTimer;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75), e -> hideButtons()));

    private boolean firstButtonClicked = false;
    private boolean soundOn = true;

    private ImageView soundOnImage;
    private ImageView soundOffImage;
    private ImageView instructionImage;

    private String pathSoundOn =  "DJTMEEMMMMMMMMMMMMMMMMM\\src\\main\\resources\\image\\soundOn.png";
    private String pathSoundOff = "DJTMEEMMMMMMMMMMMMMMMMM\\src\\main\\resources\\image\\soundOff.png";
    private String pathInstruction = "DJTMEEMMMMMMMMMMMMMMMMM\\src\\main\\resources\\image\\instruction.png";

    Image soundOnImg = new Image(new File(pathSoundOn).toURI().toString());
    Image soundOffImg = new Image(new File(pathSoundOff).toURI().toString());
    Image instructionImg = new Image(new File(pathInstruction).toURI().toString());

    String correct_sound = getClass().getResource("/sound/correct.mp3").toExternalForm();
    String wrong_sound = getClass().getResource("/sound/wrong.mp3").toExternalForm();
    String background_sound = getClass().getResource("/sound/background.mp3").toExternalForm();
    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    String gameSelect_sound = getClass().getResource("/sound/gameSelect.mp3").toExternalForm();

    Media correct_media = new Media(correct_sound);
    Media wrong_media = new Media(wrong_sound);
    Media background_media = new Media(background_sound);
    Media option_media = new Media(option_sound);
    Media gameSelect_media = new Media(gameSelect_sound);

    MediaPlayer correctSound = new MediaPlayer(correct_media);
    MediaPlayer wrongSound = new MediaPlayer(wrong_media);
    MediaPlayer backgroundSound = new MediaPlayer(background_media);
    MediaPlayer optionSound = new MediaPlayer(option_media);
    MediaPlayer gameSelectSound = new MediaPlayer(gameSelect_media);

    private int firstButtonIndex;
    private int secondButtonIndex;
    private boolean match;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootPane.setOpacity(0);
        makeClearTransition(); 
        GameData.gameMatrix = "4x4";

        soundOnImage = new ImageView(soundOnImg);
        soundOffImage = new ImageView(soundOffImg);
        instructionImage = new ImageView(instructionImg);
        soundButton.setGraphic(soundOffImage);
        instructionButton.setGraphic(instructionImage);

        correctSound.play();
        correctSound.stop();
        correctSound.seek(Duration.ZERO);

        wrongSound.play();
        wrongSound.stop();
        wrongSound.seek(Duration.ZERO);


        optionSound.play();
        optionSound.stop();
        optionSound.seek(Duration.ZERO);

        gameSelectSound.play();
        gameSelectSound.stop();
        gameSelectSound.seek(Duration.ZERO);

        backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundSound.setVolume(0.5);
        backgroundSound.play();
        backgroundSound.stop();
        soundOn = false;

        time.setText("Time: " + String.valueOf(timeCount));

        // Set time
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeCount++;
            time.setText("Time: " + String.valueOf(timeCount));
            if(timeCount >= GameData.TIMELIMIT){
                gameTimer.stop();
                try {
                    showResult();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }));
        gameTimer.setCycleCount(Animation.INDEFINITE);
        gameTimer.play();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, button10, button11, button12, button13, button14, button15));
        memoryGame.setupGame();

    }

    @FXML
    public void restartGame() {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  
        for(Button button : buttons){
            button.getStyleClass().remove("opened");
            button.getStyleClass().add("gameButton");
        }
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button14.setText("");
        button15.setText("");

        match = false;
        GameData.turns4x4 = 0;
        GameData.points4x4 = 0;
        timeCount = 0;
        memoryGame.reset();
        time.setText("Time: " + timeCount);
        turn.setText("Turns = " + GameData.turns4x4);
        point.setText("Points = " + GameData.points4x4);
    }

    public void nextRound() {
        for(Button button : buttons){
            button.getStyleClass().remove("opened");
            button.getStyleClass().add("gameButton");
        }
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button14.setText("");
        button15.setText("");
        match = false;
        memoryGame.reset();
        time.setText("Time: " + timeCount);
        turn.setText("Turns = " + GameData.turns4x4);
        point.setText("Points = " + GameData.points4x4);
    }

    @FXML
    void buttonClicked1(ActionEvent event) {
        gameSelectSound.setVolume(0.7);
        gameSelectSound.seek(Duration.ZERO);
        gameSelectSound.play();

        String buttonId1 = ((Control) event.getSource()).getId();
        String numberStr1 = buttonId1.replaceAll("\\D+", "");
        int check = Integer.parseInt(numberStr1);


        if ((memoryGame.checkClicked.get(check) == true)) {
            return;
        }
    if(!firstButtonClicked){
        //If next turn is started before old buttons are hidden
        if(!match){
            hideButtons();
            timeline.stop();
        }
        match = false;
        firstButtonClicked = true;
        // Get clicked button memory letter
        String buttonId = ((Control) event.getSource()).getId();
        String numberStr = buttonId.replaceAll("\\D+", "");
        firstButtonIndex = Integer.parseInt(numberStr);

        //Change clicked button text
        buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

        return;
    }
        if ((memoryGame.checkClicked.get(firstButtonIndex) == true)) {
            return;
        }

    //Get clicked button memory letter
    String buttonId = ((Control) event.getSource()).getId();
    String numberStr = buttonId.replaceAll("\\D+", "");
    secondButtonIndex = Integer.parseInt(numberStr);


    //Nếu mà nhấn ô 2 trùng ô 1 thì return
    if ((secondButtonIndex == firstButtonIndex)) {
        firstButtonClicked = true;
        return;
    }
    //Change clicked button text
    buttons.get(secondButtonIndex).setText(memoryGame.getOptionAtIndex(secondButtonIndex));

    firstButtonClicked = false;

    GameData.turns4x4 ++;
    turn.setText("Turns = " + GameData.turns4x4);
    //Check if the two clicked button match
    if(memoryGame.checkTwoPositions(firstButtonIndex,secondButtonIndex)){
        memoryGame.checkClicked.set(secondButtonIndex, true);
        memoryGame.checkClicked.set(firstButtonIndex, true);

        buttons.get(firstButtonIndex).getStyleClass().add("opened");
        buttons.get(secondButtonIndex).getStyleClass().add("opened");

        correctSound.setVolume(1.0);
        correctSound.seek(Duration.ZERO);
        correctSound.play();

        System.out.println("Match");
        match = true;
        GameData.points4x4++;
        point.setText("Points = " + GameData.points4x4);

        if(GameData.points4x4 % 8 == 0 && (memoryGame.countClicked(memoryGame.checkClicked) == 16)){
            nextRound();
        }
        return;

    }
    else{
        wrongSound.setVolume(1.0);
        wrongSound.seek(Duration.ZERO);
        wrongSound.play();
        memoryGame.checkClicked.set(firstButtonIndex, false);
        memoryGame.checkClicked.set(secondButtonIndex, false);
    }
    timeline.play();
    }

    private void hideButtons() {
        buttons.get(firstButtonIndex).setText("");
        buttons.get(secondButtonIndex).setText("");
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

    public void backToScene(){  
        backgroundSound.stop();
        soundOn = false; 

        try {
            Parent secondView;
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/ChooseTopic.fxml"));
            String css = this.getClass().getResource("/css/MemoryGame/ChooseTopic.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(HelloController4x4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished( (ActionEvent event) -> {
            backToScene();
        });
        fadeTransition.play();
    }
    private void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }  

    //Tiến tới scene Instruction
    @FXML
    private void nextToInstruction(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/Instruction.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        
        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/MemoryGame/Instruction.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showResult() throws IOException{
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();  
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemoryGame/MemoryGame/Result.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        
        // Thiết lập kiểu dáng của sân khấu bằng CSS
        String css = this.getClass().getResource("/css/MemoryGame/Result.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}