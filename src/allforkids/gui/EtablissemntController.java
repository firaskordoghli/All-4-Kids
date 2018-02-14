/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class EtablissemntController implements Initializable {
    
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfRegion;
    @FXML
    private TextField tfVille;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void saveEtablissement(ActionEvent event) {
        ServiceEtablissement pEtablissement = new ServiceEtablissement();
        //System.out.println(tfnombre.getText());
        Etablissement e =new Etablissement(tfNom.getText()
                ,tfType.getText(),tfRegion.getText(),tfVille.getText(),tfDescription.getText(),true,2);
        pEtablissement.insrerEtablissement(e);
        tfNom.clear();
        tfType.clear();
        tfRegion.clear();
        tfVille.clear();
        tfDescription.clear();
    }
    
}
