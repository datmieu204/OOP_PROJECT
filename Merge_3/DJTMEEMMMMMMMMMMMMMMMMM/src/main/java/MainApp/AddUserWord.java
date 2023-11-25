package MainApp;

import MainDictionary.DictionaryManagementUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddUserWord {
    
    @FXML
    private TextField txtWordTarget;

    @FXML
    private TextField txtWordExplain;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private ImageView targetImg;

    @FXML
    private ImageView explainImg;
    //===================================================================================================================================
    @FXML
    public void initialize() {
        btnAdd.requestFocus();
        btnCancel.requestFocus();
    }

    @FXML
    private void addWord(ActionEvent event){
        String wordTarget = txtWordTarget.getText();
        String wordExplain = txtWordExplain.getText();

        if(wordTarget.equals("") || wordExplain.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You must fill in all the fields");
            alert.showAndWait();
        }
        else{
            if(DictionaryManagementUser.lookUpUser(wordTarget) != -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This word already exists");
                alert.showAndWait();
                return;
            } else {
                DictionaryManagementUser.addWordUser(wordTarget, wordExplain);
                txtWordTarget.setText("");
                txtWordExplain.setText("");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Add word successfully");
                alert.showAndWait();
                
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event){
        txtWordTarget.setText("");
        txtWordExplain.setText("");
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void targetFocus(){
        targetImg.setVisible(false);
        explainLostFocus();
    }
    
    private void targetLostFocus(){
        targetImg.setVisible(true);
    }

    @FXML
    private void explainFocus(){
        explainImg.setVisible(false);
        targetLostFocus();
    }
    
    private void explainLostFocus(){
        explainImg.setVisible(true);
    }
}
