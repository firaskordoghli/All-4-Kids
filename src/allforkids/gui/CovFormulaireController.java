/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Covoiturage;
import allforkids.entites.Transport;
import allforkids.service.ServiceCovoiturage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CovFormulaireController implements Initializable {
    
    ObservableList<String> typeList = FXCollections.observableArrayList("auccasionellement", "regulierement");
    @FXML
    private JFXTextField depart;
    @FXML
    private JFXTextField Region;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField arrivé;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField placeDispo;
    @FXML
    private JFXTextField fraix;
    @FXML
    private Button addButton;
    @FXML
    private Button delButton;
    @FXML
    private ComboBox type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setValue("");
        type.setItems(typeList);
    }    
    
    @FXML
    public void addCov (ActionEvent event){
    ServiceCovoiturage cService = new ServiceCovoiturage();
    Transport t = new Transport(Region.getText(),ville.getText(),depart.getText(),arrivé.getText(),
            description.getText(),telephone.getText(),placeDispo.getText(),fraix.getText());
    cService.insrerCov(t);
    }
    
}
