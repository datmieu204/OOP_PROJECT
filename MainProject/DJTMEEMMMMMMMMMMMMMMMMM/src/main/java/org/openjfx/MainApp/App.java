package org.openjfx.MainApp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("Scene/SceneApp"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MEO DICTIONARY"); 
        // Image iconDictionary = new Image("D:\\DJTMEEMMMMMMMMMMMMMMMMM\\src\\main\\resources\\org\\openjfx\\MainApp\\image\\uetpic.png");
        // stage.getIcons().add(iconDictionary);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
