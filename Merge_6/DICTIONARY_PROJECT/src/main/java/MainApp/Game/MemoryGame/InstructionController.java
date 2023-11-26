package MainApp.Game.MemoryGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InstructionController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorRoot;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionSound.play();
        optionSound.stop();
        optionSound.seek(Duration.ZERO);
    }

    @FXML
    private void backToGameScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
