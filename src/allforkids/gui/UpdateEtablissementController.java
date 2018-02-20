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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class UpdateEtablissementController implements Initializable {

    @FXML
    private JFXTextField nometablissemnt;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXComboBox<String> typeet;
    @FXML
    private JFXComboBox<String> regionet;
    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private JFXButton boutonimage;
    @FXML
    private JFXButton boutonvalide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        typeet.getItems().addAll(
                "Garderie",
                "jardin d'enfant",
                "ecole primaire",
                "colége",
                "lycée"
        );
        typeet.setPromptText("Type établissement");

        regionet.getItems().addAll(
                "Garderie",
                "jardin d'enfant",
                "ecole primaire",
                "colége",
                "lycée"
        );
        regionet.setPromptText("Type établissement");

        ville.getItems().addAll(
                "Garderie",
                "jardin d'enfant",
                "ecole primaire",
                "colége",
                "lycée"
        );
        ville.setPromptText("Type établissement");

        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(9);

        nometablissemnt.setText(e.getNom());
        description.setText(e.getDescription());
        typeet.setValue(e.getType());
        regionet.setValue(e.getRegion());
        ville.setValue(e.getVille());

    }

    @FXML
    private void inseret(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(9);
        int id = e.getId();
        Etablissement e1 = new Etablissement(nometablissemnt.getText(),
                description.getText(),
                typeet.getValue(),
                regionet.getValue(),
                ville.getValue(),
                e.getImage());
        sr1.updateEtablissement(e1, id);
    }

}
