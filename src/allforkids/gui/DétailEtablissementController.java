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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class DétailEtablissementController implements Initializable {

    @FXML
    private ImageView image;
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
    private JFXButton supprimer;
    @FXML
    private JFXButton modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceEtablissement sr1= new ServiceEtablissement();
        
        Etablissement e = sr1.GetEtablissemebtById(10);
        nometablissement.setText(e.getNom());
        description.setText(e.getDescription());
        type.setText(e.getType());
        region.setText(e.getRegion());
        ville.setText(e.getVille());
        Image img;
        try {
            img = new Image(new FileInputStream(e.getImage()));
            
        image.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DétailEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
    }    
    

    @FXML
    private void DeleteEtablissement(ActionEvent event) {
        ServiceEtablissement sr1= new ServiceEtablissement();
        
        sr1.deleteEtablissement(9);
        
        
    }

    @FXML
    private void UpdateEtablissement(ActionEvent event) throws IOException {
        Stage stage = new Stage();
             stage.setTitle("Ajouter Etablissement");
        Parent root = FXMLLoader.load(getClass().getResource("ModifierEtablissement.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }
    
}
