/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane detail;
    @FXML
    private ImageView imageview1;
    @FXML
    private Label nometablissement;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Label region;
    @FXML
    private Label ville;
    @FXML
    private TableView<Etablissement> tableview;
    @FXML
    private TableColumn<Etablissement, String> nomCol;
    @FXML
    private TableColumn<Etablissement, String> typeCol;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXButton supprimer;
    @FXML
    private Label etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ServiceEtablissement sr1 = new ServiceEtablissement();
        try {
            tableview.setItems(sr1.selectEtablissement2());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        detail.setVisible(false);
        valider.setVisible(false);
        supprimer.setVisible(false);
    }    

    @FXML
    private void consulter(MouseEvent event) {
         detail.setVisible(true);
           valider.setVisible(true);
           supprimer.setVisible(true);
        ServiceEtablissement sr1 = new ServiceEtablissement();

        Etablissement e = sr1.GetEtablissemebtById(tableview.getSelectionModel().getSelectedItem().getId());
        nometablissement.setText(e.getNom());
        description.setText(e.getDescription());
        type.setText(e.getType());
        region.setText(e.getRegion());
        ville.setText(e.getVille());
        Image img;
        if(e.getVerification().equals("Valide")){
            valider.setVisible(false);
        }
        try {
            img = new Image(new FileInputStream(e.getImage()));
            imageview1.setImage(img);
        } catch (Exception ex) {
            System.out.println(ex); }
         etat.setText(e.getVerification());
    }

    @FXML
    private void valider(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(tableview.getSelectionModel().getSelectedItem().getId());
        int id = e.getId();
        sr1.ValiderEtablissement(e, id);
        valider.setVisible(false);
    }

    @FXML
    private void delete(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        int a = tableview.getSelectionModel().getSelectedIndex();
        sr1.deleteEtablissement(tableview.getSelectionModel().getSelectedItem().getId());
        tableview.getItems().remove(a);
        detail.setVisible(false);
    }
    
}
