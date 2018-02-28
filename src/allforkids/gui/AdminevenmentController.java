/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceParticipevenement;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class AdminevenmentController implements Initializable {

    @FXML
    private TableView<Evenement> tabevent;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> nbplase;

    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton sdepasser;
    @FXML
    private JFXButton ajouter;
    ServiceEvenement s = new ServiceEvenement();
    @FXML
    private AnchorPane detail;
    @FXML
    private Label createur;
    @FXML
    private Label nb;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton libbtn;
    @FXML
    private JFXButton filmbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Evenement> oe = FXCollections.observableArrayList();

        oe.addAll(s.selectEvenement());

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        nbplase.setCellValueFactory(new PropertyValueFactory<>("nbr_participation"));

        tabevent.setItems(oe);
        detail.setVisible(false);
    }

    @FXML
    private void dleteslect(ActionEvent event) {
        Evenement e = tabevent.getSelectionModel().getSelectedItem();

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer " + e.getNom() + " ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int id = e.getId_evenement();
            s.deleteEvenement(id);
            int a = tabevent.getSelectionModel().getSelectedIndex();
            tabevent.getItems().remove(a);
            detail.setVisible(false);
        }

    }

    @FXML
    private void supprimedepasse(ActionEvent event) {

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer les evenement  depasse ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            s.deleteEvenementdepas();

            detail.setVisible(false);
        }

    }

    @FXML
    private void Ajoterevenment(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void detailee(MouseEvent event) {
        ServiceParticipevenement sp = new ServiceParticipevenement();
        Evenement e = tabevent.getSelectionModel().getSelectedItem();
        String u = s.selectUsers(e.getId_user());
        createur.setText(u);
        int nbr = sp.nbparticipent(e.getId_evenement());
        nb.setText(String.valueOf(nbr));
        Image image = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + e.getPhoto());
        img.setImage(image);
        detail.setVisible(true);
    }

    @FXML
    private void toLib(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Adminlaiberi.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toFilm(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Movie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
