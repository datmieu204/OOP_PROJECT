import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("SceneFirst"));
        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }
    
}

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.stage.StageStyle;

// public class App extends Application {
//     double x,y =0;
//     @Override 
//     public void start(Stage primaryStage) throws Exception {
//         Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//         primaryStage.initStyle(StageStyle.UNDECORATED);

//         root.setOnMousePressed(event -> {
//             x = event.getSceneX();
//             y = event.getSceneY();
//         });

//         root.setOnMouseDragged(event -> {
//             primaryStage.setX(event.getScreenX() - x);
//             primaryStage.setY(event.getScreenY() - y);
//         });
//         primaryStage.setScene(new Scene(root, 300, 275));
//         primaryStage.show();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }