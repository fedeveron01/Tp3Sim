module com.tp.tp3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tp.tp3 to javafx.fxml;
    exports com.tp.tp3;
}