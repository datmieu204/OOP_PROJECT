package MainApp;
import java.util.ArrayList;

import MainDictionary.DictionaryMain;
import MainDictionary.DictionaryManagementMain;
import MainDictionary.DictionaryManagementUser;

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
    private Button btnPronounce;

    @FXML
    private Alert alert;

    // ===================================================================================================================================
    
    private String cur = "";
    private int curIndex = 0;

    // ===================================================================================================================================

    @FXML
    private void pronounce(ActionEvent event) throws Exception {
        TextToSpeech.tts(cur);
    }

    @FXML
    private void addWordToUser(ActionEvent event) {
        String wordTarget = labelSearch.getText();
        String wordExplain = txtExplain.getText();

        int index = DictionaryManagementUser.lookUpUser(wordTarget);
        if (index == -1) {
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
    private void initialize() {
        // Doc file tu dien
        DictionaryManagementMain.insertFromFile();

        // Dua list tu mydictionary vao listView xuat ra man hinh
        for (int i = 0; i < DictionaryMain.mainDictionary.size(); i++) {
            listView.getItems().add(DictionaryMain.mainDictionary.get(i).getWordTarget());
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

                labelSearch.setText(DictionaryMain.mainDictionary.get(curIndex).getWordTarget());
                txtExplain.setText(DictionaryMain.mainDictionary.get(curIndex).getWordExplain());

                txtExplain.setEditable(false);
                labelSearch.setBlendMode(BlendMode.DARKEN);

                // if (curIndex == 0) {
                // txtExplain.setEditable(false);
                // labelSearch.setBlendMode(BlendMode.DARKEN);
                // }

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
