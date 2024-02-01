module com.example.kompletringsuppgift {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kompletringsuppgift to javafx.fxml;
    exports com.example.kompletringsuppgift;
}