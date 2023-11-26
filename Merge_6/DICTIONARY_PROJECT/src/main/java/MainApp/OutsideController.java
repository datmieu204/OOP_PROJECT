package MainApp;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OutsideController {
    
    private static String author1 = "nguyễn Tuấn Đạt";

    private static String author2 = "Nguyễn Yến Nhi";

    private static String author3 = "Lê Đắc Thịnh";

    private static int id1 = 22024518;

    private static int id2 = 22024573;

    private static int id3 = 22024544;

    private static String className = "K67_T_CLC";

    @FXML
    private Label author1Label;

    @FXML
    private Label author2Label;

    @FXML
    private Label author3Label;

    @FXML
    private Label id1Label;

    @FXML
    private Label id2Label;

    @FXML
    private Label id3Label;

    @FXML
    private Label classLabel;

    @FXML
    private Button click;

    @FXML
    private void initialize() {
        author1Label.setText(author1);
        author2Label.setText(author2);
        author3Label.setText(author3);

        id1Label.setText(String.valueOf(id1));
        id2Label.setText(String.valueOf(id2));
        id3Label.setText(String.valueOf(id3));

        classLabel.setText(className);
    }

    @FXML
    void open() {
        HostServices hostServices = App.getHostServicesInstance();
        hostServices.showDocument("https://github.com/datmieu204");
    }

}
