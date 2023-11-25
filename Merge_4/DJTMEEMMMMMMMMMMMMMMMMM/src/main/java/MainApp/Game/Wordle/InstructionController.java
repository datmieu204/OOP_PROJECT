package MainApp.Game.Wordle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InstructionController {
    @FXML
    private Button backButton;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane stackPane;

    String option_sound = getClass().getResource("/sound/option.mp3").toExternalForm();
    Media option_media = new Media(option_sound);
    MediaPlayer optionSound = new MediaPlayer(option_media);

    @FXML
    private void backToGameScene(ActionEvent event) throws IOException {
        optionSound.setVolume(0.7);
        optionSound.seek(Duration.ZERO);
        optionSound.play();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
