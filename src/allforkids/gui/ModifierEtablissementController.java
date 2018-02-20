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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    @FXML
    private ImageView viewimage;
    
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

        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(ListEtablissementController.nb);

        nom.setText(e.getNom());
        type.setValue(e.getType());
        region.setValue(e.getRegion());
        ville.setValue(e.getVille());
        description.setText(e.getDescription());
        Image img;
        try {
            img = new Image(new FileInputStream(e.getImage()));
            
        viewimage.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DétailEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifer(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(ListEtablissementController.nb);
        int id = e.getId();
        Etablissement et = new Etablissement(nom.getText(),
                description.getText(),
                type.getValue(),
                region.getValue(),
                ville.getValue(),
                src);
        sr1.updateEtablissement(et, id);
    }

    @FXML
    private void updateimage(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image boutonimage = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();
        int id =ServiceEtablissement.GetLastId();
        String name = nom.getText();

        save(boutonimage,id , name, p);
        
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
