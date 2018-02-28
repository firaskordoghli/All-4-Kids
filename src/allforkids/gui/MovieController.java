/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Movie;
import allforkids.entites.Session;
import allforkids.service.ServiceMovie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class MovieController implements Initializable {

    private StackPane root;
    @FXML
    private JFXListView<Label> listMovie;
    private ServiceMovie sm = new ServiceMovie();
  public static String url ;
    @FXML
    private Button toolbar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL urll, ResourceBundle rb) {
        List<Movie> m = new ArrayList<>();
        m = sm.selectMovie();

        for (Movie mv : m) {

            Label la = new Label(mv.getNom());
            la.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + mv.getImg()));

            i.setFitHeight(50);
            i.setFitWidth(50);

            la.setGraphic(i);
            listMovie.getItems().add(la);
            listMovie.setStyle("-fx-background-color: #B7D7F2");

        }

    }

    @FXML
    private void lesFilm(MouseEvent event) {
         try {
           String nom = listMovie.getSelectionModel().getSelectedItem().getText();
           Movie m = sm.getIdByName(nom);
           url = m.getUrl();
          
         Stage stage = new Stage();
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("ShowMovie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         }catch(Exception e){
         
         }
    }
}
