import java.io.IOException;

import javafx.fxml.FXML;

public class ControllerSceneMenu1 {
    @FXML
    private void switchToSceneFirst() throws IOException{
        App.setRoot("SceneFirst");
    }

    @FXML 
    private void switchToVocab() throws IOException{
        App.setRoot("SceneApp");
    }
    
}