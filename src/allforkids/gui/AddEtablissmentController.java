/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class AddEtablissmentController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField image;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane addetablissement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addetablissement(ActionEvent event) {
        
       ServiceEtablissement eService = new ServiceEtablissement();
        Etablissement e =new Etablissement(nom.getText()
                                            ,type.getText()
                                            ,region.getText()
                                            ,ville.getText()
                                            ,description.getText()
                                            ,image.getText());
        
        eService.insrerEtablissement(e);
        nom.clear();
        type.clear();
        region.clear();
        ville.clear();
        description.clear();
        image.clear();
        
        
        
    }

    @FXML
    private void annulerajout(ActionEvent event) {
        Stage stage =(Stage) addetablissement.getScene().getWindow();
        stage.close();
    }
    
}
