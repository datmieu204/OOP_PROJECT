package org.openjfx.hellofx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController implements Initializable{

    @FXML
    private ChoiceBox<String> choiceBox;
    
    private String[] level = {"3x3", "4x4", "5x5"};

    private Stage stage;
    private Scene scene;

    private int boardLength = 3;

    TextAnimator textAnimator;
    @FXML
    private Text text;

    public void switchToGame(ActionEvent event) throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource(matrix));

        String css = this.getClass().getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    // @FXML
    // void start(ActionEvent event) {
    //     Thread thread = new Thread(textAnimator);
    //     thread.start();
    // }




    public void getLevel(ActionEvent event){
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
}