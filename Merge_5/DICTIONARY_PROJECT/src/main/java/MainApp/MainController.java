package MainApp;

import java.util.ArrayList;

import MainDictionary.DictionaryManagementApp;
import MainDictionary.DictionaryManagementUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private Button btnPronounce;

    @FXML
    private Button btnAddToUser;

    @FXML
    private Alert alert;

    // ===================================================================================================================================

    private String cur = "";
    private int curIndex = 0;
    private DictionaryManagementApp dictionaryApp = new DictionaryManagementApp();

    // ===================================================================================================================================

    @FXML
    private void pronounce(ActionEvent event) throws Exception {
        TextToSpeech.languageName = "Linda";
        TextToSpeech.languageCode = "en-gb";
        TextToSpeech.tts(cur);
    }

    @FXML
    private void addWordToUser(ActionEvent event) {
        String wordTarget = labelSearch.getText();
        String wordExplain = txtExplain.getText();

        int index = DictionaryManagementUser.lookUpUser(wordTarget);
        if (index == -1) {
            dictionaryApp.addWord(wordTarget, wordExplain);
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
        dictionaryApp.insertFromFile();

        // Dua list tu mydictionary vao listView xuat ra man hinh
        for (int i = 0; i < dictionaryApp.getList_word().size(); i++) {
            listView.getItems().add(dictionaryApp.getList_word().get(i).getWordTarget());
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

                curIndex = dictionaryApp.lookUp(cur);

                labelSearch.setText(dictionaryApp.getList_word().get(curIndex).getWordTarget());
                txtExplain.setText(dictionaryApp.getList_word().get(curIndex).getWordExplain());

                txtExplain.setEditable(false);
            }
        });

        listView.getSelectionModel().select(0);
    }

    private void uploadListView() {
        listView.getItems().clear();

        ArrayList<String> list = dictionaryApp.searcher(txtSearch.getText());

        for (int i = 0; i < list.size(); i++) {
            listView.getItems().add(list.get(i));
        }
    }
}
