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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class ModifierEtablissementController implements Initializable {

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
    private JFXButton boutonimage;
    @FXML
    private JFXButton boutonvalide;

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

        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(10);

        nom.setText(e.getNom());
        type.setValue(e.getType());
        region.setValue(e.getRegion());
        ville.setValue(e.getVille());
        description.setText(e.getDescription());
    }

    @FXML
    private void modifer(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(10);
        int id = e.getId();
        Etablissement et = new Etablissement(nom.getText(),
                description.getText(),
                type.getValue(),
                region.getValue(),
                ville.getValue(),
                e.getImage());
        sr1.updateEtablissement(et, id);
    }

}
