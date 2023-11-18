package org.openjfx.MainApp;

import java.io.IOException;
import java.util.Optional;

import org.openjfx.MainDictionary.DictionaryManagementUser;
import org.openjfx.MainDictionary.DictionaryUser;
//import org.openjfx.MainDictionary.Word;

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
    private Button btnBack;

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

    // ==================================================================================================================================
    private String cur = "";
    private int curIndex = 0;

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

    @FXML
    private void fixWord(ActionEvent event) {
        btnFix.setDisable(true);
        btnDelete.setDisable(true);
        labelSearch.setBlendMode(null);
        // txtExplain.setBlendMode(null);

        if(txtExplain.isEditable()){
            txtExplain.setEditable(false);
        } else {
            txtExplain.setEditable(true);
        }
        btnSave.setDisable(true);
    }

    @FXML
    private void saveWord(ActionEvent event){
        btnFix.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setVisible(true);

        txtExplain.setEditable(false);

        //Word word = new Word(cur, txtExplain.getText());
        String s  = DictionaryManagementUser.fixUserWord(cur, txtExplain.getText());
        txtExplain.setText(s);
    }

    @FXML
    private void deleteWord(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Word");
        alert.setHeaderText("Are you sure you want to delete this word?");
        alert.setContentText(labelSearch.getText());

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Yes");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setText("No");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            int preIndex = listView.getSelectionModel().getSelectedIndex() - 1;

            DictionaryManagementUser.deleteWordUser(curIndex);
            DictionaryManagementUser.exportToFileUser();
            uploadUserListView();
            listView.getSelectionModel().select(preIndex);

            if (listView.getSelectionModel().getSelectedIndex() == 0) {
                offAction();
            }

            // if(listView.getItems().size() == 0){
            // txtExplain.setText("");
            // labelSearch.setText("");
            // offAction();
            // }
        } else if (result.get() == ButtonType.CANCEL) {
            event.consume();
        }
    }

    // ==================================================================================================================================
    @FXML
    public void backToMain() throws IOException {
        App.setRoot("Scene/SceneApp");
    }

    private void resetAction() {
        txtExplain.setEditable(false);
        labelSearch.setBlendMode(null);
        // txtExplain.setBlendMode(null);
        btnDelete.setDisable(false);
        btnFix.setDisable(false);

    }

    private void offAction() {
        txtExplain.setEditable(false);
        labelSearch.setBlendMode(null);
        // txtExplain.setBlendMode(null);
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

    // ==================================================================================================================================
    @FXML
    private void initialize() {
        DictionaryManagementUser.insertFromFileUser();

        for (int i = 0; i < DictionaryUser.list_user.size(); i++) {
            String word = DictionaryUser.list_user.get(i).getWordTarget();
            listView.getItems().add(word);
        }

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                cur = newValue;
                labelSearch.setText(cur);

                curIndex = DictionaryManagementUser.lookUpUser(cur);

                labelSearch.setText(DictionaryUser.list_user.get(curIndex).getWordTarget());
                txtExplain.setText(DictionaryUser.list_user.get(curIndex).getWordExplain());

                resetAction();
            }
        });

        listView.getSelectionModel().select(0);
    }
}
