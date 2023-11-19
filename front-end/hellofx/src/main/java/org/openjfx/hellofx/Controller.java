package org.openjfx.hellofx;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private AnchorPane pane1, pane2;

    @FXML
    private ImageView exit, menu;
    @FXML
    private StackPane contentArea;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        try{
            Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        pane1.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        menu.setOnMouseClicked(event -> {


            pane1.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.5);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        pane1.setOnMouseClicked(event -> {
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0.5);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });
    }


    public void Home(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    
        // Thực hiện hiệu ứng thu gọn sidebar
        pane1.setVisible(false);
    
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.5);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
    
        fadeTransition1.setOnFinished(event -> {
            pane1.setVisible(false);
        });
    
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }
    public void Add(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Add.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    
        // Thực hiện hiệu ứng thu gọn sidebar
        pane1.setVisible(false);
    
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.5);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
    
        fadeTransition1.setOnFinished(event -> {
            pane1.setVisible(false);
        });
    
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }
    public void Setting(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    
        // Thực hiện hiệu ứng thu gọn sidebar
        pane1.setVisible(false);
    
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.5);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
    
        fadeTransition1.setOnFinished(event -> {
            pane1.setVisible(false);
        });
    
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }
    public void User(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("User.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    
        // Thực hiện hiệu ứng thu gọn sidebar
        pane1.setVisible(false);
    
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.5);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
    
        fadeTransition1.setOnFinished(event -> {
            pane1.setVisible(false);
        });
    
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }

}
