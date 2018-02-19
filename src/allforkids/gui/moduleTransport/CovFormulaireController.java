/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui.moduleTransport;

import allforkids.entites.Covoiturage;
import allforkids.entites.Transport;
import allforkids.service.ServiceCovoiturage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CovFormulaireController implements Initializable {

    @FXML
    private JFXTextField depart;
    @FXML
    private JFXTextField arrive;
    @FXML
    private JFXButton add;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void addCov (ActionEvent event){
    ServiceCovoiturage cService = new ServiceCovoiturage();
    Transport t = new Transport(Region.getText(),ville.getText(),depart.getText(),arrivé.getText(),
            description.getText(),telephone.getText(),placeDispo.getText(),fraix.getText());
    cService.insrerCov(t);
    }
    
}
