/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Movie;
import allforkids.entites.Session;
import allforkids.service.ServiceMovie;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        final File f = new File("C:\\Users\\slim\\Desktop\\33.mp4");

        final Media m = new Media(f.toURI().toString());
        final MediaPlayer mp = new MediaPlayer(m);
        final MediaView mv = new MediaView(mp);

        final DoubleProperty width = mv.fitWidthProperty();
        final DoubleProperty height = mv.fitHeightProperty();

        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));

        mv.setPreserveRatio(true);
        
        StackPane root = new StackPane();
        root.getChildren().add(mv);

        Scene scene = new Scene(root, 960, 540);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Full Screen Video Player");
        stage.setFullScreen(true);
        stage.show();

        mp.play();
    }
}