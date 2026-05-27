module com.example.miniproyecto2sudokujsdqlszg {
    requires javafx.controls;
    requires javafx.fxml;


    opens Model to javafx.fxml;
    opens Controller to javafx.fxml;
    exports Model;
    exports Controller;
}