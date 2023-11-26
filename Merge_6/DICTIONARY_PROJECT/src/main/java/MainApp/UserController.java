package MainApp;

import java.io.IOException;

import MainDictionary.DictionaryManagementUser;
import MainDictionary.DictionaryUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UserController{

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea txtExplain;

    @FXML
    private Label labelSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnFix;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPronounce;

    private String cur = "";
    private int curIndex = 0;
    private boolean isEdit = false;

    // ==================================================================================================================================
    // THEM, SUA, XOA TU
    
    @FXML
    private void addNewWord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/SceneAddWord.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Add Word");
        stage.showAndWait();
        uploadUserListView();
    }   

    private void fixWord() throws IOException {
        btnFix.setOnAction(e -> {

            if (isEdit) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Fix Word");
                confirm.setHeaderText("Are you sure you want to fix this word?");

                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {

                        btnFix.setDisable(false);
                        btnDelete.setDisable(false);
                        btnAdd.setDisable(false);
                        check();
                        txtExplain.setEditable(false);
                        //btnFix.setText("Fix");

                        String s = DictionaryManagementUser.fixUserWord(cur, txtExplain.getText());
                        txtExplain.setText("- " + s);
                        isEdit = false;

                    } else {
                        e.consume();
                    }
                });
            } else {

                btnDelete.setDisable(true);
                btnAdd.setDisable(true);
                check();
                btnFix.setDisable(false);
                //btnFix.setText("Save");

                txtExplain.setEditable(true);
                isEdit = true;
            }
        });
    }

    private void deleteWord() throws IOException {

        btnDelete.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Delete Word");
            alert.setHeaderText("Are you sure you want to delete this word?");
            alert.setContentText(labelSearch.getText());

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    int preIndex = listView.getSelectionModel().getSelectedIndex() - 1;

                    DictionaryManagementUser.deleteWordUser(curIndex);
                    DictionaryManagementUser.exportToFileUser();

                    check();
                    uploadUserListView();
                    listView.getSelectionModel().select(preIndex);

                    if (listView.getSelectionModel().getSelectedIndex() == -1) {
                        offAction();
                    }
                } else {
                    e.consume();
                }
            });
        });
    }

    // ==================================================================================================================================

    private void resetAction() {
        txtExplain.setEditable(false);
        check();
        btnDelete.setDisable(false);
        btnFix.setDisable(false);
    }

    private void offAction() {
        txtExplain.setEditable(false);
        check();
        btnDelete.setDisable(true);
        btnFix.setDisable(true);
    }

    private void uploadUserListView() {
        listView.getItems().clear();
        for (int i = 0; i < DictionaryUser.list_user.size(); i++) {
            String word = DictionaryUser.list_user.get(i).getWordTarget();
            listView.getItems().add(word);
        }
    }

    public void check() {

    }

    @FXML
    private void pronounce(ActionEvent event) throws Exception {
        TextToSpeech.languageName = "Linda";
        TextToSpeech.languageCode = "en-gb";
        TextToSpeech.tts(cur);
    }

    // ==================================================================================================================================
    @FXML
    private void initialize() throws IOException {
        DictionaryManagementUser.insertFromFileUser();

        for (int i = 0; i < DictionaryUser.list_user.size(); i++) {
            String word = DictionaryUser.list_user.get(i).getWordTarget();
            listView.getItems().add(word);
        }

        // xu li su kien khi nhan xoa tu trong listView
        deleteWord();


        // xu li su kien khi nhan sua tu trong listView
        fixWord();

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                cur = newValue;
                labelSearch.setText(cur);

                curIndex = DictionaryManagementUser.lookUpUser(cur);
                check();
                labelSearch.setText(DictionaryUser.list_user.get(curIndex).getWordTarget());
                txtExplain.setText(DictionaryUser.list_user.get(curIndex).getWordExplain());

                resetAction();
            }
        });

        listView.getSelectionModel().select(0);
    }
}
