package MainApp.Game.MemoryGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class ChooseTopic implements Initializable {
    static MemStartScene memStartScene;

    public static void setMemStartScene(MemStartScene memStart) {
        ChooseTopic.memStartScene = memStart;
    }

    static GameController3x3 game3x3;
    static GameController4x4 game4x4;
    static GameController5x5 game5x5;

    public static void setGame3x3(GameController3x3 game3x3) {
        ChooseTopic.game3x3 = game3x3;
    }

    public static void setGame4x4(GameController4x4 game4x4) {
        ChooseTopic.game4x4 = game4x4;
    }

    public static void setGame5x5(GameController5x5 game5x5) {
        ChooseTopic.game5x5 = game5x5;
    }

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private StackPane rootPane;
    @FXML
    private Button backToStart;
    @FXML
    private Pane memory3x3;
    @FXML
    private Pane memory4x4;
    @FXML
    private Pane memory5x5;
    @FXML
    private AnchorPane chooseTopicPane;

    private String[] topic = { "Animal", "Body", "Family" };
    TextAnimator textAnimator;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private Text text;

    public void show3x3Pane() {
        chooseTopicPane.setVisible(false);
        memory3x3.setVisible(true);
        memory3x3.toFront();
    }

    public void show4x4Pane() {
        chooseTopicPane.setVisible(false);
        memory4x4.setVisible(true);
        memory4x4.toFront();
    }

    public void show5x5Pane() {
        chooseTopicPane.setVisible(false);
        memory5x5.setVisible(true);
        memory5x5.toFront();
    }

    public void hide3x3Pane() {
        chooseTopicPane.setVisible(true);
        memory3x3.setVisible(false);
        memory3x3.toFront();
        makeClearTransition();
    }

    public void hide4x4Pane() {
        chooseTopicPane.setVisible(true);
        memory4x4.setVisible(false);
        memory4x4.toFront();
        makeClearTransition();

    }

    public void hide5x5Pane() {
        chooseTopicPane.setVisible(true);
        memory5x5.setVisible(false);
        memory5x5.toFront();
        makeClearTransition();

    }

    public void start() {
        makeClearTransition();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeClearTransition();
        MemStartScene.setChooseTopic(this);
        try {
            StackPane pane3x3 = (StackPane) FXMLLoader
                    .load(getClass().getResource("/fxml/MemoryGame/GameScene3x3.fxml"));
            StackPane pane4x4 = (StackPane) FXMLLoader
                    .load(getClass().getResource("/fxml/MemoryGame/GameScene4x4.fxml"));
            StackPane pane5x5 = (StackPane) FXMLLoader
                    .load(getClass().getResource("/fxml/MemoryGame/GameScene5x5.fxml"));
            memory3x3.getChildren().add(pane3x3);
            memory4x4.getChildren().add(pane4x4);
            memory5x5.getChildren().add(pane5x5);
            String css1 = this.getClass().getResource("/css/MemoryGame/Game.css").toExternalForm();
            String css2 = this.getClass().getResource("/css/MemoryGame/Game.css").toExternalForm();
            String css3 = this.getClass().getResource("/css/MemoryGame/Game.css").toExternalForm();
            pane3x3.getStylesheets().add(css1);
            pane4x4.getStylesheets().add(css2);
            pane5x5.getStylesheets().add(css3);
            hide3x3Pane();
            hide4x4Pane();
            hide5x5Pane();
            GameController3x3.setChooseTopicScene(this);
            GameController4x4.setChooseTopicScene(this);
            GameController5x5.setChooseTopicScene(this);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        optionSound.play();
        optionSound.stop();
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
    private void handleButtonClick(ActionEvent event) throws IOException {
        optionSound.setVolume(1.0);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        loadNextScene();
    }

    public void loadNextScene() {
        if (MemStartScene.matrixType.equals("3x3")) {
            show3x3Pane();
            game3x3.startGame();
            System.out.println("3x3");
        } else if (MemStartScene.matrixType.equals("4x4")) {
            show4x4Pane();
            game4x4.startGame();
            System.out.println("4x4");
        } else if (MemStartScene.matrixType.equals("5x5")) {
            show5x5Pane();
            game5x5.startGame();
            System.out.println("5x5");
        }
    }

    public void makeClearTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(event -> rootPane.setOpacity(1));
        fadeTransition.play();
    }

    @FXML
    private void backToStartScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        memStartScene.hideChooseTopicPane();
    }
}