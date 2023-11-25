package MainApp.Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Start.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            String css = this.getClass().getResource("/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout!!");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
