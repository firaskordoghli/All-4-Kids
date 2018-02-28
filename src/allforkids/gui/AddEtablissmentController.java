/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.entites.Region;
import allforkids.entites.Session;
import allforkids.entites.Ville;
import allforkids.service.ServiceEtablissement;
import allforkids.service.ServiceRegion;
import allforkids.service.ServiceVille;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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

    private static String src = "";
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;

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

        region.setPromptText("Region établissement");

        ville.setPromptText("Ville établissement");
        
        try {
            AfficherCombo();
        } catch (SQLException ex) {
            Logger.getLogger(AddEtablissmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

    @FXML
    private void addetablissement(ActionEvent event) throws IOException, SQLException {
        int u=Session.getIdThisUser();
        if (this.controleSaisie()) {
        ServiceEtablissement eService = new ServiceEtablissement();
        Etablissement e = new Etablissement(nom.getText(),
                 type.getSelectionModel().getSelectedItem(),
                 region.getSelectionModel().getSelectedItem(),
                 ville.getSelectionModel().getSelectedItem(),
                 description.getText(),
                 src
                
        );
        
        eService.insrerEtablissement(e,u);
        nom.clear();
        description.clear();
        }
        
        Stage stage = (Stage) addetablissement.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void annulerajout(ActionEvent event) {
        Stage stage = (Stage) addetablissement.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ajouterimage(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image buttonimage = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();
        int id = ServiceEtablissement.GetLastId();
        String name = nom.getText();

        save(buttonimage, id, name, p);
    }

    public void save(Image image, int id, String name, String p) throws IOException {
        if (p.indexOf(".png") != -1) {
            File fileoutput = new File("src/icons/" + id + name + ".png");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(BI, "png", fileoutput);
            src = "src/icons/" + id + name + ".png";
        } else {
            File fileoutput = new File("src/icons/" + id + name + ".jpeg");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(BI, "jpeg", fileoutput);
            src = "src/icons/" + id + name + ".png";
        }
    }

    private void AfficherCombo() throws SQLException {
       
        
        ServiceRegion sr = new ServiceRegion();
        for (Region r : sr.select2()) {
            String nom = r.getNom_region();
            region.getItems().add(nom);
            
        }
   
    
}

    @FXML
    private void selectville(ActionEvent event) throws SQLException {
        ville.getItems().clear();
        ServiceRegion sr = new ServiceRegion();
        int id= sr.GetIdByNom(region.getSelectionModel().getSelectedItem()).getId_region();
        
        ServiceVille sv = new ServiceVille();
        for (Ville v : sv.selectById(id)) {
            String nom = v.getNom_ville();
            ville.getItems().add(nom);
        }
        
        
    }
    
    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        ServiceEtablissement se = new ServiceEtablissement();

        if (!Validation.textValidation(nom, label1, "Vous devez remplir le nom de l'établissement")) {
            saisie = false;
        }
        if (!Validation.textValidation(description, label5, "Vous devez remplir la description de votre établissement")) {
            saisie = false;
        }
        if (!Validation.textValidation(type, label2,"Vous devez choisir le type")) {
            saisie = false;
        }
        if (!Validation.textValidation(region, label3,"Vous devez choisir la region")) {
            saisie = false;
        }
        if (!Validation.textValidation(ville,label4,"Vous devez choisir la ville")) {
            saisie = false;
        }

           return saisie;
    }

}
