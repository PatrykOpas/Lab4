package lab.lab4;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Controller {

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml)", "*.xml");

    @FXML
    public Label labelFile;
    public ListView lstInfograf;
    ObservableList<String> titles = FXCollections.observableArrayList();
    GusInfoGraphicList igList;

    public ImageView infoGraf;
    public Button btnPokazGrafike;
    public Button btnWeb;
    public TextField labelAdress;

    public void centerImage() {
        Image img = infoGraf.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = infoGraf.getFitWidth() / img.getWidth();
            double ratioY = infoGraf.getFitHeight() / img.getHeight();

            double reduc = 0;
            if(ratioX >= ratioY) {
                reduc = ratioY;
            } else {
                reduc = ratioX;
            }

            w = img.getWidth() * reduc;
            h = img.getHeight() * reduc;

            infoGraf.setX((infoGraf.getFitWidth() - w) / 2);
            infoGraf.setY((infoGraf.getFitHeight() - h) / 2);

        }
    }

    private Infografika selectedInfo;
    public void initialize(){
        fileChooser.getExtensionFilters().add(xmlFilter);

        lstInfograf.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        int index = t1.intValue();
                        if(index != -1){
                            labelAdress.setText(igList.infografiki.get(index).Web);
                            Image image;
                            if(igList.infografiki.get(index).IconWeb != "Brak"){
                                image = new Image(igList.infografiki.get(index).IconWeb);
                                centerImage();
                            }else{
                                image = null;
                            }
                            infoGraf.setImage(image);
                            selectedInfo = igList.infografiki.get(index);
                        }else{
                            labelAdress.setText("");
                            infoGraf.setImage(null);
                            selectedInfo = null;
                        }
                    }
                }
        );
    }

    public void bntOpenFileDialog(ActionEvent actionEvent){
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            igList = new GusInfoGraphicList(file.getAbsolutePath());
            labelFile.setText(file.getAbsolutePath());
            for(Infografika ig: igList.infografiki) if(ig.Title != "Brak") titles.add(ig.Title);
            lstInfograf.setItems(titles);

        }else{
            labelFile.setText("Wczytaj Plik...");
        }
    }

    HostServices hostServices;
    public void loadWeb(ActionEvent actionEvent) {
        if(selectedInfo != null){
            try {
                Desktop.getDesktop().browse(new URL(selectedInfo.Web).toURI());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnPokazOnAction(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("ImgViewer.fxml"));
            Parent root = loader.load();
            ImgViewer viewer = loader.getController();
            if(selectedInfo != null && selectedInfo.Web != "Brak"){
                Image img = new Image(selectedInfo.ImgWeb);
                viewer.imgView.setFitWidth(img.getWidth());
                viewer.imgView.setFitHeight(img.getHeight());
                viewer.imgView.setImage(img);
            }
            Stage stage = new Stage();
            stage.setTitle("Podgląd Grafiki");
            stage.setScene(new Scene(root, 900, 800));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
