package lab.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Wczytywanie XML");

        Controller controller = fxmlLoader.getController();

        stage.setMinHeight(639);
        stage.setMaxHeight(639);

        stage.setMinWidth(815);
        stage.setMaxWidth(815);

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}