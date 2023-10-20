module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    
    opens org.openjfx.MainApp to javafx.fxml;
    exports org.openjfx.MainApp;
    
}