import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControllerSceneFirst implements Initializable {

    TextAnimator textAnimator;
    @FXML
    private Text text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        textAnimator = new TextAnimator("WELCOME!",
                150, textOutput);
        Thread thread = new Thread(textAnimator);
        thread.start();
    }

    @FXML
    private void switchToScene2() throws IOException {
        //Tạo một transition Scale từ 1 đến 0
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), text);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);

        // Xử lý sự kiện kết thúc transition
        scaleTransition.setOnFinished(event -> {
            try {
                App.setRoot("SceneMenu1");


//                 // Chuyển đến scene2
//                 // FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
//                 // Parent scene2Root = loader.load();
//                 // Scene scene2 = new Scene(scene2Root);
//                 // Stage primaryStage = (Stage) text.getScene().getWindow();
//                 // primaryStage.setScene(scene2);




            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Chạy transition
        scaleTransition.play();
    }
}
