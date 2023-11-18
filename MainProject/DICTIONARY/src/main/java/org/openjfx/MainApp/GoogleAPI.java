package org.openjfx.MainApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openjfx.MainApp.Model.SelectLang;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class GoogleAPI {

    @FXML
    private TextArea txtGG;

    @FXML
    private TextArea txtGGtrans;

    @FXML
    private ComboBox<String> toCountry;

    @FXML
    private ComboBox<String> fromCountry;

    @FXML
    private Button btnSwap;

    @FXML
    private Button pronounceTo;

    @FXML
    private Button pronounceFrom;

    List<String> list_lang = new ArrayList<String>();
    List<String> list_lang_code = new ArrayList<String>();
    List<String> list_toCountry = new ArrayList<String>();
    List<String> list_fromCountry = new ArrayList<String>();

    String translatedText = "";

    SelectLang sl;

    // ==============================================================================================================================

    @FXML
    public void back(ActionEvent event) throws IOException {
        App.setRoot("Scene/SceneApp");
    }

    @FXML
    private void ggTranslate(ActionEvent event) throws IOException {
        translate(txtGG.getText());
    }

    @FXML
    private void pronounceTo(ActionEvent event) throws Exception {
        TextToSpeech.tts(translatedText);
    }

    // loi
    @FXML
    private void pronounceFrom(ActionEvent event) throws Exception {
        TextToSpeech.tts(txtGG.getText());
    }

    @FXML
    private void ggSwap(ActionEvent event) {
        int indexTo = toCountry.getSelectionModel().getSelectedIndex();
        int indexFrom = fromCountry.getSelectionModel().getSelectedIndex();
        toCountry.getSelectionModel().select(indexFrom);
        fromCountry.getSelectionModel().select(indexTo);
        txtGG.setText(txtGGtrans.getText());
        txtGGtrans.setText("");
    }

    private void translate(String text) throws IOException {
        toCountry.setDisable(true);
        fromCountry.setDisable(true);
        btnSwap.setDisable(true);
        txtGG.setDisable(true);

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int indexTo = toCountry.getSelectionModel().getSelectedIndex();
                        int indexFrom = fromCountry.getSelectionModel().getSelectedIndex();
                        String langTo = list_toCountry.get(indexTo);
                        String langFrom = list_fromCountry.get(indexFrom);
                        // String langTo = toCountry.getSelectionModel().getSelectedItem();
                        // String langFrom = fromCountry.getSelectionModel().getSelectedItem();
                        translatedText = sl.translator(langFrom, langTo, text);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                txtGGtrans.setText(translatedText);
                                toCountry.setDisable(false);
                                txtGG.setDisable(false);
                                fromCountry.setDisable(false);
                                btnSwap.setDisable(false);
                            }
                        });
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    @FXML
    private void initialize() {
        translatedText = "";
        sl = new SelectLang();

        toCountry.setDisable(true);
        fromCountry.setDisable(true);
        btnSwap.setDisable(true);
        txtGG.setDisable(true);

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws IOException {
                        list_lang = sl.getLanguageName();
                        list_lang_code = sl.getLanguageCode();

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (!list_lang.isEmpty()) {
                                    for (String s : list_lang) {
                                        toCountry.getItems().add(s);
                                        fromCountry.getItems().add(s);
                                    }

                                    for (String s : list_lang_code) {
                                        list_toCountry.add(s);
                                        list_fromCountry.add(s);
                                    }

                                    toCountry.setDisable(false);
                                    fromCountry.setDisable(false);
                                    btnSwap.setDisable(false);
                                    txtGG.setDisable(false);
                                }
                            }
                        });
                        return null;
                    }
                };
            }
        };
        service.start();
    }

}
