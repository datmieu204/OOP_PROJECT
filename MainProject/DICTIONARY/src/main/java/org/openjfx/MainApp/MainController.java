package org.openjfx.MainApp;

import java.io.IOException;
import java.util.ArrayList;

import org.openjfx.MainDictionary.Dictionary;
import org.openjfx.MainDictionary.DictionaryManagementMain;
import org.openjfx.MainDictionary.DictionaryManagementUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;

public class MainController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Label labelSearch;

    @FXML
    private TextArea txtExplain;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnAddToUser;

    @FXML   
    private Button btnUser;

    @FXML
    private Button btnPronounce;

    @FXML
    private Alert alert;

    @FXML
    private Button btnGG;

    // @FXML
    // private TextArea txtGG;

    // @FXML
    // private TextArea txtGGtrans;

    //===================================================================================================================================
    private String cur = "";
    private int curIndex = 0;

    private void resetAction() {
        txtExplain.setEditable(false);
        labelSearch.setBlendMode(BlendMode.DARKEN);
        // txtExplain.setBlendMode(BlendMode.DARKEN);
    }

    private void offAction() {
        txtExplain.setEditable(false);
        labelSearch.setBlendMode(BlendMode.DARKEN);
        // txtExplain.setBlendMode(BlendMode.DARKEN);
    }

    @FXML
    private void pronounce(ActionEvent event) throws Exception {
        TextToSpeech.tts(cur);
    }

    // @FXML
    // private void ggTranslate(ActionEvent event) throws IOException{
    //     txtGGtrans.setText(GoogleAPI.translate("en", "vi", txtGG.getText()));
    // }

    // @FXML
    // private void ggTranslateSwap(ActionEvent event) throws IOException{
    //     txtGGtrans.setText(GoogleAPI.translate("vi", "en", txtGG.getText()));
    // }

    @FXML
    private void addWordToUser(ActionEvent event){
        String wordTarget = labelSearch.getText();
        String wordExplain = txtExplain.getText();

        int index = DictionaryManagementUser.lookUpUser(wordTarget);
        if(index == -1){
            DictionaryManagementMain.addWord(wordTarget, wordExplain);
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Word");
            alert.setHeaderText("Word already exists");
            alert.setContentText("Please try again!");
            alert.showAndWait();
        }
    }

    @FXML
    public void showUser() throws IOException {
        App.setRoot("Scene/SceneUser");
    }

    @FXML
    public void showGG() throws IOException {
        App.setRoot("Scene/SceneGGAPI");
    }

    @FXML
    private void initialize() {
        // Doc file tu dien
        DictionaryManagementMain.insertFromFile();

        // Dua list tu mydictionary vao listView xuat ra man hinh
        for (int i = 0; i < Dictionary.mainDictionary.size(); i++) {
            listView.getItems().add(Dictionary.mainDictionary.get(i).getWordTarget());
        }

        // cap nhat moi khi nhap vao o tim kiem
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            uploadListView();
        });

        // cap nhat moi khi chon tu trong listView
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {

                cur = listView.getSelectionModel().getSelectedItem();
                labelSearch.setText(cur);

                curIndex = DictionaryManagementMain.lookUp(cur);

                labelSearch.setText(Dictionary.mainDictionary.get(curIndex).getWordTarget());
                txtExplain.setText(Dictionary.mainDictionary.get(curIndex).getWordExplain());

                resetAction();

                if (curIndex == 0) {
                    offAction();
                }

            }
        });

        listView.getSelectionModel().select(0);
    }

    private void uploadListView() {
        listView.getItems().clear();

        ArrayList<String> list = DictionaryManagementMain.searcher(txtSearch.getText());

        for (int i = 0; i < list.size(); i++) {
            listView.getItems().add(list.get(i));
        }
    }
}
