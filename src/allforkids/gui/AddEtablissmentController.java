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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    private static String src="" ;

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
                                "Tunis",
                                "Kairoun",
                                "Nabeul",
                                "Bizerte",
                                "Sousse"
                                        
                );
        region.setPromptText("Region établissement");
        
        ville.getItems().addAll(
                                "Centre ville",
                                "Ariana",
                                "Hay Tahrire",
                                "Bardo",
                                "Manar"
                                        
                );
        ville.setPromptText("Ville établissement");
        
    }    

    @FXML
    private void addetablissement(ActionEvent event) {
        
       ServiceEtablissement eService = new ServiceEtablissement();
        Etablissement e =new Etablissement(nom.getText()
                                            ,type.getSelectionModel().getSelectedItem()
                                            ,region.getSelectionModel().getSelectedItem()
                                            ,ville.getSelectionModel().getSelectedItem()
                                            ,description.getText()
                                            ,src);
        
        eService.insrerEtablissement(e);
        nom.clear();
        description.clear();
        
        
        
    }

    @FXML
    private void annulerajout(ActionEvent event) {
        Stage stage =(Stage) addetablissement.getScene().getWindow();
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
        int id =ServiceEtablissement.GetLastId();
        String name = nom.getText();

        save(buttonimage,id , name, p);
    }
    
    public void save(Image image,int id, String name, String p) throws IOException {
        if (p.indexOf(".png") != -1) {
            File fileoutput = new File("src/icons/" + id  + name + ".png");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(BI, "png", fileoutput);
            src=  "src/icons/" + id + name + ".png";
        } else {
            File fileoutput = new File("src/icons/" + id + name + ".jpeg");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(BI, "jpeg", fileoutput);
             src=  "src/icons/" + id + name + ".png";
        }
    }
    
}
