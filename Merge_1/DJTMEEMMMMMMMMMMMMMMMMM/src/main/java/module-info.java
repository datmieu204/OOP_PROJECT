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
    
    opens org.openjfx.MainApp to javafx.fxml;
    exports org.openjfx.MainApp;

    opens org.openjfx.MainApp.Game.MemoryGame to javafx.fxml, javafx.base, javafx.graphics;
    exports org.openjfx.MainApp.Game.MemoryGame;

    opens org.openjfx.MainApp.Game.Wordle to javafx.fxml, javafx.base, javafx.graphics;
    exports org.openjfx.MainApp.Game.Wordle;

    opens org.openjfx.MainApp.Game to javafx.fxml, javafx.base, javafx.graphics;
    exports org.openjfx.MainApp.Game;
}
