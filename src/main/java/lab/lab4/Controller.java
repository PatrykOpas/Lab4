package lab.lab4;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import java.io.File;

public class Controller {

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml)", "*.xml");

    @FXML
    public Label labelFile;

    public void initialize(){
        fileChooser.getExtensionFilters().add(xmlFilter);
    }

    public void bntOpenFileDialog(ActionEvent actionEvent){
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            labelFile.setText(file.getAbsolutePath());
        }else{
            labelFile.setText("Wczytaj Plik...");
        }
    }

}
