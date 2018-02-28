/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.service.ServiceEvenement;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Evenement, String> user;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton sdepasser;
    @FXML
    private JFXButton ajouter;
    ServiceEvenement s = new ServiceEvenement();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Evenement> oe = FXCollections.observableArrayList(); ;
        oe.addAll(s.selectEvenement());
           nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
           date.setCellValueFactory(new PropertyValueFactory<>("date"));
           nbplase.setCellValueFactory(new PropertyValueFactory<>("nbr_participation"));
           user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
           tabevent.setItems(oe);
    }    

    @FXML
    private void dleteslect(ActionEvent event) {
    }

    @FXML
    private void supprimedepasse(ActionEvent event) {
    }

    @FXML
    private void Ajoterevenment(ActionEvent event) {
    }
    
}
