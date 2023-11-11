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
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController5x5 implements Initializable {
    // Biến thời gian
    int i = 0;
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
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    
    @FXML
    private Button restart;

    @FXML
    private Button switchButton;
    @FXML
    private Button soundButton;

    @FXML
    private Label point;
    @FXML
    private Label turn;
    @FXML
    private Label time;

    @FXML
    private StackPane rootPane;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75), e -> hideButtons()));

    private boolean firstButtonClicked = false;

    private boolean soundOn = true;

    private ImageView soundOnImage;
    private ImageView soundOffImage;

    Image soundOnImg = new Image("/image/soundOn.png");
    Image soundOffImg = new Image("/image/soundOff.png");
    
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

    public int turns = 0;
    public int points = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootPane.setOpacity(0);
        makeClearTransition(); 

        soundOnImage = new ImageView(soundOnImg);
        soundOffImage = new ImageView(soundOffImg);
        soundButton.setGraphic(soundOffImage);
        makeClearTransition();


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

        time.setText("Time: " + String.valueOf(i));

        // Set time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            i++;
            time.setText("Time: " + String.valueOf(i));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, button10, button11, button12, button13, button14, button15,
                button16, button17, button18, button19, button20, button21, button22, button23, button24));
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
        button16.setText("");
        button17.setText("");
        button18.setText("");
        button19.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button24.setText("");
        match = false;
        turns = 0;
        points = 0;
        i = 0;
        memoryGame.reset();
        time.setText("Time: " + i);
        turn.setText("Turns = " + turns);
        point.setText("Points = " + points);
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
        button16.setText("");
        button17.setText("");
        button18.setText("");
        button19.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button24.setText("");
        match = false;
        memoryGame.reset();
        time.setText("Time: " + i);
        turn.setText("Turns = " + turns);
        point.setText("Points = " + points);
    }

    @FXML
    void buttonClicked(ActionEvent event) {
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
            //Get clicked button memory letter
            // Get clicked button memory letter
            String buttonId = ((Control) event.getSource()).getId();
            String numberStr = buttonId.replaceAll("\\D+", "");
            firstButtonIndex = Integer.parseInt(numberStr);


            if ((memoryGame.checkClicked.get(firstButtonIndex) == true)) {
                return;
            }

            //Change clicked button text
            buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

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

        turns ++;
        turn.setText("Turns = " + turns);
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
            points++;
            point.setText("Points = " + points);

            if(points % 12 == 0 && (memoryGame.countClicked(memoryGame.checkClicked) == 24)){
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
            secondView = (StackPane) FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
            String css = this.getClass().getResource("/css/Style.css").toExternalForm();
            secondView.getStylesheets().add(css);
            Scene newScene = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(HelloController5x5.class.getName()).log(Level.SEVERE, null, ex);
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

}