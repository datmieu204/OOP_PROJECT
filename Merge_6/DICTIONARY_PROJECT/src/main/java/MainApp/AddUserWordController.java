package MainApp;

import MainDictionary.DictionaryManagementUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddUserWordController {

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

    // ===================================================================================================================================

    @FXML
    public void initialize() {
        btnAdd.requestFocus();
        btnCancel.requestFocus();
    }

    @FXML
    private void addWord(ActionEvent event) {
        String wordTarget = txtWordTarget.getText();
        String wordExplain = txtWordExplain.getText();

        if (wordTarget.equals("") || wordExplain.equals("")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all the fields");
        } else {
            if (DictionaryManagementUser.lookUpUser(wordTarget) != -1) {
                showAlert(Alert.AlertType.ERROR, "Error", "This word is already in the dictionary");
            } else {
                DictionaryManagementUser.addWordUser(wordTarget, wordExplain);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Add word successfully");
                checkAdd();
                clearFields();
                closeWindow();
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtWordTarget.clear();
        txtWordExplain.clear();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void checkAdd() {

    }

    @FXML
    private void cancel(ActionEvent event) {
        txtWordTarget.setText("");
        txtWordExplain.setText("");
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void targetFocus() {
        targetImg.setVisible(false);
    }

    @FXML
    private void explainFocus() {
        explainImg.setVisible(false);
    }
}
