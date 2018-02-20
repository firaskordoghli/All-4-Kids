/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    private JFXComboBox<String> type;
    
    @FXML
    private JFXComboBox<String> region;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane addetablissement;
    @FXML
    private JFXButton buttonimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        type.getItems().addAll(
                                "Garderie",
                                "jardin d'enfant",
                                "ecole primaire",
                                "colége",
                                "lycée"
                                        
                );
        type.setPromptText("Type établissement");
        
        region.getItems().addAll(
                                "Garderie",
                                "jardin d'enfant",
                                "ecole primaire",
                                "colége",
                                "lycée"
                                        
                );
        region.setPromptText("Type établissement");
        
        ville.getItems().addAll(
                                "Garderie",
                                "jardin d'enfant",
                                "ecole primaire",
                                "colége",
                                "lycée"
                                        
                );
        ville.setPromptText("Type établissement");
        
    }    

    @FXML
    private void addetablissement(ActionEvent event) {
        
       ServiceEtablissement eService = new ServiceEtablissement();
        Etablissement e =new Etablissement(nom.getText()
                                            ,type.getSelectionModel().getSelectedItem()
                                            ,region.getSelectionModel().getSelectedItem()
                                            ,ville.getSelectionModel().getSelectedItem()
                                            ,description.getText()
                                            ,image.getText());
        
        eService.insrerEtablissement(e);
        nom.clear();
        description.clear();
        image.clear();
        
        
        
    }

    @FXML
    private void annulerajout(ActionEvent event) {
        Stage stage =(Stage) addetablissement.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ajouterimage(ActionEvent event) {
    }
    
}
