module lab.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab.lab4 to javafx.fxml;
    exports lab.lab4;
}