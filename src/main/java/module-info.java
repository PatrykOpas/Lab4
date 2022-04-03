module lab.lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens lab.lab4 to javafx.fxml;
    exports lab.lab4;
}