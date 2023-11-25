module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.media;
    requires java.logging;

    requires jsapi;
    requires voicerss.tts;

    requires google.cloud.speech;
    requires google.api.translate.java;

    requires transitive javafx.graphics;
    
    opens MainApp to javafx.fxml;
    exports MainApp;

    opens MainApp.Game.MemoryGame to javafx.fxml, javafx.base, javafx.graphics;
    exports MainApp.Game.MemoryGame;

    opens MainApp.Game.Wordle to javafx.fxml, javafx.base, javafx.graphics;
    exports MainApp.Game.Wordle;

    opens MainApp.Game to javafx.fxml, javafx.base, javafx.graphics;
    exports MainApp.Game;
}
