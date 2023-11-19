module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.media;

    requires jsapi;
    requires voicerss.tts;

    requires google.cloud.speech;
    requires google.api.translate.java;

    requires transitive javafx.graphics;
    
    opens org.openjfx.MainApp to javafx.fxml;
    exports org.openjfx.MainApp;
}
