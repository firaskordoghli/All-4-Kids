/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Covoiturage;
import allforkids.service.ServiceCovoiturage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    Covoiturage c = new Covoiturage(depart.getText(),arrive.getText());
    cService.ajoucov(c);
    }
    
}
